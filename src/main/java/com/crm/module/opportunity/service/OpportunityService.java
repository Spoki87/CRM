package com.crm.module.opportunity.service;

import com.crm.exception.domain.InvalidDataException;
import com.crm.exception.domain.ResourceNotFoundException;
import com.crm.module.company.model.Company;
import com.crm.module.company.repository.CompanyRepository;
import com.crm.module.contact.model.Contact;
import com.crm.module.contact.repository.ContactRepository;
import com.crm.module.opportunity.dto.request.CloseOpportunityRequest;
import com.crm.module.opportunity.dto.request.CreateOpportunityRequest;
import com.crm.module.opportunity.dto.request.UpdateOpportunityRequest;
import com.crm.module.opportunity.dto.response.OpportunityResponse;
import com.crm.module.opportunity.dto.response.SimpleOpportunityResponse;
import com.crm.module.opportunity.mapper.OpportunityMapper;
import com.crm.module.opportunity.model.Opportunity;
import com.crm.module.opportunity.model.OpportunityStage;
import com.crm.module.opportunity.repository.OpportunityRepository;
import com.crm.user.appuser.model.User;
import com.crm.user.appuser.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OpportunityService {
    private final ContactRepository contactRepository;

    private final UserRepository userRepository;
    private final OpportunityRepository opportunityRepository;
    private final CompanyRepository companyRepository;
    private final OpportunityMapper opportunityMapper;

    @Transactional
    public SimpleOpportunityResponse createOpportunity(CreateOpportunityRequest request) {

        Company company = getEntityById(Optional.ofNullable(request.getCompanyId()), companyRepository);
        Contact contact = getEntityById(Optional.ofNullable(request.getContactId()), contactRepository);
        User owner = getEntityById(Optional.ofNullable(request.getOwnerId()), userRepository);

        if (contact != null && company != null) {
            if (!Objects.equals(contact.getCompany().getId(), company.getId())) {
                throw new InvalidDataException("The contact is not related to the company");
            }
        }

        String generatedNumber = generateOpportunityNumber();

        Opportunity opportunity = new Opportunity(
                generatedNumber,
                request.getTitle(),
                request.getDescription(),
                request.getValue(),
                request.getDueDate(),
                company,
                contact,
                owner
        );

        opportunityRepository.save(opportunity);

        return opportunityMapper.fromOpportunityToSimpleOpportunityResponse(opportunity);
    }

    public OpportunityResponse getOpportunity(UUID id) {
        Opportunity opportunity = opportunityRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        return opportunityMapper.fromOpportunityToOpportunityResponse(opportunity);
    }

    public Page<OpportunityResponse> getOpportunities(Pageable pageable) {
        return opportunityRepository.findAll(pageable)
                .map(opportunityMapper::fromOpportunityToOpportunityResponse);
    }

    @Transactional
    public SimpleOpportunityResponse update(UUID id, UpdateOpportunityRequest request) {

        Company company = getEntityById(Optional.ofNullable(request.getCompanyId()), companyRepository);
        Contact contact = getEntityById(Optional.ofNullable(request.getContactId()), contactRepository);
        User owner = getEntityById(Optional.ofNullable(request.getOwnerId()), userRepository);

        Opportunity opportunity = opportunityRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        opportunity.update(
                request.getTitle(),
                request.getDescription(),
                request.getValue(),
                request.getDueDate(),
                company,
                contact,
                owner
        );

        return opportunityMapper.fromOpportunityToSimpleOpportunityResponse(opportunity);
    }

    @Transactional
    public SimpleOpportunityResponse closeOpportunity(UUID id, CloseOpportunityRequest request) {
        Opportunity opportunity = opportunityRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        opportunity.closeOpportunity(request.getClosedDate(),request.isClosedSuccessfully());

        return opportunityMapper.fromOpportunityToSimpleOpportunityResponse(opportunity);
    }

    public List<OpportunityStage> getAvailableTransitions(UUID id) {
        Opportunity opportunity = opportunityRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        return opportunity.getStage().getAvailableTransitions().stream().toList();

    }

    @Transactional
    public void transitionStage(UUID id, OpportunityStage stage) {
        Opportunity opportunity = opportunityRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        opportunity.transitionTo(stage);
    }

    @Transactional
    public void delete(UUID id) {
        Opportunity opportunity = opportunityRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        opportunityRepository.delete(opportunity);
    }

    private <T> T getEntityById(Optional<UUID> id, JpaRepository<T, UUID> repository) {
        return id.map(uuid -> repository.findById(uuid)
                        .orElseThrow(ResourceNotFoundException::new))
                .orElse(null);
    }

    private String generateOpportunityNumber() {
        long count = opportunityRepository.count();
        long nextNumber = count + 1;
        int currentYear = LocalDate.now().getYear();
        return String.format("O%03d/%d", nextNumber, currentYear);
    }


}
