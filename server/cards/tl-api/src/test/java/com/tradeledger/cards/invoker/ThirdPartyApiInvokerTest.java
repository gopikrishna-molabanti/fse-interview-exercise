package com.tradeledger.cards.invoker;

import com.tradeledger.cards.common.Applicant;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.*;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class ThirdPartyApiInvokerTest {

    private static final String URI = "http://localhost:3317/eligibility/check";

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ThirdPartyApiInvoker thirdPartyApiInvoker;

    private Applicant applicant;
    private ThirdPartyEligibilityCheckResponse thirdPartyEligibilityCheckResponse;

    @Before
    public void before() {
        Mockito.reset(restTemplate);
        ReflectionTestUtils.setField(thirdPartyApiInvoker, "thirdPartyApiUri", URI);
        thirdPartyEligibilityCheckResponse = new ThirdPartyEligibilityCheckResponse();
    }

    @After
    public void after() {
        Mockito.verifyNoMoreInteractions(restTemplate);
    }

    @Test
    public void testCheckBorisIsEligibleForCard1() {
        applicant = new Applicant("Boris", "boris@test.com", "Sydney, 2000");
        Set<String> eligibleCards = new HashSet<>();
        eligibleCards.add("C1");
        thirdPartyEligibilityCheckResponse.setEligibleCards(eligibleCards);
        ResponseEntity<ThirdPartyEligibilityCheckResponse> responseEntity = new ResponseEntity<>(thirdPartyEligibilityCheckResponse, HttpStatus.OK);
        HttpEntity<Applicant> requestEntity = createRequestEntity(applicant);
        when(restTemplate.exchange(URI, HttpMethod.POST, requestEntity, ThirdPartyEligibilityCheckResponse.class)).thenReturn(responseEntity);
        ThirdPartyEligibilityCheckResponse result = thirdPartyApiInvoker.invokeThirdPartyApiForEligibilityCheck(applicant);

        assertNotNull(result);
        assertThat(result.getEligibleCards()).hasSize(1);
        assertThat(result.getEligibleCards()).contains("C1");

        verify(restTemplate, times(1)).exchange(URI, HttpMethod.POST, requestEntity, ThirdPartyEligibilityCheckResponse.class);
    }

    @Test
    public void testCheckAngelaIsEligibleForCard1AndCard2() {

        applicant = new Applicant("Angela", "Angela@test.com", "Sydney, 2000");
        Set<String> eligibleCards = new HashSet<>();
        eligibleCards.add("C1");
        eligibleCards.add("C2");
        thirdPartyEligibilityCheckResponse.setEligibleCards(eligibleCards);
        ResponseEntity<ThirdPartyEligibilityCheckResponse> responseEntity = new ResponseEntity<>(thirdPartyEligibilityCheckResponse, HttpStatus.OK);
        HttpEntity<Applicant> requestEntity = createRequestEntity(applicant);
        when(restTemplate.exchange(URI, HttpMethod.POST, requestEntity, ThirdPartyEligibilityCheckResponse.class)).thenReturn(responseEntity);
        ThirdPartyEligibilityCheckResponse result = thirdPartyApiInvoker.invokeThirdPartyApiForEligibilityCheck(applicant);

        assertNotNull(result);
        assertThat(result.getEligibleCards()).hasSize(2);
        assertThat(result.getEligibleCards()).contains("C1");
        assertThat(result.getEligibleCards()).contains("C2");

        verify(restTemplate, times(1)).exchange(URI, HttpMethod.POST, requestEntity, ThirdPartyEligibilityCheckResponse.class);
    }

    @Test
    public void testEveryoneElseIsEligibleForNothing() {

        applicant = new Applicant("Gopi", "Gopi@test.com", "Sydney, 2000");
        ResponseEntity<ThirdPartyEligibilityCheckResponse> responseEntity = new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);
        HttpEntity<Applicant> requestEntity = createRequestEntity(applicant);
        when(restTemplate.exchange(URI, HttpMethod.POST, requestEntity, ThirdPartyEligibilityCheckResponse.class)).thenReturn(responseEntity);
        ThirdPartyEligibilityCheckResponse result = thirdPartyApiInvoker.invokeThirdPartyApiForEligibilityCheck(applicant);

        assertNull(result);
        verify(restTemplate, times(1)).exchange(URI, HttpMethod.POST, requestEntity, ThirdPartyEligibilityCheckResponse.class);
    }

    @Test
    public void ioExceptionWhenCalling3rdPartyApi() {

        applicant = new Applicant("Gopi", "Gopi@test.com", "Sydney, 2000");
        HttpEntity<Applicant> requestEntity = createRequestEntity(applicant);
        when(restTemplate.exchange(URI, HttpMethod.POST, requestEntity, ThirdPartyEligibilityCheckResponse.class)).thenThrow(new RuntimeException("Some network exception occurred calling 3rd party api"));
        ThirdPartyEligibilityCheckResponse result = thirdPartyApiInvoker.invokeThirdPartyApiForEligibilityCheck(applicant);

        assertNull(result);
        verify(restTemplate, times(1)).exchange(URI, HttpMethod.POST, requestEntity, ThirdPartyEligibilityCheckResponse.class);
    }

    private HttpEntity<Applicant> createRequestEntity(Applicant applicant) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(applicant, headers);
    }

}