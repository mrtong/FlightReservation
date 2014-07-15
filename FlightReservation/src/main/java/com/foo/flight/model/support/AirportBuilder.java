package com.foo.flight.model.support;

import com.foo.flight.model.Airport;

public class AirportBuilder extends EntityBuilder<Airport> {

	@Override
	void initProduct() {
		this.product = new Airport();
	}
	
	public AirportBuilder buildAirport(String code, String name, String city){
		product.setCode(code);
		product.setName(name);
		product.setCity(city);
		return this;
	}

	@Override
	Airport assembleProduct() {
		return this.product;
	}

}
