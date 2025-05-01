package com.crm.module.lead.controller;

import com.crm.api.Response;
import com.crm.module.lead.dto.request.CreateLeadRequest;
import com.crm.module.lead.dto.request.UpdateLeadRequest;
import com.crm.module.lead.dto.response.ConvertedLeadResponse;
import com.crm.module.lead.dto.response.LeadResponse;
import com.crm.module.lead.dto.response.SimpleLeadResponse;
import com.crm.module.lead.model.LeadStatus;
import com.crm.module.lead.service.LeadService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/leads")
@RequiredArgsConstructor
@Validated
public class LeadController {

    private final LeadService leadService;

    //create
    @PostMapping()
    ResponseEntity<Response<SimpleLeadResponse>> createLead(@Valid CreateLeadRequest request){
        SimpleLeadResponse response = leadService.createLead(request);
        return ResponseEntity.ok(Response.success("Created successfully",response, HttpStatus.CREATED));
    }
    //get
    @GetMapping("/{id}")
    ResponseEntity<Response<LeadResponse>> getLeadById(@PathVariable UUID id){
        LeadResponse response = leadService.getLead(id);
        return ResponseEntity.ok(Response.success("Retrieved successfully",response, HttpStatus.OK));
    }

    //get page
    @GetMapping()
    ResponseEntity<Response<Page<LeadResponse>>> getLeads(@RequestParam(defaultValue = "0") int page){
        Pageable pageable = PageRequest.of(page, 200);
        Page<LeadResponse> response = leadService.getLeads(pageable);
        return ResponseEntity.ok(Response.success("Retrieved successfully",response, HttpStatus.OK));
    }

    //update
    @PutMapping("/{id}")
    ResponseEntity<Response<SimpleLeadResponse>> updateLead(@Valid UpdateLeadRequest request, @PathVariable UUID id){
        SimpleLeadResponse response = leadService.updateLead(id, request);
        return ResponseEntity.ok(Response.success("Updated successfully",response, HttpStatus.OK));
    }

    //delete
    @DeleteMapping("/{id}")
    ResponseEntity<Response<String>> deleteLead(@PathVariable UUID id){
        leadService.delete(id);
        return ResponseEntity.ok(Response.success("Deleted successfully",null,HttpStatus.OK));
    }

    //convert
    @PostMapping("/convert/{id}")
    ResponseEntity<Response<ConvertedLeadResponse>> convertLead(@PathVariable UUID id){
        ConvertedLeadResponse response = leadService.convert(id);
        return ResponseEntity.ok(Response.success("Converted successfully",response, HttpStatus.OK));
    }

    //get status transitions
    @GetMapping("/transitions/{id}")
    ResponseEntity<Response<List<LeadStatus>>> getAvailableTransitions(@PathVariable UUID id){
        List<LeadStatus> response = leadService.getAvailableTransitions(id);
        return ResponseEntity.ok(Response.success("Retrieved successfully",response, HttpStatus.OK));
    }

    //status transition
    @PostMapping("transition/{id}")
    ResponseEntity<Response<String>> statusTransition(@PathVariable UUID id,@RequestParam LeadStatus status){
        leadService.statusTransition(id,status);
        return ResponseEntity.ok(Response.success("Status transition successfully",null,HttpStatus.OK));
    }
}
