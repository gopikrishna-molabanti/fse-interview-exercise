package com.tradeledger.cards.controller;

import com.tradeledger.cards.common.Applicant;
import com.tradeledger.cards.common.TradeLedgerEligibilityCheckResponse;
import com.tradeledger.cards.service.EligibilityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("eligibility")
public class EligibilityController {

    Logger logger = LoggerFactory.getLogger(EligibilityController.class);

    @Autowired
    private EligibilityService eligibilityService;

    @PostMapping(path = "check", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TradeLedgerEligibilityCheckResponse> checkEligibility(@Valid @RequestBody Applicant applicant) {
        logger.info("Request received for card eligibility check: " + applicant);
        return eligibilityService.checkEligibilityFor(applicant);
    }

}
