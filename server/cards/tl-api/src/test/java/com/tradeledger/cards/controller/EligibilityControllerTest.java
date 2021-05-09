package com.tradeledger.cards.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tradeledger.cards.common.Applicant;
import com.tradeledger.cards.common.TradeLedgerEligibilityCheckResponse;
import com.tradeledger.cards.service.EligibilityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = EligibilityController.class)
public class EligibilityControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objMapper;

    @MockBean
    private EligibilityService service;

    @Test
    public void testCheckEligibility() throws Exception {

        Applicant applicant = new Applicant("Boris", "Boris@test.com", "Sydney, 2000");
        TradeLedgerEligibilityCheckResponse tlResponse = new TradeLedgerEligibilityCheckResponse();
        tlResponse.setEligibleCards(Collections.singleton("C1"));

        ResponseEntity<TradeLedgerEligibilityCheckResponse> responseEntity = new ResponseEntity<>(tlResponse, HttpStatus.OK);

        when(service.checkEligibilityFor(applicant))
                .thenReturn(responseEntity);

        mvc.perform(
                post("/eligibility/check")
                        .contentType("application/json")
                        .content(objMapper.writeValueAsString(applicant))
        )
                .andExpect(status().isOk());
    }

}