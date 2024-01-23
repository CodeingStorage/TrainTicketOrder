<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>車票搜尋結果</title><link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
</head>

<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="../index.html">灣鐵</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active " aria-current="page" href="../schedule_query/schedule_query.html">列車時刻/車次查詢</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active " aria-current="page" href="../booking/booking.html">預訂車票</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active " aria-current="page" href="../ticket_query/ticket_query.html">查詢/修改車票</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active " aria-current="page" href="../../backend/ticket_info/ticket_info_display.html">後端</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    
    <div class="container mt-5">
    <h2 class="mb-4">Ticket Query Result</h2>

    <c:if test="${not empty tickets}">
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Ticket ID</th>
                    <th>User ID</th>
                    <th>Train ID</th>
                    <th>Departure Station</th>
                    <th>Arrival Station</th>
                    <th>Price</th>
                    <th>Booking Time</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="ticket" items="${tickets}">
                    <tr>
                        <td>${ticket.ticketId}</td>
                        <td>${ticket.userId}</td>
                        <td>${ticket.trainId}</td>
                        <td>${ticket.departureStation}</td>
                        <td>${ticket.arrivalStation}</td>
                        <td>${ticket.price}</td>
                        <td>${ticket.bookTime}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <c:if test="${empty tickets}">
        <p class="alert alert-info">No tickets found for the specified criteria.</p>
    </c:if>
</div>

    
    
</body>
</html>