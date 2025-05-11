package com.crm.module.activity.service;

import com.crm.module.activity.model.RelatedModule;
import com.crm.module.company.repository.CompanyRepository;
import com.crm.module.contact.repository.ContactRepository;
import com.crm.module.lead.repository.LeadRepository;
import com.crm.module.opportunity.repository.OpportunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final LeadRepository leadRepository;
    private final ContactRepository contactRepository;
    private final CompanyRepository companyRepository;
    private final OpportunityRepository opportunityRepository;

    public Optional<?> getRelatedEntity(RelatedModule module, UUID relatedId) {
        return switch (module) {
            case LEAD -> leadRepository.findById(relatedId);
            case CONTACT -> contactRepository.findById(relatedId);
            case OPPORTUNITY -> opportunityRepository.findById(relatedId);
            case COMPANY -> companyRepository.findById(relatedId);
            default -> throw new IllegalArgumentException("Unsupported module: " + module);
        };
    }
}
