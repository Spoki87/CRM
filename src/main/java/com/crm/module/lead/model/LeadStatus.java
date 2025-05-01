package com.crm.module.lead.model;

import java.util.Map;
import java.util.Set;

public enum LeadStatus {
    NEW,
    IN_PROGRESS,
    CONTACTED,
    LOST,
    CONVERTED;

    private static final Map<LeadStatus, Set<LeadStatus>> transitions = Map.of(
            NEW, Set.of(CONTACTED, IN_PROGRESS),
            IN_PROGRESS, Set.of(CONVERTED, LOST),
            CONTACTED, Set.of(IN_PROGRESS, LOST),
            LOST, Set.of(),
            CONVERTED, Set.of()
    );

    public boolean canTransitionTo(LeadStatus next) {
        return transitions.getOrDefault(this, Set.of()).contains(next);
    }

    public Set<LeadStatus> getAvailableTransitions() {
        return transitions.getOrDefault(this, Set.of());
    }

}
