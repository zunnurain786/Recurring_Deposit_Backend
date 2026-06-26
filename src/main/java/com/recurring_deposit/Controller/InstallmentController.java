package com.recurring_deposit.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.recurring_deposit.DTO.CustomerInstallment;
import com.recurring_deposit.Entity.InstallmentEntity;
import com.recurring_deposit.Service.InstallmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/installments")
@Tag(
name = "Installment Management",
description = "APIs for RD Installment Management"
)
public class InstallmentController {


private final InstallmentService installmentService;

public InstallmentController(
        InstallmentService installmentService) {

    this.installmentService = installmentService;
}

/*
 * SAVE INSTALLMENT
 */
@PreAuthorize("hasAnyRole('ADMIN','AGENT')")
@PostMapping("/save")
@Operation(
        summary = "Save Installment",
        description = "Creates a new installment"
)
public ResponseEntity<InstallmentEntity>
saveInstallment(

        @RequestBody InstallmentEntity installment) {

    InstallmentEntity savedInstallment =
            installmentService.saveInstallment(
                    installment);

    return ResponseEntity.ok(
            savedInstallment);
}

/*
 * GET ALL INSTALLMENTS
 */
@PreAuthorize("hasAnyRole('ADMIN','AGENT')")
@GetMapping("/all")
@Operation(
        summary = "Get All Installments",
        description = "Returns all installments"
)
public ResponseEntity<List<InstallmentEntity>>
getAllInstallments() {

    return ResponseEntity.ok(
            installmentService.getAllInstallments());
}

/*
 * GET INSTALLMENT BY ID
 */
@PreAuthorize("hasAnyRole('ADMIN','AGENT','CUSTOMER')")
@GetMapping("/{id}")
@Operation(
        summary = "Get Installment By ID",
        description = "Returns installment details by ID"
)
@ApiResponses({
        @ApiResponse(responseCode = "200",
                description = "Installment found"),
        @ApiResponse(responseCode = "404",
                description = "Installment not found")
})
public ResponseEntity<InstallmentEntity>
getInstallmentById(

        @Parameter(
                description = "Installment ID",
                example = "1")
        @PathVariable Long id) {

    InstallmentEntity installment =
            installmentService.getInstallmentById(id);
                 
    return ResponseEntity.ok(
            installment);
}

/*
 * UPDATE INSTALLMENT
 */
@PreAuthorize("hasAnyRole('ADMIN','AGENT')")
@PutMapping("/{id}")
@Operation(
        summary = "Update Installment",
        description = "Updates installment information"
)
public ResponseEntity<InstallmentEntity>
updateInstallment(

        @PathVariable Long id,

        @RequestBody InstallmentEntity installment) {

    installment.setInstallmentId(id);

    InstallmentEntity updatedInstallment =
            installmentService.updateInstallment(
                    installment);

    return ResponseEntity.ok(
            updatedInstallment);
}

/*
 * CUSTOMER INSTALLMENT SUMMARY
 */
@PreAuthorize("hasAnyRole('ADMIN','AGENT','CUSTOMER')")
@GetMapping("/customer/{userId}")
@Operation(
        summary = "Customer Installments",
        description = "Returns installment summary of customer"
)
public ResponseEntity<List<CustomerInstallment>>
getCustomerInstallments(

        @PathVariable Long userId) {

    return ResponseEntity.ok(
            installmentService
                    .getCustomerInstallments(
                            userId));
}

/*
 * DELETE INSTALLMENT
 */
@PreAuthorize("hasRole('ADMIN')")
@DeleteMapping("/{id}")
@Operation(
        summary = "Delete Installment",
        description = "Deletes installment by ID"
)
public ResponseEntity<String>
deleteInstallment(

        @PathVariable Long id) {

    installmentService.deleteInstallment(id);

    return ResponseEntity.ok(
            "Installment deleted successfully");
}


}
