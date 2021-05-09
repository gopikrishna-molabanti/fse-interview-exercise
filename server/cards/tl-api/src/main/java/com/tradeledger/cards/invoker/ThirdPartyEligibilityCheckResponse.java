package com.tradeledger.cards.invoker;

import java.util.Set;

public final class ThirdPartyEligibilityCheckResponse {

    private Set<String> eligibleCards;

    public Set<String> getEligibleCards() {
        return eligibleCards;
    }

    public void setEligibleCards(Set<String> eligibleCards) {
        this.eligibleCards = eligibleCards;
    }
}
