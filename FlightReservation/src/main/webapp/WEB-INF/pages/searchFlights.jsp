<%@ include file="/taglibs.jsp"%>

<H2>Search Flights</H2>

<form:form commandName="flightSearchRequest" action='${ctx}/searchFlights.html' method="post">
	<table>
		<tr>
			<td>From :</td>

			<td><select name="fromAirportCode" STYLE="width: 250px">
					<option value="">--Select One--</option>
					<c:forEach items="${airports}" var="airport">
						<option
							<c:if test="${from eq airport.code}">
                         selected="selected"
                         </c:if>
							value='<c:out value="${airport.code}"/>'>
							<c:out value='${airport.name}' />
						</option>
					</c:forEach>
			</select></td>
		</tr>
		<tr>
			<td>To:</td>
			<td><select name="toAirportCode" STYLE="width: 250px">
					<option value="">--Select One--</option>
					<c:forEach items="${airports}" var="airport">
						<option
							<c:if test="${airport.code eq to}">
                         selected="selected"
                         </c:if>
							value='<c:out value="${airport.code}"/>'>
							<c:out value='${airport.name}' />
						</option>
					</c:forEach>
			</select></td>

		</tr>
		
		<tr>
			<td colspan="2">
				<p class="submit">
					<input class="submit" type="submit" value="Submit" />
				</p>
			</td>
		</tr>
	</table>
</form:form>

<display:table name="flightSearchResult.flights" class="table"
	requestURI="" id="flight" export="true" pagesize="10">
	<display:column title="Number" property="number" sortable="true" />

	<display:column title="Departure City" property="from.city"
		sortable="true" escapeXml="true" />
	<display:column title="Departure" sortable="true"
		sortProperty="departureTime" escapeXml="true">
		<joda:format value="${flight.departureTime}" style="SS" />
	</display:column>
	<display:column title="Arrival City" property="to.city" sortable="true"
		escapeXml="true" />
	<display:column title="Arrival" sortable="true"
		sortProperty="arrivalTime" escapeXml="true">
		<joda:format value="${flight.arrivalTime}" style="SS" />
	</display:column>
	<display:column title="Seats Available" property="seatsAvailable" />
	<display:column title="Miles" property="miles" />
	<display:column title="" sortable="false" href="${ctx}/bookFlight.html"
		paramId="id" paramProperty="id" titleKey="flightId">
       		 Book
       </display:column>
</display:table>

<script type="text/javascript">
	highlightTableRows("flightSearchResult.flights");
</script>