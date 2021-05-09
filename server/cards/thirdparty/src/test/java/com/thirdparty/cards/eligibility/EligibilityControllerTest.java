package com.thirdparty.cards.eligibility;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static com.thirdparty.cards.eligibility.Eligibility.newEligibility;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

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

		EligibilityApplicant applicant = new EligibilityApplicant("Boris", "Boris@J.com", "143 Icy Road");
		
		when(service.checkEligibilityFor(applicant))
			.thenReturn(newEligibility(2).addCard("C1").addCard("C2").build());
		
		mvc.perform(
				post("/eligibility/check")
					.contentType("application/json")
					.content(objMapper.writeValueAsString(applicant))
				)
				.andExpect(status().isOk());
	}
	
}
