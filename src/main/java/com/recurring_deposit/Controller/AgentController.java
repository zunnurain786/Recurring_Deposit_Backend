package com.recurring_deposit.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.recurring_deposit.DTO.AgentDashboard;
import com.recurring_deposit.Entity.AgentEntity;
import com.recurring_deposit.Service.AgentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/agents")
@Tag(
name = "Agent Management",
description = "Agent related APIs"
)
public class AgentController {


private final AgentService agentService;

public AgentController(
        AgentService agentService) {

    this.agentService = agentService;
}

/*
 * CREATE AGENT
 */
@Operation(
        summary = "Create Agent Profile",
        description = "Create a new agent profile for an existing user"
)
@ApiResponses({
        @ApiResponse(responseCode = "201", description = "Agent created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid request"),
        @ApiResponse(responseCode = "404", description = "User not found")
})
@PostMapping("/save")
public ResponseEntity<AgentEntity> saveAgent(
        @RequestBody AgentEntity agent) {

    AgentEntity savedAgent =
            agentService.saveAgent(agent);

    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(savedAgent);
}

/*
 * GET ALL AGENTS
 */
@Operation(
        summary = "Get All Agents",
        description = "Fetch all agent records"
)
@PreAuthorize("hasRole('ADMIN')")
@GetMapping("/all")
public ResponseEntity<List<AgentEntity>> getAllAgents() {

    return ResponseEntity.ok(
            agentService.getAllAgents());
}

/*
 * GET AGENT BY ID
 */
@Operation(
        summary = "Get Agent By Id",
        description = "Fetch agent details using agent id"
)
@PreAuthorize("hasAnyRole('ADMIN','AGENT')")
@GetMapping("/{id}")
public ResponseEntity<AgentEntity> getAgentById(
        @PathVariable Long id) {

    AgentEntity agent =
            agentService.getAgentById(id)
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "Agent not found"));

    return ResponseEntity.ok(agent);
}

/*
 * UPDATE AGENT
 */
@Operation(
        summary = "Update Agent",
        description = "Update existing agent details"
)
@PreAuthorize("hasRole('ADMIN')")
@PutMapping("/update")
public ResponseEntity<AgentEntity> updateAgent(
        @RequestBody AgentEntity agent) {

    AgentEntity updatedAgent =
            agentService.updateAgent(agent);

    return ResponseEntity.ok(
            updatedAgent);
}

/*
 * DELETE AGENT
 */
@Operation(
        summary = "Delete Agent",
        description = "Delete agent by id"
)
@PreAuthorize("hasRole('ADMIN')")
@DeleteMapping("/{id}")
public ResponseEntity<String> deleteAgent(
        @PathVariable Long id) {

    agentService.deleteAgent(id);

    return ResponseEntity.ok(
            "Agent deleted successfully");
}

/*
 * AGENT DASHBOARD
 */
@Operation(
        summary = "Get Agent Dashboard",
        description = "Fetch dashboard details of an agent using user id"
)
@PreAuthorize("hasAnyRole('ADMIN','AGENT')")
@GetMapping("/dashboard/user/{userId}")
public ResponseEntity<AgentDashboard>
getDashboardByUserId(
        @PathVariable Long userId) {

    AgentDashboard dashboard =
            agentService.getAgentDashboardByUserId(
                    userId);

    return ResponseEntity.ok(
            dashboard);
}


}
