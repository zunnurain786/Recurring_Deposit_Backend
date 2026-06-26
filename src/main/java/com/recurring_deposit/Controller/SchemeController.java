package com.recurring_deposit.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.recurring_deposit.Entity.SchemeEntity;
import com.recurring_deposit.Service.SchemeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/schemes")
@Tag(
        name = "Scheme Management",
        description = "APIs for managing Recurring Deposit Schemes"
)
public class SchemeController {

    private final SchemeService schemeService;

    public SchemeController(
            SchemeService schemeService) {

        this.schemeService = schemeService;
    }

    /*
     * CREATE SCHEME
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/save")
    @Operation(
            summary = "Create Scheme",
            description = "Create a new recurring deposit scheme"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Scheme created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public ResponseEntity<SchemeEntity> saveScheme(
            @RequestBody SchemeEntity scheme) {

        SchemeEntity savedScheme =
                schemeService.saveScheme(scheme);

        return ResponseEntity.ok(savedScheme);
    }

    /*
     * GET ALL SCHEMES
     */
    @GetMapping("/all")
    @Operation(
            summary = "Get All Schemes",
            description = "Fetch all RD schemes"
    )
    @ApiResponse(responseCode = "200", description = "Schemes fetched successfully")
    public ResponseEntity<List<SchemeEntity>> getAllSchemes() {

        return ResponseEntity.ok(
                schemeService.getAllSchemes());
    }

    /*
     * GET SCHEME BY ID
     */
    @GetMapping("/{id}")
    @Operation(
            summary = "Get Scheme By ID",
            description = "Fetch a scheme using scheme id"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Scheme found"),
            @ApiResponse(responseCode = "404", description = "Scheme not found")
    })
    public ResponseEntity<SchemeEntity> getSchemeById(

            @Parameter(
                    description = "Scheme ID",
                    example = "1")
            @PathVariable Long id) {

        SchemeEntity scheme =
                schemeService.getSchemeById(id);
        return ResponseEntity.ok(scheme);
    }

    /*
     * UPDATE SCHEME
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    @Operation(
            summary = "Update Scheme",
            description = "Update existing RD scheme"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Scheme updated successfully"),
            @ApiResponse(responseCode = "404", description = "Scheme not found")
    })
    public ResponseEntity<SchemeEntity> updateScheme(

            @PathVariable Long id,

            @RequestBody SchemeEntity scheme) {

        SchemeEntity updatedScheme =
                schemeService.updateScheme(id ,scheme);

        return ResponseEntity.ok(updatedScheme);
    }

    /*
     * DELETE SCHEME
     */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete Scheme",
            description = "Delete scheme by id"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Scheme deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Scheme not found")
    })
    public ResponseEntity<String> deleteScheme(

            @Parameter(
                    description = "Scheme ID",
                    example = "1")
            @PathVariable Long id) {

        schemeService.deleteScheme(id);

        return ResponseEntity.ok(
                "Scheme deleted successfully");
    }
}