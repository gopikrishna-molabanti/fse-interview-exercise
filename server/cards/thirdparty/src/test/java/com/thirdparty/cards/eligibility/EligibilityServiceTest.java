package com.thirdparty.cards.eligibility;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EligibilityServiceTest {

	private final EligibilityService service = new EligibilityService();

	@Test
	public void testCheckBorisIsEligibleForCard1() {

		Eligibility eligibilityForBoris = service.checkEligibilityFor(
				new EligibilityApplicant("Boris", "b@b.com", "5 Drowning Street"));

		assertThat(eligibilityForBoris.getEligibleCards()).hasSize(1);
		assertThat(eligibilityForBoris.getEligibleCards()).contains("C1");
		
	}

	@Test
	public void testCheckAngelaIsEligibleForCard1AndCard2() {

		Eligibility eligibilityForAngela = service.checkEligibilityFor(
				new EligibilityApplicant("Angela", "a@b.com", "5 Drowning Street"));

		assertThat(eligibilityForAngela.getEligibleCards()).hasSize(2);
		assertThat(eligibilityForAngela.getEligibleCards()).contains("C1");
		assertThat(eligibilityForAngela.getEligibleCards()).contains("C2");

	}

	@Test
	public void testCheckTheresaIsEligibleForCard2() {

		Eligibility eligibilityForTheresa = service.checkEligibilityFor(
				new EligibilityApplicant("Theresa", "t@b.com", "5 Drowning Street"));

		assertThat(eligibilityForTheresa.getEligibleCards()).hasSize(1);
		assertThat(eligibilityForTheresa.getEligibleCards()).contains("C2");

	}

	@Test
	public void testEveryoneElseIsEligibleForNothing() {

		Eligibility eligibilityForDonald = service.checkEligibilityFor(
				new EligibilityApplicant("Donald", "a@b.com", "5 Drowning Street"));

		assertThat(eligibilityForDonald.getEligibleCards()).isEmpty();

	}
}
