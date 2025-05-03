package com.crm.module.opportunity.controller;

import com.crm.api.Response;
import com.crm.module.opportunity.dto.request.CloseOpportunityRequest;
import com.crm.module.opportunity.dto.request.CreateOpportunityRequest;
import com.crm.module.opportunity.dto.request.UpdateOpportunityRequest;
import com.crm.module.opportunity.dto.response.OpportunityResponse;
import com.crm.module.opportunity.dto.response.SimpleOpportunityResponse;
import com.crm.module.opportunity.model.OpportunityStage;
import com.crm.module.opportunity.service.OpportunityService;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/api/opportunities")
@RequiredArgsConstructor
@Validated
public class OpportunityController {

    private final OpportunityService opportunityService;

    @PostMapping()
    public ResponseEntity<Response<SimpleOpportunityResponse>> createOpportunity(@Validated @RequestBody CreateOpportunityRequest request){
        SimpleOpportunityResponse response = opportunityService.createOpportunity(request);
        return ResponseEntity.ok(Response.success("Created successfully",response, HttpStatus.CREATED));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<OpportunityResponse>> getOpportunityById(@PathVariable UUID id){
        OpportunityResponse response = opportunityService.getOpportunity(id);
        return ResponseEntity.ok(Response.success("Retrieved successfully",response, HttpStatus.OK));
    }

    @GetMapping()
    ResponseEntity<Response<Page<OpportunityResponse>>> getOpportunities(@RequestParam(defaultValue = "0") int page){
        Pageable pageable = PageRequest.of(page, 200);
        Page<OpportunityResponse> response = opportunityService.getOpportunities(pageable);
        return ResponseEntity.ok(Response.success("Retrieved successfully",response, HttpStatus.OK));
    }

    @PutMapping("/{id}")
    ResponseEntity<Response<SimpleOpportunityResponse>> updateOpportunity(@PathVariable UUID id, @RequestBody UpdateOpportunityRequest request){
        SimpleOpportunityResponse response = opportunityService.update(id,request);
        return ResponseEntity.ok(Response.success("Updated successfully",response, HttpStatus.OK));
    }

    @GetMapping("/transitions/{id}")
    ResponseEntity<Response<List<OpportunityStage>>> getAvailableTransitions(@PathVariable UUID id){
        List<OpportunityStage> response = opportunityService.getAvailableTransitions(id);
        return ResponseEntity.ok(Response.success("Retrieved successfully",response, HttpStatus.OK));
    }

    @PostMapping("/transition/{id}")
    ResponseEntity<Response<Void>> stageTransition(@PathVariable UUID id, @RequestParam OpportunityStage stage){
        opportunityService.transitionStage(id,stage);
        return ResponseEntity.ok(Response.success("Stage transition successfully",null, HttpStatus.OK));
    }

    @PostMapping("/close/{id}")
    ResponseEntity<Response<SimpleOpportunityResponse>> close(@PathVariable UUID id, @RequestBody CloseOpportunityRequest request){
        SimpleOpportunityResponse response = opportunityService.closeOpportunity(id,request);
        return ResponseEntity.ok(Response.success("Closed successfully",response, HttpStatus.OK));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Response<Void>> delete(@PathVariable UUID id){
        opportunityService.delete(id);
        return ResponseEntity.ok(Response.success("Deleted successfully", null, HttpStatus.OK));
    }

}
