<%@ include file="/taglibs.jsp"%>
<H2>
    Detail of Flight: <c:out value="${flight.number}" />
</H2>
<table>
<!-- 
	<tr>
		<td>
			<b>Flight Number:</b>
		</td>
		<td>
			<c:out value="${flight.number}" />
		</td>
	</tr>
-->	
	<tr>
		<td>
			From:
		</td>
		<td>
			<c:out value="${flight.fromAirport.city}" />
			(
			<c:out value="${flight.fromAirport.code}" />
			)
		</td>
	   <td>
            Departure:
        </td>
        <td>
            <joda:format value="${flight.departureTime}" style="MM" />
        </td>
    
	</tr>
	
	<tr>
	   <td>
            To:
        </td>
        <td>
            <c:out value="${flight.toAirport.city}" />
            (
            <c:out value="${flight.toAirport.code}" />
            )
        </td>
    
		<td>
			Arrival:
		</td>
		<td>
			<joda:format value="${flight.arrivalTime}" style="MM" />
		</td>
	</tr>
</table>

<H2>
    Book a ticket on flight: <c:out value="${flight.number}" />
</H2>

<form action='${ctx}/bookFlight.htm'>
	<input type="hidden" name="flightId" value="${flight.number}" />
	<input type="hidden" name="departureTime"
		value="${flight.departureTime}" />

	<table>
		<tr>
			<td>
				First Name:
			</td>
			<td>
				<input type="text" name="firstName" />
			</td>
		</tr>
		<tr>
			<td>
				Last Name:
			</td>
			<td>
				<input type="text" name="lastName" />
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="Book this flight" />
			</td>
		</tr>
	</table>
</form>

<a href='<c:url value="/flights.htm"/>'>Search for other flights</a>
