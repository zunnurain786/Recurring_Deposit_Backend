package com.recurring_deposit.Service;

import org.springframework.stereotype.Service;

import com.recurring_deposit.DTO.AdminDashboard;
import com.recurring_deposit.Entity.AdminEntity;
import com.recurring_deposit.Entity.RdAccountStatus;
import com.recurring_deposit.Entity.Role;
import com.recurring_deposit.Entity.UserEntity;
import com.recurring_deposit.Repository.AdminRepository;
import com.recurring_deposit.Repository.AgentRepository;
import com.recurring_deposit.Repository.CustomerRepository;
import com.recurring_deposit.Repository.RdAccountRepository;
import com.recurring_deposit.Repository.SchemeRepository;
import com.recurring_deposit.Repository.UserRepository;

@Service
public class AdminService {

    private final UserRepository userRepository;
    private final AgentRepository agentRepository;
    private final CustomerRepository customerRepository;
    private final RdAccountRepository rdAccountRepository;
    private final SchemeRepository schemeRepository;
    private final AdminRepository adminRepository;
    

    public AdminService(
            UserRepository userRepository,
            AgentRepository agentRepository,
            CustomerRepository customerRepository,
            RdAccountRepository rdAccountRepository,
            SchemeRepository schemeRepository,
            AdminRepository adminRepository
            ) {

        this.userRepository = userRepository;
        this.agentRepository = agentRepository;
        this.customerRepository = customerRepository;
        this.rdAccountRepository = rdAccountRepository;
        this.schemeRepository = schemeRepository;
        this.adminRepository = adminRepository;
        
    }
    
    
    
    
    
public AdminEntity saveAdmin(AdminEntity admin) {

    Long userId = admin.getUser().getUserId();

    UserEntity user =
            userRepository.findById(userId)
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "User not found"));

    if (user.getRole() != Role.ADMIN) {

        throw new RuntimeException(
                "Selected user is not an ADMIN");
    }

    if (adminRepository.existsByUserUserId(userId)) {

        throw new RuntimeException(
                "Admin profile already exists");
    }

    // Generate unique Admin Code
    String adminCode;

    do {

        adminCode =
                "ADM-"
                        + Integer.toHexString(
                                (int) (Math.random() * 0xFFFFFF))
                        .toUpperCase();

    } while (
            adminRepository.existsByAdminCode(adminCode)
    );

    admin.setAdminCode(adminCode);

    admin.setUser(user);
    
    user.setProfileCompleted(true);
    
    userRepository.save(user);
    
    

    return adminRepository.save(admin);
}

    public AdminDashboard getAdminDashboard(
            Long userId) {
  

        UserEntity admin =
                userRepository.findById(userId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Admin not found"));

        if (admin.getRole() != Role.ADMIN) {

            throw new RuntimeException(
                    "Access denied");
        }

        AdminDashboard dashboard =
                new AdminDashboard();

        /*
         * ADMIN INFO
         */
        dashboard.setAdminName(
                admin.getFullName());
        
        AdminEntity adminEntity = 
        		adminRepository.findByUserUserId(userId);
        
        
        dashboard.setAdminCode(adminEntity.getAdminCode());
        					
        /*
         * USER COUNTS
         */
        dashboard.setTotalUsers(
                userRepository.count());

        dashboard.setTotalAgents(
                agentRepository.count());

        dashboard.setTotalCustomers(
                customerRepository.count());

        /*
         * SCHEME COUNT
         */
        dashboard.setTotalSchemes(
                schemeRepository.count());

        /*
         * RD ACCOUNT COUNT
         */
        dashboard.setActiveRdAccounts(
                rdAccountRepository.countByStatus(
                        RdAccountStatus.ACTIVE));

        dashboard.setClosedRdAccounts(
                rdAccountRepository.countByStatus(
                        RdAccountStatus.CLOSED));

        /*
         * COLLECTIONS
         * TODO: Replace with actual calculations
         */
        dashboard.setTodayCollection(0.0);
        dashboard.setMonthlyCollection(0.0);
        dashboard.setYearlyCollection(0.0);

        /*
         * PROFITS
         * TODO: Replace with actual calculations
         */
        dashboard.setTodayProfit(0.0);
        dashboard.setTotalProfit(0.0);

        /*
         * CUSTOMER ALERTS
         * TODO: Replace with actual calculations
         */
        dashboard.setDueCustomersToday(0L);
        dashboard.setMaturityCustomersToday(0L);

        return dashboard;
    }
}