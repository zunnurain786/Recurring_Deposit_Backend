package com.recurring_deposit.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.recurring_deposit.Entity.SchemeEntity;
import com.recurring_deposit.Repository.SchemeRepository;

@Service
public class SchemeService {

    private final SchemeRepository schemeRepository;

    public SchemeService(SchemeRepository schemeRepository) {
        this.schemeRepository = schemeRepository;
    }

    /*
     * SAVE SCHEME
     */
    public SchemeEntity saveScheme(SchemeEntity scheme) {

        if (scheme.getMinimumAmount()
                .compareTo(scheme.getMaximumAmount()) > 0) {

            throw new RuntimeException(
                    "Minimum amount cannot be greater than maximum amount");
        }

        return schemeRepository.save(scheme);
    }

    /*
     * GET ALL SCHEMES
     */
    public List<SchemeEntity> getAllSchemes() {
        return schemeRepository.findAll();
    }

    /*
     * GET ACTIVE SCHEMES
     */
    public List<SchemeEntity> getActiveSchemes() {
        return schemeRepository.findByActiveTrue();
    }

    /*
     * GET SCHEME BY ID
     */
    public SchemeEntity getSchemeById(Long id) {

        return schemeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Scheme not found"));
    }

    /*
     * UPDATE SCHEME
     */
    public SchemeEntity updateScheme(
            Long schemeId,
            SchemeEntity updatedScheme) {

        SchemeEntity existingScheme =
                getSchemeById(schemeId);

        existingScheme.setSchemeName(
                updatedScheme.getSchemeName());

        existingScheme.setDurationMonths(
                updatedScheme.getDurationMonths());

        existingScheme.setInterestRate(
                updatedScheme.getInterestRate());

        existingScheme.setMinimumAmount(
                updatedScheme.getMinimumAmount());

        existingScheme.setMaximumAmount(
                updatedScheme.getMaximumAmount());

        existingScheme.setActive(
                updatedScheme.getActive());

        return schemeRepository.save(existingScheme);
    }

    /*
     * DELETE SCHEME
     */
    public void deleteScheme(Long id) {

        SchemeEntity scheme =
                getSchemeById(id);

        schemeRepository.delete(scheme);
    }
}