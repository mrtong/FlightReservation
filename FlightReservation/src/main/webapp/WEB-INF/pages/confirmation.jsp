<%@ include file="/taglibs.jsp"%>

Your flight has been successfully booked.
<br />

Confirmation Number:
<c:out value="${ticket.id}" />
<br/>
Ticket Issue date:
<c:out value="${ticket.issueDate}" />
<br/>
Flight:<c:out value="${ticket.flight.number}" />
<br/>
Number of Seats:<c:out value="${ticket.numberOfSeats}" />
<br/>
<table class="table">
	<tr>
		<td>From:</td>
		<td><c:out value="${ticket.flight.from.city}" /> ( <c:out
				value="${ticket.flight.from.code}" /> )</td>
		<td>Departure:</td>
		<td><joda:format value="${ticket.flight.departureTime}" style="MM" /></td>
	</tr>

	<tr>
		<td>To:</td>
		<td><c:out value="${ticket.flight.to.city}" /> ( <c:out
				value="${ticket.flight.to.code}" /> )</td>

		<td>Arrival:</td>
		<td><joda:format value="${ticket.flight.arrivalTime}" style="MM" /></td>
	</tr>
</table>
<br/>
<a href='${ctx}/searchFlights.html'>Search and Book more Flights</a>
<%@ include file="/WEB-INF/pages/footer.jsp"%>
