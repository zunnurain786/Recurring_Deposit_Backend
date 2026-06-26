package com.recurring_deposit.Controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.recurring_deposit.Entity.UserEntity;
import com.recurring_deposit.Service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/users")
@Tag(
        name = "User Management",
        description = "APIs for managing users"
)
public class UserController {

    private final UserService userService;

    public UserController(
            UserService userService) {

        this.userService = userService;
    }

    /*
     * GET ALL USERS
     */
    @Operation(
            summary = "Get All Users",
            description = "Fetch all registered users from the system"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Users fetched successfully"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Access denied"
            )
    })
    @PreAuthorize("hasAnyRole('ADMIN','AGENT')")
    @GetMapping("/all")
    public ResponseEntity<List<UserEntity>> getAllUsers() {

        return ResponseEntity.ok(
                userService.getAllUsers());
    }

    /*
     * GET USER BY ID
     */
    @Operation(
            summary = "Get User By Id",
            description = "Fetch user details using user id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "User found"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Access denied"
            )
    })
    @PreAuthorize("hasAnyRole('ADMIN','AGENT','CUSTOMER')")
    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById(
            @PathVariable Long id) {

        UserEntity user =
                userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    /*
     * UPDATE USER
     */
    @Operation(
            summary = "Update User",
            description = "Update user information using user id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "User updated successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Access denied"
            )
    })
    @PreAuthorize("hasAnyRole('ADMIN','AGENT','CUSTOMER')")
    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserEntity user) {

        user.setUserId(id);

        UserEntity updatedUser =
                userService.updateUser(id , user);

        return ResponseEntity.ok(updatedUser);
    }

    /*
     * DELETE USER
     */
    @Operation(
            summary = "Delete User",
            description = "Delete a user using user id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "User deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Access denied"
            )
    })
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(
            @PathVariable Long id) {

        userService.deleteUser(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("User deleted successfully");
    }
}