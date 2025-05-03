package com.crm.module.opportunity.mapper;

import com.crm.module.opportunity.dto.response.OpportunityResponse;
import com.crm.module.opportunity.dto.response.SimpleOpportunityResponse;
import com.crm.module.opportunity.model.Opportunity;
import org.springframework.stereotype.Component;

@Component
public class OpportunityMapper {
    public OpportunityResponse fromOpportunityToOpportunityResponse(Opportunity opportunity){
        return new OpportunityResponse(
            opportunity.getId(),
            opportunity.getTitle(),
            opportunity.getNumber(),
                opportunity.getDescription(),
                opportunity.getValue(),
                opportunity.getDueDate(),
                opportunity.getClosedDate(),
                opportunity.getStage(),
                opportunity.getContactId(),
                opportunity.getCompanyId(),
                opportunity.getOwnerId(),
                opportunity.getCreatedTime(),
                opportunity.getUpdatedTime(),
                opportunity.getCreatedBy().getId(),
                opportunity.getUpdatedBy().getId()
        );
    }

    public SimpleOpportunityResponse fromOpportunityToSimpleOpportunityResponse(Opportunity opportunity){
        return new SimpleOpportunityResponse(
                opportunity.getId(),
                opportunity.getTitle()
        );
    }
}
