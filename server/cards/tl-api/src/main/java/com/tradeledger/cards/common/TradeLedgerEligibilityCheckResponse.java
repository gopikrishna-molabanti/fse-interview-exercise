package com.tradeledger.cards.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public final class TradeLedgerEligibilityCheckResponse {

    private Set<String> eligibleCards;

    private String message;

    public Set<String> getEligibleCards() {
        return eligibleCards;
    }

    public void setEligibleCards(Set<String> eligibleCards) {
        this.eligibleCards = eligibleCards;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
