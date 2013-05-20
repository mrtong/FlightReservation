<%@ include file="/taglibs.jsp"%>
Welcome to the flight application. Search Flights and book reservations. All reservations require a name 
and a quantity which serve to demonstrate JSR 303 validation. No user based authentication/authorization is 
provided for the example. Bookings are per name and quantity. Attempting to book more than the number of seats 
available leads to errors and validation.