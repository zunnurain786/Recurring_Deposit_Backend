package com.recurring_deposit.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.recurring_deposit.DTO.RdAccountRequest;
import com.recurring_deposit.DTO.RdAccountResponse;
import com.recurring_deposit.Service.RdAccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/rdaccounts")
@Tag(
        name = "RD Account Management",
        description = "APIs for Recurring Deposit Account Operations"
)
public class RdAccountController {

    private final RdAccountService rdAccountService;

    public RdAccountController(
            RdAccountService rdAccountService) {

        this.rdAccountService = rdAccountService;
    }

    /*
     * CREATE RD ACCOUNT
     */
    @PreAuthorize("hasRole('CUSTOMER')")
    @Operation(
            summary = "Create RD Account",
            description = "Creates a new RD Account"
    )
    @PostMapping("/save")
    public ResponseEntity<RdAccountResponse> createRdAccount(
            @RequestBody RdAccountRequest request) {

        return ResponseEntity.ok(
                rdAccountService.createRdAccount(request));
    }

    /*
     * GET ALL RD ACCOUNTS
     */
    @PreAuthorize("hasAnyRole('ADMIN','AGENT')")
    @Operation(
            summary = "Get All RD Accounts"
    )
    @GetMapping("/all")
    public ResponseEntity<List<RdAccountResponse>>
    getAllRdAccounts() {

        return ResponseEntity.ok(
                rdAccountService.getAllRdAccounts());
    }

    /*
     * GET RD ACCOUNT BY ID
     */
    @PreAuthorize("hasAnyRole('ADMIN','AGENT','CUSTOMER')")
    @Operation(
            summary = "Get RD Account By ID"
    )
    @GetMapping("/{id}")
    public ResponseEntity<RdAccountResponse>
    getRdAccountById(

            @Parameter(
                    description = "RD Account Id",
                    example = "1")
            @PathVariable Long id) {

        return ResponseEntity.ok(
                rdAccountService.getRdAccountById(id));
    }

    /*
     * CUSTOMER ACCOUNTS
     */
    @PreAuthorize("hasAnyRole('CUSTOMER','AGENT','ADMIN')")
    @Operation(
            summary = "Get Customer RD Accounts"
    )
    @GetMapping("/customer/{userId}")
    public ResponseEntity<List<RdAccountResponse>>
    getCustomerAccounts(

            @PathVariable Long userId) {

        return ResponseEntity.ok(
                rdAccountService
                        .getRdAccountByCustomerId(userId));
    }

    /*
     * AGENT ACCOUNTS
     */
    @PreAuthorize("hasAnyRole('ADMIN','AGENT')")
    @Operation(
            summary = "Get Agent RD Accounts"
    )
    @GetMapping("/agent/{userId}")
    public ResponseEntity<List<RdAccountResponse>>
    getAgentAccounts(

            @PathVariable Long userId) {

        return ResponseEntity.ok(
                rdAccountService
                        .getAgentAccounts(userId));
    }

    /*
     * UPDATE RD ACCOUNT
     */
    @PreAuthorize("hasAnyRole('ADMIN','AGENT')")
    @Operation(
            summary = "Update RD Account"
    )
    @PutMapping("/{id}")
    public ResponseEntity<RdAccountResponse>
    updateRdAccount(

            @PathVariable Long id,

            @RequestBody RdAccountRequest request) {

        return ResponseEntity.ok(
                rdAccountService
                        .updateRdAccount(id, request));
    }

    /*
     * DELETE RD ACCOUNT
     */
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
            summary = "Delete RD Account"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String>
    deleteRdAccount(

            @PathVariable Long id) {

        rdAccountService.deleteRdAccount(id);

        return ResponseEntity.ok(
                "RD Account deleted successfully");
    }
}