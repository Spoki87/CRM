package com.crm.module.company.service;

import com.crm.exception.domain.ResourceNotFoundException;
import com.crm.exception.domain.UserNotFoundException;
import com.crm.module.company.dto.request.CreateCompanyRequest;
import com.crm.module.company.dto.request.UpdateCompanyRequest;
import com.crm.module.company.dto.response.CompanyResponse;
import com.crm.module.company.dto.response.SimpleCompanyResponse;
import com.crm.module.company.mapper.CompanyMapper;
import com.crm.module.company.model.Company;
import com.crm.module.company.repository.CompanyRepository;
import com.crm.user.appuser.model.User;
import com.crm.user.appuser.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    private final UserRepository userRepository;

    public SimpleCompanyResponse createCompany(CreateCompanyRequest request) {

        User owner = userRepository.findById(request.getOwnerId()).orElseThrow(UserNotFoundException::new);

        Company company = new Company(
                request.getName(),
                request.getEmail(),
                request.getPhone(),
                request.getTaxIdentificationNumber(),
                request.getType(),
                request.getIndustry(),
                request.getWebsite(),
                request.getNumberOfEmployees(),
                request.getDescription(),
                request.getBillingAddress(),
                request.getShippingAddress(),
                owner
        );
        companyRepository.save(company);

        return companyMapper.fromCompanyToSimpleCompanyResponse(company);
    }

    public CompanyResponse getCompany(UUID id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        return companyMapper.fromCompanyToCompanyResponse(company);
    }

    public Page<CompanyResponse> getCompanies(Pageable pageable) {
        return companyRepository.findAll(pageable)
                .map(companyMapper::fromCompanyToCompanyResponse);
    }

    @Transactional
    public SimpleCompanyResponse update(UUID id, UpdateCompanyRequest request) {
        Company company = companyRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        User owner = userRepository.findById(request.getOwnerId()).orElseThrow(UserNotFoundException::new);

        company.update(
                request.getName(),
                request.getEmail(),
                request.getPhone(),
                request.getTaxIdentificationNumber(),
                request.getType(),
                request.getIndustry(),
                request.getWebsite(),
                request.getNumberOfEmployees(),
                request.getDescription(),
                request.getBillingAddress(),
                request.getShippingAddress(),
                owner
        );

        return companyMapper.fromCompanyToSimpleCompanyResponse(company);
    }

    @Transactional
    public void delete(UUID id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        companyRepository.delete(company);
    }
}
