package com.crm.module.contact.mapper;

import com.crm.module.contact.dto.response.ContactResponse;
import com.crm.module.contact.dto.response.SimpleContactResponse;
import com.crm.module.contact.model.Contact;
import org.springframework.stereotype.Component;

@Component
public class ContactMapper {
    public SimpleContactResponse fromContactToSimpleContactResponse(Contact contact) {
        return new SimpleContactResponse(
                contact.getId(),
                contact.getFirstName()+" "+contact.getLastName()
        );
    }

    public ContactResponse fromContactToContactResponse(Contact contact) {
        return new ContactResponse(
            contact.getId(),
                contact.getCompanyId(),
                contact.getFirstName(),
                contact.getLastName(),
                contact.getPhone(),
                contact.getEmail(),
                contact.getAddress(),
                contact.getDescription(),
                contact.getOwnerId(),
                contact.getCreatedTime(),
                contact.getUpdatedTime(),
                contact.getCreatedBy().getId(),
                contact.getUpdatedBy().getId()
        );
    }
}
