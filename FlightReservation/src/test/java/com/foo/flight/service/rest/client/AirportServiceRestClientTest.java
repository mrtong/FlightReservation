package com.foo.flight.service.rest.client;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import com.foo.flight.model.Airport;
import com.foo.flight.service.exceptions.NoSuchAirportException;

//refer to https://github.com/jeffsheets/MockRestServiceServerExample/blob/master/src/main/java/com/jeffsheets/rest/SimpleRestService.java

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class AirportServiceRestClientTest{
	
	Logger log = Logger.getLogger(AirportServiceRestClientTest.class);
	
	@Configuration
	static class AirportServiceRestTestContextConfiguration {
		@Bean
		public RestTemplate restTemplate() {
			return new RestTemplate();
		}
		
		@Bean
		public AirportServiceRestClient airportServiceRestClient() {
			return new AirportServiceRestClient();
		}
	}
	
	
	
	@Autowired
	private AirportServiceRestClient airportServiceRestClient;
	
	@Autowired
    private RestTemplate restTemplate;
	
	private MockRestServiceServer mockServer;
	
	@Before
	public void setup() {

		mockServer = MockRestServiceServer.createServer(restTemplate);
	}
	
	@Test(expected=NoSuchAirportException.class)
	public void getAirportByCode() throws Exception {
		log.info("TEST:::getAirportByCode");
		String code = "LAX123";
		airportServiceRestClient.getAirport(code);
	}
	
	@Test
	public void getAirports() {
		log.info("TEST:::getAirports");
		Resource responseBody = new ClassPathResource("/airports.json", this.getClass());
		mockServer.expect(requestTo("http://localhost:8080/FlightReservation/airports")).andExpect(method(HttpMethod.GET)).andRespond(withSuccess(responseBody, MediaType.APPLICATION_JSON));
		List<Airport> airportList=airportServiceRestClient.getAirports();
		assertNotNull(airportList);
		assertTrue(airportList.size()>0);
		mockServer.verify();
	}
}
