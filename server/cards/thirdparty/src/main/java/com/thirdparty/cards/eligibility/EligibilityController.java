package com.thirdparty.cards.eligibility;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("eligibility")
public class EligibilityController {

	@Autowired
	private EligibilityService service;
	
	@PostMapping(path = "check", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public Eligibility checkEligibility(@Valid @RequestBody EligibilityApplicant applicant) {
		
		return service.checkEligibilityFor(applicant);
		
	}
	
}
