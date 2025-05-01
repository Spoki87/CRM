package com.crm.module.company.mapper;

import com.crm.module.company.dto.response.CompanyResponse;
import com.crm.module.company.dto.response.SimpleCompanyResponse;
import com.crm.module.company.model.Company;
import com.crm.module.contact.mapper.ContactMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompanyMapper {

    private final ContactMapper contactMapper;


    public CompanyResponse fromCompanyToCompanyResponse(Company company) {
        return new CompanyResponse(
                company.getId(),
                company.getName(),
                company.getEmail(),
                company.getPhone(),
                company.getTaxIdentificationNumber(),
                company.getType(),
                company.getIndustry(),
                company.getWebsite(),
                company.getNumberOfEmployees(),
                company.getDescription(),
                company.getBillingAddress(),
                company.getShippingAddress(),
                company.getContacts().stream().map(contactMapper::fromContactToSimpleContactResponse).toList(),
                company.getOwner().getId(),
                company.getCreatedTime(),
                company.getUpdatedTime(),
                company.getCreatedBy().getId(),
                company.getUpdatedBy().getId()
        );
    }

    public SimpleCompanyResponse fromCompanyToSimpleCompanyResponse(Company company) {
        return new SimpleCompanyResponse(
                company.getId(),
                company.getName()
        );
    }
}
