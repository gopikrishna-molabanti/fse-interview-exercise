package com.tradeledger.cards.invoker;

import com.tradeledger.cards.common.Applicant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.Objects;

@Component
public class ThirdPartyApiInvoker {

    Logger logger = LoggerFactory.getLogger(ThirdPartyApiInvoker.class);

    private RestTemplate restTemplate;

    @Value("${thirdParty.api.eligibility.check.uri}")
    private String thirdPartyApiUri;

    @PostConstruct
    private void postConstruct() {
        restTemplate = new RestTemplate();
    }

    public ThirdPartyEligibilityCheckResponse invokeThirdPartyApiForEligibilityCheck(final Applicant applicant) {
        HttpEntity<Applicant> requestEntity = createRequestEntity(applicant);
        ResponseEntity<ThirdPartyEligibilityCheckResponse> responseEntity;
        try {
            responseEntity = restTemplate.exchange(thirdPartyApiUri,
                    HttpMethod.POST, requestEntity, ThirdPartyEligibilityCheckResponse.class);
            return processResponse(responseEntity);
        } catch (Exception e) {
            logger.error("Exception occurred while invoking 3rd party api.", e);
            return null;
        }

    }

    private HttpEntity<Applicant> createRequestEntity(Applicant applicant) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(applicant, headers);
    }

    private ThirdPartyEligibilityCheckResponse processResponse(final ResponseEntity<ThirdPartyEligibilityCheckResponse> responseEntity) {
        if (Objects.nonNull(responseEntity) && HttpStatus.OK.equals(responseEntity.getStatusCode())) {
            return responseEntity.getBody();
        }
        return null;
    }
}
