package com.crm.module.opportunity.model;

import java.util.Map;
import java.util.Set;

public enum OpportunityStage {
    NEW, QUALIFIED, PROPOSAL_SENT, WON, LOST;

    private static final Map<OpportunityStage, Set<OpportunityStage>> transitions = Map.of(
            NEW, Set.of(QUALIFIED, PROPOSAL_SENT),
            QUALIFIED, Set.of(PROPOSAL_SENT, WON,LOST),
            PROPOSAL_SENT, Set.of(WON, LOST),
            LOST, Set.of(),
            WON, Set.of()
    );

    public boolean canTransitionTo(OpportunityStage next) {
        return transitions.getOrDefault(this, Set.of()).contains(next);
    }

    public Set<OpportunityStage> getAvailableTransitions() {
        return transitions.getOrDefault(this, Set.of());
    }
}
