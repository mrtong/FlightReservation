package com.foo.flight.service.rest.client;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.foo.flight.model.Airport;
import com.foo.flight.model.Airports;
import com.foo.flight.service.exceptions.NoSuchAirportException;
import com.foo.flight.service.interfaces.AirportService;
@Service
public class AirportServiceRestClient implements AirportService {
	@Autowired
	private RestTemplate template;

	public RestTemplate getTemplate() {
		return template;
	}

	public void setTemplate(RestTemplate template) {
		this.template = template;
	}

	private static final String BASE_URL = "http://localhost:8080/FlightReservation/";
	// private static final String BASE_URL = "http://localhost:9091";
	private MediaType contentType;
	private MediaType accept;
	@SuppressWarnings("rawtypes")
	private HttpEntity entity;
	private HttpHeaders headers;
	private List<MediaType> acceptableMediaTypes;
    @PostConstruct
    private void initialise() {
//    	Based on http://www.mkyong.com/spring/spring-postconstruct-and-predestroy-example/
//    	By default, Spring will not aware of the @PostConstruct and @PreDestroy annotation. To enable it, you have to either register ‘CommonAnnotationBeanPostProcessor‘ or specify the ‘<context:annotation-config />‘ in bean configuration file,
		acceptableMediaTypes = new ArrayList<MediaType>();
	    acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.setAccept(acceptableMediaTypes);
	    entity = new HttpEntity(headers);	
    }

	@Override
	public List<Airport> getAirports() {
		// TODO Auto-generated method stub
		    
		ResponseEntity<Airports> responseEntity = template.exchange(BASE_URL+ "/airports", HttpMethod.GET, entity, Airports.class);
		Airports airports=responseEntity.getBody();
	    
		return airports.getAirport();
	}

	@Override
	public Airport getAirport(String code) throws NoSuchAirportException {
		// TODO Auto-generated method stub
		return null;
	}

}
