package com.crm.module.lead.service;

import com.crm.exception.domain.ResourceNotFoundException;
import com.crm.exception.domain.UserNotFoundException;
import com.crm.module.company.model.Company;
import com.crm.module.company.repository.CompanyRepository;
import com.crm.module.contact.model.Contact;
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
import com.crm.user.appuser.model.User;
import com.crm.user.appuser.repository.UserRepository;
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

    private final UserRepository userRepository;

    private final ContactRepository contactRepository;
    private final CompanyRepository companyRepository;

    public SimpleLeadResponse createLead(CreateLeadRequest request) {

        User owner = userRepository.findById(request.getOwnerId()).orElseThrow(UserNotFoundException::new);

        Lead lead = new Lead(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPhone(),
                request.getCompany(),
                request.getDescription(),
                request.getAddress(),
                request.getSource(),
                owner
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

        User owner = null;
        if (request.getOwnerId() != null) {
            owner = userRepository.findById(request.getOwnerId())
                    .orElseThrow(UserNotFoundException::new);
        }

        lead.update(
                request.getFirstName(),
                lead.getLastName(),
                request.getEmail(),
                request.getPhone(),
                request.getCompany(),
                request.getDescription(),
                request.getAddress(),
                request.getSource(),
                owner
        );

         return leadMapper.fromLeadToSimpleLeadResponse(lead);
    }

    @Transactional
    public void delete(UUID id) {
        Lead lead = leadRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        leadRepository.delete(lead);
    }

    @Transactional
    public ConvertedLeadResponse convert(UUID id) {
        Lead lead = leadRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        Company company = companyRepository.findByName(lead.getCompany())
                .orElseGet(() -> {
                    Company newCompany = new Company(lead.getCompany(), lead.getOwner());
                    return companyRepository.save(newCompany);
                });

        Contact contact = new Contact(
                company,
                lead.getFirstName(),
                lead.getLastName(),
                lead.getEmail(),
                lead.getAddress(),
                lead.getPhone(),
                lead.getDescription(),
                lead.getOwner()
        );

        contactRepository.save(contact);

        lead.setAsConverted(contact);

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
