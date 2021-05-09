package com.tradeledger.cards.service;

import com.tradeledger.cards.common.Applicant;
import com.tradeledger.cards.common.TradeLedgerEligibilityCheckResponse;
import com.tradeledger.cards.invoker.ThirdPartyApiInvoker;
import com.tradeledger.cards.invoker.ThirdPartyEligibilityCheckResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EligibilityServiceTest {

    @Mock
    private ThirdPartyApiInvoker thirdPartyApiInvoker;

    @InjectMocks
    private EligibilityService eligibilityService;

    private ThirdPartyEligibilityCheckResponse thirdPartyEligibilityCheckResponse;
    private Applicant applicant;

    @Before
    public void before() {
        Mockito.reset(thirdPartyApiInvoker);
        thirdPartyEligibilityCheckResponse = new ThirdPartyEligibilityCheckResponse();
    }

    @After
    public void after() {
        Mockito.verifyNoMoreInteractions(thirdPartyApiInvoker);
    }

    @Test
    public void testCheckBorisIsEligibleForCard1() {
        applicant = new Applicant("Boris", "boris@test.com", "Sydney, 2000");
        Set<String> eligibleCards = new HashSet<>();
        eligibleCards.add("C1");
        thirdPartyEligibilityCheckResponse.setEligibleCards(eligibleCards);
        when(thirdPartyApiInvoker.invokeThirdPartyApiForEligibilityCheck(applicant)).thenReturn(thirdPartyEligibilityCheckResponse);
        ResponseEntity<TradeLedgerEligibilityCheckResponse> responseEntity = eligibilityService.checkEligibilityFor(applicant);

        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertThat(responseEntity.getBody().getEligibleCards()).hasSize(1);
        assertThat(responseEntity.getBody().getEligibleCards()).contains("C1");
        assertNull(responseEntity.getBody().getMessage());

        verify(thirdPartyApiInvoker, times(1)).invokeThirdPartyApiForEligibilityCheck(applicant);
    }

    @Test
    public void testCheckAngelaIsEligibleForCard1AndCard2() {

        applicant = new Applicant("Angela", "Angela@test.com", "Sydney, 2000");
        Set<String> eligibleCards = new HashSet<>();
        eligibleCards.add("C1");
        eligibleCards.add("C2");
        thirdPartyEligibilityCheckResponse.setEligibleCards(eligibleCards);
        when(thirdPartyApiInvoker.invokeThirdPartyApiForEligibilityCheck(applicant)).thenReturn(thirdPartyEligibilityCheckResponse);
        ResponseEntity<TradeLedgerEligibilityCheckResponse> responseEntity = eligibilityService.checkEligibilityFor(applicant);

        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertThat(responseEntity.getBody().getEligibleCards()).hasSize(2);
        assertThat(responseEntity.getBody().getEligibleCards()).contains("C1");
        assertThat(responseEntity.getBody().getEligibleCards()).contains("C2");
        assertNull(responseEntity.getBody().getMessage());

        verify(thirdPartyApiInvoker, times(1)).invokeThirdPartyApiForEligibilityCheck(applicant);
    }

    @Test
    public void testEveryoneElseIsEligibleForNothing() {

        applicant = new Applicant("Gopi", "Gopi@test.com", "Sydney, 2000");
        when(thirdPartyApiInvoker.invokeThirdPartyApiForEligibilityCheck(applicant)).thenReturn(null);
        ResponseEntity<TradeLedgerEligibilityCheckResponse> responseEntity = eligibilityService.checkEligibilityFor(applicant);

        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody().getEligibleCards());
        assertEquals("Technical error occurred while checking eligibility. Please try again in sometime", responseEntity.getBody().getMessage());

        verify(thirdPartyApiInvoker, times(1)).invokeThirdPartyApiForEligibilityCheck(applicant);
    }
}