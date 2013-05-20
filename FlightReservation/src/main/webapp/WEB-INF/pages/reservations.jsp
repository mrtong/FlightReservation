<%@ include file="/taglibs.jsp"%>

<H2>Reservations</H2>

<display:table name="reservations" class="table" requestURI=""
	id="ticket" export="true" pagesize="10">
	<display:column title="Reservation Numer" property="id" />
	<display:column title="Reservation Name" property="reservationName"
		sortable="true" />
	<display:column title="Number of Seats" property="numberOfSeats" />
	<display:column title="Departure City" property="flight.from.city"
		sortable="true" escapeXml="true" />
	<display:column title="Arrival City" property="flight.to.city"
		sortable="true" escapeXml="true" />
	<display:column title="" sortable="false">
		<a href="${ctx}/reservations/${ticket.id}.html"> Show details </a>
	</display:column>
</display:table>

<script type="text/javascript">
	highlightTableRows("flightSearchResult.flights");
</script>