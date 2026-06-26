package com.recurring_deposit.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.recurring_deposit.DTO.AdminDashboard;
import com.recurring_deposit.Entity.AdminEntity;
import com.recurring_deposit.Service.AdminService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/admins")
@Tag(
        name = "Admin Management",
        description = "Admin Dashboard APIs"
)
public class AdminController {

    private final AdminService adminService;

    public AdminController(
            AdminService adminService) {

        this.adminService = adminService;
    }
    
    @Operation(summary = "save Admin",
    		description = "save Admin Using profile"
    )
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "successfully data saved"
        ),
        @ApiResponse(
                responseCode = "403",
                description = "Access denied"
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Admin not found"
        )
})
@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/save")
    public ResponseEntity<AdminEntity> saveAdmin(
            @RequestBody AdminEntity admin) {

        return ResponseEntity.ok(
                adminService.saveAdmin(admin));
    }

    /*
     * ADMIN DASHBOARD
     */
    @Operation(
            summary = "Get Admin Dashboard",
            description = "Fetch complete admin dashboard information including users, customers, agents, schemes, RD accounts, collections and profits"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Dashboard fetched successfully"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Access denied"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Admin not found"
            )
    })
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/dashboard/{userId}")
    public ResponseEntity<AdminDashboard> getDashboard(
            @PathVariable Long userId) {

        AdminDashboard dashboard =
                adminService.getAdminDashboard(userId);

        return ResponseEntity.ok(
                dashboard);
    }
}