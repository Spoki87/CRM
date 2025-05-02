package com.crm.module.contact.controller;

import com.crm.api.Response;
import com.crm.module.contact.dto.request.CreateContactRequest;
import com.crm.module.contact.dto.request.UpdateContactRequest;
import com.crm.module.contact.dto.response.ContactResponse;
import com.crm.module.contact.dto.response.SimpleContactResponse;
import com.crm.module.contact.service.ContactService;
import com.crm.module.lead.dto.request.UpdateLeadRequest;
import com.crm.module.lead.dto.response.LeadResponse;
import com.crm.module.lead.dto.response.SimpleLeadResponse;
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
@RequestMapping("/api/contacts")
@RequiredArgsConstructor
@Validated
public class ContactController {

    private final ContactService contactService;

    @PostMapping()
    ResponseEntity<Response<SimpleContactResponse>> createContact(@Valid @RequestBody CreateContactRequest request){
        SimpleContactResponse response = contactService.createContact(request);
        return ResponseEntity.ok(Response.success("Created successfully",response, HttpStatus.CREATED));
    }

    @GetMapping("/{id}")
    ResponseEntity<Response<ContactResponse>> getContact(@PathVariable UUID id){
        ContactResponse response = contactService.getContact(id);
        return ResponseEntity.ok(Response.success("Retrieved successfully",response, HttpStatus.OK));
    }

    @GetMapping()
    ResponseEntity<Response<Page<ContactResponse>>> getContacts(@RequestParam(defaultValue = "0") int page){
        Pageable pageable = PageRequest.of(page, 200);
        Page<ContactResponse> response = contactService.getContacts(pageable);
        return ResponseEntity.ok(Response.success("Retrieved successfully",response, HttpStatus.OK));
    }

    @PutMapping("/{id}")
    ResponseEntity<Response<SimpleContactResponse>> updateContact(@Valid @RequestBody UpdateContactRequest request, @PathVariable UUID id){
        SimpleContactResponse response = contactService.updateContact(id, request);
        return ResponseEntity.ok(Response.success("Updated successfully",response, HttpStatus.OK));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Response<Void>> deleteContact(@PathVariable UUID id){
        contactService.deleteContact(id);
        return ResponseEntity.ok(Response.success("Deleted successfully",null,HttpStatus.OK));
    }
}
