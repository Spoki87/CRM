package com.crm.module.lead.service;

import com.crm.exception.domain.ResourceNotFoundException;
import com.crm.module.company.repository.CompanyRepository;
import com.crm.module.contact.repository.ContactRepository;
import com.crm.module.lead.dto.request.CreateLeadRequest;
import com.crm.module.lead.dto.request.UpdateLeadRequest;
import com.crm.module.lead.dto.response.ConvertedLeadResponse;
import com.crm.module.lead.dto.response.LeadResponse;
import com.crm.module.lead.dto.response.SimpleLeadResponse;
import com.crm.module.lead.mapper.LeadMapper;
import com.crm.module.lead.model.Lead;
import com.crm.module.lead.model.LeadStatus;
import com.crm.module.lead.repository.LeadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LeadService {

    private final LeadRepository leadRepository;
    private final LeadMapper leadMapper;

    private final ContactRepository contactRepository;
    private final CompanyRepository companyRepository;

    public SimpleLeadResponse createLead(CreateLeadRequest request) {
        Lead lead = new Lead(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPhone(),
                request.getCompany(),
                request.getDescription(),
                request.getAddress(),
                request.getSource()
        );
        leadRepository.save(lead);
        return leadMapper.fromLeadToSimpleLeadResponse(lead);
    }

    public LeadResponse getLead(UUID id) {
        Lead lead = leadRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        return leadMapper.fromLeadToLeadResponse(lead);
    }

    public Page<LeadResponse> getLeads(Pageable pageable) {
        return leadRepository.findAll(pageable)
                .map(leadMapper::fromLeadToLeadResponse);
    }

    @Transactional
    public SimpleLeadResponse updateLead(UUID id, UpdateLeadRequest request) {
        Lead lead = leadRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        lead.update(
                request.getFirstName(),
                lead.getLastName(),
                request.getEmail(),
                request.getPhone(),
                request.getCompany(),
                request.getDescription(),
                request.getAddress(),
                request.getSource()
        );

         return leadMapper.fromLeadToSimpleLeadResponse(lead);
    }

    @Transactional
    public void delete(UUID id) {
        Lead lead = leadRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        leadRepository.delete(lead);
    }

    public ConvertedLeadResponse convert(UUID id) {
        Lead lead = leadRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        /*
            to do
            create contact
            create company
            update lead
        */

        return leadMapper.fromLeadToConvertedLeadResponse(lead);
    }

    public List<LeadStatus> getAvailableTransitions(UUID id) {
        Lead lead = leadRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        return lead.getStatus().getAvailableTransitions().stream().toList();
    }

    @Transactional
    public void statusTransition(UUID id, LeadStatus status) {
        Lead lead = leadRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        lead.transitionTo(status);
        leadRepository.save(lead);
    }
}
