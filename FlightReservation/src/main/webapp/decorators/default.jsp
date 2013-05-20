<%@ include file="/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <title><decorator:title default="Welcome"/></title>
    <meta http-equiv="Cache-Control" content="no-store"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Expires" content="0"/>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <link rel="shortcut icon" href="/iFlightReservation/mages/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" href="/FlightReservation/styles/deliciouslyblue/theme.css" title="default" />     
    <link rel="alternate stylesheet" type="text/css" href="/FlightReservation/styles/deliciouslygreen/theme.css" title="green" />
    <script type="text/javascript" src="/FlightReservation/scripts/prototype.js"></script>
    <script type="text/javascript" src="/FlightReservation/scripts/scriptaculous.js"></script>
    <script type="text/javascript" src="/FlightReservation/scripts/stylesheetswitcher.js"></script>
    <script type="text/javascript" src="/FlightReservation/scripts/global.js"></script>
    <decorator:head/>
</head>
<body>
<a name="top"></a>
<div id="page">

    <div id="header" class="clearfix">

        <h1 style="cursor: pointer" onclick="location.href='${ctx}/'">Flight Application</h1>
    </div>

    <div id="content">

        <div id="main">
            <h1><decorator:title/></h1>
            <%@ include file="/messages.jsp"%>
            <decorator:body/>

            <div id="underground"><decorator:getProperty property="page.underground"/></div>
        </div>
        <div id="nav">
            <div class="wrapper">
                <h2 class="accessibility">Navigation</h2>
                <ul class="clearfix">
                    <li><a href="/FlightReservation/home.html" title="Home"><span>Home</span></a></li>
                    <li><a href="/FlightReservation/searchFlights.html" title="Flights"><span>Flights</span></a></li>
                    <li><a href="/FlightReservation/reservations.html" title=""><span>Reservations</span></a></li>
                </ul>
            </div>
        </div><!-- end nav -->

    </div><!-- end content -->

    <div id="footer">
        <p>
            <a href="http://validator.w3.org/check?uri=referer">Valid XHTML 1.0</a> |
            <a href="http://www.oswd.org/design/preview/id/2634">Deliciously Blue</a> from <a href="http://www.oswd.org/">OSWD</a> |
            Design by <a href="http://www.oswd.org/user/profile/id/8377">super j man</a>
        </p>
    </div>
</div>
</body>
</html>


