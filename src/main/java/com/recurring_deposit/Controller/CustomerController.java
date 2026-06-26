package com.recurring_deposit.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.recurring_deposit.Entity.CustomerEntity;
import com.recurring_deposit.Service.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/customers")
@Tag(
        name = "Customer Management",
        description = "APIs for Customer Operations"
)
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(
            CustomerService customerService) {

        this.customerService = customerService;
    }

    /*
     * SAVE CUSTOMER
     */
    @Operation(
            summary = "Create Customer Profile",
            description = "Creates a new customer profile"
    )
    @PostMapping("/save")
    public ResponseEntity<CustomerEntity> saveCustomer(
            @RequestBody CustomerEntity customer) {

        CustomerEntity savedCustomer =
                customerService.saveCustomer(customer);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedCustomer);
    }

    /*
     * GET ALL CUSTOMERS
     */
    @Operation(
            summary = "Get All Customers",
            description = "Fetch all customers"
    )
    @PreAuthorize("hasAnyRole('ADMIN','AGENT')")
    @GetMapping("/all")
    public ResponseEntity<List<CustomerEntity>> getAllCustomers() {

        return ResponseEntity.ok(
                customerService.getAllCustomers()
        );
    }

    /*
     * GET CUSTOMER BY ID
     */
    @Operation(
            summary = "Get Customer By Id",
            description = "Fetch customer details using customer id"
    )
    @PreAuthorize("hasAnyRole('ADMIN','AGENT','CUSTOMER')")
    @GetMapping("/{id}")
    public ResponseEntity<CustomerEntity> getCustomerById(
            @PathVariable Long id) {

        CustomerEntity customer =
                customerService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    /*
     * UPDATE CUSTOMER
     */
    @Operation(
            summary = "Update Customer",
            description = "Update customer profile"
    )
    @PreAuthorize("hasAnyRole('ADMIN','AGENT','CUSTOMER')")
    @PutMapping("/update/{customerId}")
    public ResponseEntity<CustomerEntity> updateCustomer(
    		@PathVariable Long customerId,
    		@RequestBody CustomerEntity customer) {

        CustomerEntity updatedCustomer =
                customerService.updateCustomer(customerId , customer);

        return ResponseEntity.ok(updatedCustomer);
    }

    /*
     * DELETE CUSTOMER
     */
    @Operation(
            summary = "Delete Customer",
            description = "Delete customer by id"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(
            @PathVariable Long id) {

        customerService.deleteCustomer(id);

        return ResponseEntity.ok(
                "Customer deleted successfully"
        );
    }

    /*
     * GET CUSTOMER BY USER ID
     */
    @Operation(
            summary = "Get Customer By User Id",
            description = "Fetch customer profile using user id"
    )
    @PreAuthorize("hasAnyRole('ADMIN','AGENT','CUSTOMER')")
    @GetMapping("/user/{userId}")
    public ResponseEntity<CustomerEntity> getCustomerByUserId(
            @PathVariable Long userId) {

        CustomerEntity customer =
                customerService.getCustomerByUserId(userId);

        return ResponseEntity.ok(customer);
    }
}