package com.recurring_deposit.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.recurring_deposit.Entity.CustomerEntity;
import com.recurring_deposit.Entity.UserEntity;
import com.recurring_deposit.Repository.CustomerRepository;
import com.recurring_deposit.Repository.UserRepository;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    public CustomerService(
            CustomerRepository customerRepository,
            UserRepository userRepository) {

        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
    }

    /*
     * SAVE CUSTOMER
     */
    public CustomerEntity saveCustomer(
            CustomerEntity customer) {

        if (customer.getUser() == null ||
                customer.getUser().getUserId() == null) {

            throw new RuntimeException(
                    "User Id is required");
        }

        UserEntity user =
                userRepository.findById(
                        customer.getUser().getUserId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "User not found"));

        if (customerRepository.existsByUserUserId(
                user.getUserId())) {

            throw new RuntimeException(
                    "Customer profile already exists");
        }

        customer.setUser(user);

        CustomerEntity savedCustomer =
                customerRepository.save(customer);

        user.setProfileCompleted(true);

        userRepository.save(user);

        return savedCustomer;
    }

    /*
     * GET ALL CUSTOMERS
     */
    public List<CustomerEntity> getAllCustomers() {

        return customerRepository.findAll();
    }

    /*
     * GET CUSTOMER BY ID
     */
    public CustomerEntity getCustomerById(
            Long customerId) {

        return customerRepository
                .findById(customerId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Customer not found"));
    }

    /*
     * UPDATE CUSTOMER
     */
    public CustomerEntity updateCustomer(
            Long customerId,
            CustomerEntity updatedCustomer) {

        CustomerEntity existingCustomer =
                customerRepository
                        .findById(customerId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Customer not found"));

        existingCustomer.setAddress(
                updatedCustomer.getAddress());

        existingCustomer.setCity(
                updatedCustomer.getCity());

        existingCustomer.setOccupation(
                updatedCustomer.getOccupation());

        existingCustomer.setMonthlyIncome(
                updatedCustomer.getMonthlyIncome());

        existingCustomer.setDateOfBirth(
                updatedCustomer.getDateOfBirth());

        existingCustomer.setActive(
                updatedCustomer.getActive());

        return customerRepository.save(
                existingCustomer);
    }

    /*
     * DELETE CUSTOMER
     */
    public void deleteCustomer(
            Long customerId) {

        CustomerEntity customer =
                customerRepository
                        .findById(customerId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Customer not found"));

        customerRepository.delete(customer);
    }

    /*
     * GET CUSTOMER BY USER ID
     */
    public CustomerEntity getCustomerByUserId(
            Long userId) {

        return customerRepository
                .findByUserUserId(userId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Customer not found"));
    }
}