package com.crm.module.company.controller;

import com.crm.api.Response;
import com.crm.module.company.dto.request.CreateCompanyRequest;
import com.crm.module.company.dto.request.UpdateCompanyRequest;
import com.crm.module.company.dto.response.CompanyResponse;
import com.crm.module.company.dto.response.SimpleCompanyResponse;
import com.crm.module.company.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
@Validated
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping()
    public ResponseEntity<Response> createCompany(@Validated @RequestBody CreateCompanyRequest request) {
        SimpleCompanyResponse response = companyService.createCompany(request);
        return ResponseEntity.ok(Response.success("Created successfully", response, HttpStatus.CREATED));
    }

    @GetMapping("/{id}")
    ResponseEntity<Response> getCompanyById(@PathVariable UUID id) {
        CompanyResponse response = companyService.getCompany(id);
        return ResponseEntity.ok(Response.success("Get successfully", response, HttpStatus.OK));
    }

    @GetMapping()
    ResponseEntity<Response> getCompanies(@RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 200);
        Page<CompanyResponse> response = companyService.getCompanies(pageable);
        return ResponseEntity.ok(Response.success("Retrieved successfully", response, HttpStatus.OK));
    }

    @PutMapping("/{id}")
    ResponseEntity<Response> updateCompany(@Valid @RequestBody UpdateCompanyRequest request, @PathVariable UUID id) {
        SimpleCompanyResponse response = companyService.update(id, request);
        return ResponseEntity.ok(Response.success("Updated successfully", response, HttpStatus.OK));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Response> deleteCompany(@PathVariable UUID id) {
        companyService.delete(id);
        return ResponseEntity.ok(Response.success("Deleted successfully", null, HttpStatus.OK));
    }

}

