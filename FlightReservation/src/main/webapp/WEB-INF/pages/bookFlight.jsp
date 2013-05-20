<%@ include file="/taglibs.jsp"%>

<H2>Book Seats on flight</H2>

<table>
	<tr>
		<td>Flight Number:</td>
		<td><c:out value="${flight.number}" />
	</tr>
	<tr>
		<td>From:</td>
		<td><c:out value="${flight.from.city}" /> ( <c:out
				value="${flight.from.code}" /> )</td>
		<td>Departure:</td>
		<td><joda:format value="${flight.departureTime}" style="MM" /></td>
	</tr>

	<tr>
		<td>To:</td>
		<td><c:out value="${flight.to.city}" /> ( <c:out
				value="${flight.to.code}" /> )</td>

		<td>Arrival:</td>
		<td><joda:format value="${flight.arrivalTime}" style="MM" /></td>
	</tr>
	<tr>
		<td>Seats Currently Available:</td>
		<td><c:out value="${flight.seatsAvailable}"/></td>
	</tr>
</table>

<form:form commandName="reservation" method="post" action="${ctx}/bookFlight.html"> 
    <input type="hidden" name="flightId" value="${flight.id}"/>
    <table>
        <tr>
            <td>Reservation Name:<font color="red"><form:errors path="reservationName"/></font></td>
            <td><form:input path="reservationName"/></td>
        <tr>
            <td>Number of Seats:<font color="red"><form:errors path="quantity"/></font></td>
            <td><form:input path="quantity"/></td>
        </tr>
    </table>
    <input type="submit" value="Submit" />

</form:form>
