package com.recurring_deposit.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.recurring_deposit.DTO.CustomerInstallment;
import com.recurring_deposit.DTO.Installment;
import com.recurring_deposit.Entity.InstallmentEntity;
import com.recurring_deposit.Entity.InstallmentStatus;
import com.recurring_deposit.Entity.RdAccountEntity;
import com.recurring_deposit.Repository.InstallmentRepository;
import com.recurring_deposit.Repository.RdAccountRepository;

@Service
public class InstallmentService {

    private final RdAccountRepository rdAccountRepository;
    private final InstallmentRepository installmentRepository;

    public InstallmentService(
            RdAccountRepository rdAccountRepository,
            InstallmentRepository installmentRepository) {

        this.rdAccountRepository = rdAccountRepository;
        this.installmentRepository = installmentRepository;
    }

    /*
     * SAVE INSTALLMENT
     */
    public InstallmentEntity saveInstallment(
            InstallmentEntity installment) {

        if (installment.getRdAccount() == null) {
            throw new RuntimeException(
                    "RD Account is required");
        }

        return installmentRepository.save(
                installment);
    }

    /*
     * GET ALL INSTALLMENTS
     */
    public List<InstallmentEntity> getAllInstallments() {

        return installmentRepository.findAll();
    }

    /*
     * GET INSTALLMENT BY ID
     */
    public InstallmentEntity getInstallmentById(
            Long id) {

        return installmentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Installment not found"));
    }

    /*
     * UPDATE INSTALLMENT
     */
    public InstallmentEntity updateInstallment(
            InstallmentEntity installment) {

        if (installment.getInstallmentId() == null) {
            throw new RuntimeException(
                    "Installment Id is required");
        }

        if (!installmentRepository.existsById(
                installment.getInstallmentId())) {

            throw new RuntimeException(
                    "Installment not found");
        }

        return installmentRepository.save(
                installment);
    }

    /*
     * GET CUSTOMER INSTALLMENTS
     */
    public List<CustomerInstallment> getCustomerInstallments(
            Long userId) {

        List<RdAccountEntity> accounts =
                rdAccountRepository.findByCustomerUserUserId(
                        userId);

        List<CustomerInstallment> result =
                new ArrayList<>();

        for (RdAccountEntity account : accounts) {

            CustomerInstallment dto =
                    new CustomerInstallment();

            dto.setRdAccountId(
                    account.getRdAccountId());

            dto.setAccountNumber(
                    account.getAccountNumber());

            dto.setSchemeName(
                    account.getScheme()
                            .getSchemeName());

            dto.setMonthlyDepositAmount(
                    account.getMonthlyDepositAmount());

            dto.setDurationMonths(
                    account.getScheme()
                            .getDurationMonths());

            dto.setStartDate(
                    account.getStartDate());

            dto.setMaturityDate(
                    account.getMaturityDate());

            dto.setMaturityAmount(
                    account.getMaturityAmount());

            List<InstallmentEntity> installmentEntities =
                    installmentRepository
                            .findByRdAccountRdAccountId(
                                    account.getRdAccountId());

            List<Installment> installmentDtos =
                    new ArrayList<>();

            for (InstallmentEntity entity :
                    installmentEntities) {

                Installment installmentDto =
                        new Installment();

                installmentDto.setInstallmentNumber(
                        entity.getInstallmentNumber());

                installmentDto.setInstallmentAmount(
                        entity.getAmountPaid());

                installmentDto.setDueDate(
                        entity.getPaymentDate());

                installmentDto.setStatus(
                        entity.getStatus().name());

                installmentDtos.add(
                        installmentDto);
            }

            dto.setInstallments(
                    installmentDtos);

            int paidInstallments =
                    (int) installmentEntities
                            .stream()
                            .filter(i ->
                                    i.getStatus() ==
                                            InstallmentStatus.PAID)
                            .count();

            int totalInstallments =
                    account.getScheme()
                            .getDurationMonths();

            dto.setPaidInstallments(
                    paidInstallments);

            dto.setPendingInstallments(
                    Math.max(
                            totalInstallments -
                                    paidInstallments,
                            0));

            result.add(dto);
        }

        return result;
    }

    /*
     * DELETE INSTALLMENT
     */
    public void deleteInstallment(
            Long id) {

        if (!installmentRepository.existsById(
                id)) {

            throw new RuntimeException(
                    "Installment not found");
        }

        installmentRepository.deleteById(
                id);
    }
}