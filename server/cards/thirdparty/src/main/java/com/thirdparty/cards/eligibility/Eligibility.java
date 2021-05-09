package com.thirdparty.cards.eligibility;

import java.util.HashSet;
import java.util.Set;

public final class Eligibility {

	private final Set<String> eligibleCards;

	private Eligibility(Set<String> eligibleCards) {
		
		this.eligibleCards = eligibleCards;
	}

	public static EligibilityBuilder newEligibility(final int numberOfEligibleCards) {
		
		return new EligibilityBuilder(numberOfEligibleCards);
	}
	
	public static class EligibilityBuilder {
		
		private final int numberOfEligibleCards;
		private final Set<String> newEligibility;
		
		public EligibilityBuilder(final int numberOfEligibleCards) {
			
			this.numberOfEligibleCards = numberOfEligibleCards;
			this.newEligibility = new HashSet<String>(numberOfEligibleCards);
		}
		
		public EligibilityBuilder addCard(final String cardName) {
			
			if (numberOfEligibleCards > newEligibility.size()) {
				newEligibility.add(cardName);
				return this;
			}

			throw new IllegalStateException("Cannot add more cards than eligible for!");
		}
		
		public Eligibility build() {
			
			if (numberOfEligibleCards != newEligibility.size()) {
				throw new IllegalStateException("Incorrect number of cards specified!");
			}
			
			return new Eligibility(newEligibility);
		}
	}

	public Set<String> getEligibleCards() {
		return this.eligibleCards;
	}
}
