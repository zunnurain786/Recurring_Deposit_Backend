package com.recurring_deposit.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.recurring_deposit.Entity.TransactionEntity;
import com.recurring_deposit.Service.TransactionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/transactions")
@Tag(
name = "Transaction Management",
description = "APIs for managing RD transactions"
)
public class TransactionController {


private final TransactionService transactionService;

public TransactionController(
        TransactionService transactionService) {

    this.transactionService = transactionService;
}

/*
 * CREATE TRANSACTION
 */
@PreAuthorize("hasAnyRole('ADMIN','AGENT')")
@PostMapping("/save")
@Operation(
        summary = "Create Transaction",
        description = "Create a new transaction"
)
@ApiResponses({
        @ApiResponse(responseCode = "200",
                description = "Transaction created successfully"),
        @ApiResponse(responseCode = "400",
                description = "Invalid request")
})
public ResponseEntity<TransactionEntity> saveTransaction(
        @RequestBody TransactionEntity transaction) {

    TransactionEntity savedTransaction =
            transactionService.saveTransaction(transaction);

    return ResponseEntity.ok(savedTransaction);
}

/*
 * GET ALL TRANSACTIONS
 */
@PreAuthorize("hasAnyRole('ADMIN','AGENT')")
@GetMapping("/all")
@Operation(
        summary = "Get All Transactions",
        description = "Returns all transactions"
)
public ResponseEntity<List<TransactionEntity>>
getAllTransactions() {

    return ResponseEntity.ok(
            transactionService.getAllTransactions());
}

/*
 * GET TRANSACTION BY ID
 */
@PreAuthorize("hasAnyRole('ADMIN','AGENT')")
@GetMapping("/{id}")
@Operation(
        summary = "Get Transaction By ID",
        description = "Returns transaction details by ID"
)
@ApiResponses({
        @ApiResponse(responseCode = "200",
                description = "Transaction found"),
        @ApiResponse(responseCode = "404",
                description = "Transaction not found")
})
public ResponseEntity<TransactionEntity>
getTransactionById(

        @Parameter(
                description = "Transaction ID",
                example = "1")
        @PathVariable Long id) {

    TransactionEntity transaction =
            transactionService.getTransactionById(id);
    return ResponseEntity.ok(transaction);
}

/*
 * UPDATE TRANSACTION
 */
@PreAuthorize("hasRole('ADMIN')")
@PutMapping("/{id}")
@Operation(
        summary = "Update Transaction",
        description = "Updates an existing transaction"
)
public ResponseEntity<TransactionEntity>
updateTransaction(

        @PathVariable Long id,

        @RequestBody TransactionEntity transaction) {

    TransactionEntity updatedTransaction =
            transactionService.updateTransaction(
                    transaction);

    return ResponseEntity.ok(
            updatedTransaction);
}

/*
 * DELETE TRANSACTION
 */
@PreAuthorize("hasRole('ADMIN')")
@DeleteMapping("/{id}")
@Operation(
        summary = "Delete Transaction",
        description = "Deletes transaction by ID"
)
public ResponseEntity<String>
deleteTransaction(

        @Parameter(
                description = "Transaction ID",
                example = "1")
        @PathVariable Long id) {

    transactionService.deleteTransaction(id);

    return ResponseEntity.ok(
            "Transaction deleted successfully");
}


}
