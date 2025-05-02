package com.crm.module.contact.service;

import com.crm.exception.domain.ResourceNotFoundException;
import com.crm.module.company.model.Company;
import com.crm.module.company.repository.CompanyRepository;
import com.crm.module.contact.dto.request.CreateContactRequest;
import com.crm.module.contact.dto.request.UpdateContactRequest;
import com.crm.module.contact.dto.response.ContactResponse;
import com.crm.module.contact.dto.response.SimpleContactResponse;
import com.crm.module.contact.mapper.ContactMapper;
import com.crm.module.contact.model.Contact;
import com.crm.module.contact.repository.ContactRepository;
import com.crm.module.lead.dto.response.SimpleLeadResponse;
import com.crm.user.appuser.model.User;
import com.crm.user.appuser.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContactService {
    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;


    @Transactional
    public SimpleContactResponse createContact(CreateContactRequest request) {

        Company company = getEntityById(Optional.ofNullable(request.getCompanyId()), companyRepository);
        User owner = getEntityById(Optional.ofNullable(request.getOwnerId()), userRepository);

        Contact contact = new Contact(
                company,
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getAddress(),
                request.getPhone(),
                request.getDescription(),
                owner
        );

        contactRepository.save(contact);

        return contactMapper.fromContactToSimpleContactResponse(contact);
    }

    public ContactResponse getContact(UUID id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        return contactMapper.fromContactToContactResponse(contact);
    }

    public Page<ContactResponse> getContacts(Pageable pageable) {
        return contactRepository.findAll(pageable)
                .map(contactMapper::fromContactToContactResponse);
    }

    @Transactional
    public SimpleContactResponse updateContact(UUID id, UpdateContactRequest request) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        Company company = getEntityById(Optional.ofNullable(request.getCompanyId()), companyRepository);
        User owner = getEntityById(Optional.ofNullable(request.getOwnerId()), userRepository);

        contact.update(
            company,
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getAddress(),
                request.getPhone(),
                request.getDescription(),
                owner
        );
        return contactMapper.fromContactToSimpleContactResponse(contact);
    }

    @Transactional
    public void deleteContact(UUID id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        contactRepository.delete(contact);
    }

    private <T> T getEntityById(Optional<UUID> id, JpaRepository<T, UUID> repository) {
        return id.map(uuid -> repository.findById(uuid)
                        .orElseThrow(ResourceNotFoundException::new))
                .orElse(null);
    }

}
