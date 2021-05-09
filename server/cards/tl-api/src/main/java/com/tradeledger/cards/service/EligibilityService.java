package com.tradeledger.cards.service;

import com.tradeledger.cards.common.Applicant;
import com.tradeledger.cards.common.TradeLedgerEligibilityCheckResponse;
import com.tradeledger.cards.invoker.ThirdPartyApiInvoker;
import com.tradeledger.cards.invoker.ThirdPartyEligibilityCheckResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class EligibilityService {

    @Autowired
    private ThirdPartyApiInvoker thirdPartyApiInvoker;

    public ResponseEntity<TradeLedgerEligibilityCheckResponse> checkEligibilityFor(final Applicant applicant) {
        ThirdPartyEligibilityCheckResponse thirdPartyEligibilityCheckResponse = thirdPartyApiInvoker.invokeThirdPartyApiForEligibilityCheck(applicant);
        TradeLedgerEligibilityCheckResponse tlResponse = new TradeLedgerEligibilityCheckResponse();
        if (Objects.nonNull(thirdPartyEligibilityCheckResponse)) {
            BeanUtils.copyProperties(thirdPartyEligibilityCheckResponse, tlResponse);
            return new ResponseEntity<>(tlResponse, HttpStatus.OK);
        } else {
            tlResponse.setMessage("Technical error occurred while checking eligibility. Please try again in sometime");
            return new ResponseEntity<>(tlResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
