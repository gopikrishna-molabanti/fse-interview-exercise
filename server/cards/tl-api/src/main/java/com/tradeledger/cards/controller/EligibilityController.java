package com.tradeledger.cards.controller;

import com.tradeledger.cards.common.Applicant;
import com.tradeledger.cards.common.TradeLedgerEligibilityCheckResponse;
import com.tradeledger.cards.service.EligibilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("eligibility")
public class EligibilityController {

    @Autowired
    private EligibilityService eligibilityService;

    @PostMapping(path = "check", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TradeLedgerEligibilityCheckResponse> checkEligibility(@Valid @RequestBody Applicant applicant) {

        return eligibilityService.checkEligibilityFor(applicant);
    }

}
