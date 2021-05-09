package com.thirdparty.cards.eligibility;

import org.springframework.stereotype.Service;

import com.thirdparty.cards.eligibility.Eligibility.EligibilityBuilder;

import static com.thirdparty.cards.eligibility.Eligibility.newEligibility;

import java.util.stream.Stream;

@Service
public class EligibilityService {
	
	private static final String CARD_1 = "C1";
	private static final String CARD_2 = "C2";
	
	public Eligibility checkEligibilityFor(final EligibilityApplicant applicant) {
		
		switch (applicant.getName()) {
		case "Boris":
			return eligibilityOf(CARD_1);

		case "Angela":
			return eligibilityOf(CARD_1, CARD_2);
			
		case "Theresa":
			return eligibilityOf(CARD_2);
		
		default:
			return eligibilityOf();
		}
	}

	private Eligibility eligibilityOf(String... cards) {
		
		EligibilityBuilder builder = newEligibility(cards.length);
		
		Stream. <String> of(cards).forEach(card -> builder.addCard(card));
		
		return builder.build();
	}
}
