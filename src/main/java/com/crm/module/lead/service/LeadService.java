package com.crm.module.lead.service;

import com.crm.module.lead.repository.LeadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LeadService {

    private final LeadRepository leadRepository;
}
