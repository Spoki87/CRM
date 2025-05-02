package com.crm.module.opportunity.repository;

import com.crm.module.opportunity.model.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public interface OpportunityRepository extends JpaRepository<Opportunity, UUID> {
}
