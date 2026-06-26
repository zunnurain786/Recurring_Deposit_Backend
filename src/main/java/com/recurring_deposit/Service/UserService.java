package com.recurring_deposit.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.recurring_deposit.Entity.UserEntity;
import com.recurring_deposit.Repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(
            UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    /*
     * SAVE USER
     */
    public UserEntity saveUser(
            UserEntity user) {

        return userRepository.save(user);
    }

    /*
     * GET ALL USERS
     */
    public List<UserEntity> getAllUsers() {

        return userRepository.findAll();
    }

    /*
     * GET USER BY ID
     */
    public UserEntity getUserById(
            Long userId) {

        return userRepository
                .findById(userId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found with ID : "
                                        + userId));
    }

    /*
     * UPDATE USER
     */
    public UserEntity updateUser(
            Long userId,
            UserEntity updatedUser) {

        UserEntity existingUser =
                userRepository
                        .findById(userId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "User not found with ID : "
                                                + userId));

        existingUser.setFullName(
                updatedUser.getFullName());

        existingUser.setEmail(
                updatedUser.getEmail());

        existingUser.setMobileNumber(
                updatedUser.getMobileNumber());

        existingUser.setActive(
                updatedUser.getActive());

        existingUser.setRole(
                updatedUser.getRole());

        return userRepository.save(
                existingUser);
    }

    /*
     * DELETE USER
     */
    public void deleteUser(
            Long userId) {

        UserEntity user =
                userRepository
                        .findById(userId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "User not found with ID : "
                                                + userId));

        userRepository.delete(user);
    }
}