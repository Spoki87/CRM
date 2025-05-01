package com.crm.module.lead.mapper;

import com.crm.module.lead.dto.response.ConvertedLeadResponse;
import com.crm.module.lead.dto.response.LeadResponse;
import com.crm.module.lead.dto.response.SimpleLeadResponse;
import com.crm.module.lead.model.Lead;
import org.springframework.stereotype.Component;

@Component
public class LeadMapper {

    public LeadResponse fromLeadToLeadResponse(Lead lead) {
        return new LeadResponse(
                lead.getId(),
                lead.getFirstName(),
                lead.getLastName(),
                lead.getFirstName()+","+lead.getLastName(),
                lead.getEmail(),
                lead.getPhone(),
                lead.getCompany(),
                lead.getStatus(),
                lead.getSource(),
                lead.getAddress(),
                lead.getDescription(),
                lead.isConverted(),
                lead.getContactId()
        );
    }

    public SimpleLeadResponse fromLeadToSimpleLeadResponse(Lead lead) {
        return new SimpleLeadResponse(
                lead.getId(),
                lead.getFirstName()+" "+lead.getLastName()
        );
    }

    public ConvertedLeadResponse fromLeadToConvertedLeadResponse(Lead lead) {
        return new ConvertedLeadResponse(
                lead.isConverted(),
                lead.getContact().getId()
        );
    }
}
