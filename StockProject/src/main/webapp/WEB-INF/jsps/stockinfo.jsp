<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${contextRoot}/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextRoot}/css/main.css" rel="stylesheet">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Stock Tracker</title>
</head>
<body>


	<button>
		<h3>
			<a href="${contextRoot}/generate">Stock Portfolio</a>
		</h3>
	</button>
	<button>
		<h3>
			<a href="${contextRoot}/stocks">Add Stock Transaction</a>
		</h3>
	</button>



	<div class="row">

		<div class="col-md-12" align="center">
			<h1>Portfolio</h1>
			<table class="table" align="center" style="width: 70%">


				<tr>
					<th>Symbol</th>    
					<th>Total Shares</th>    
					<th>Total Price of Shares</th>    
					<th>Current Stock Price</th>    
					<th>Your Average Price</th>    
					<th>Profit or Loss</th>  
				</tr>
				<tbody>
					<c:forEach var="list" items="${list}">

						<tr>
							<td><c:out value="${list.symbol}"></c:out></td>
							<td><c:out value="${list.totalShares}"></c:out></td>
							<td><c:out value="${list.totalPrice}"></c:out></td>
							<td><c:out value="${list.currentPrice}"></c:out></td>
							<td><c:out value="${list.averagePrice}"></c:out></td>
							<td><c:out value="${list.profit}"></c:out></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<br>
			<h3 align="center">
				Unrealized Profit or Loss:
				<c:out value="${profit}"></c:out>
			</h3>
			</br>
		</div>
	</div>

	<div class="row">

		<div class="col-md-12" align="center">
			<h1>Realized Profits</h1>
			<table class="table" align="center" style="width: 70%">

				<tr>
					<th>Symbol</th>  
					<th>Shares Sold</th>    
					<th>Price Sold At</th>    
					<th>Realized Profit</th>       
				<tbody>
					<c:forEach var="realized" items="${realized}">

						<tr>
							<td><c:out value="${realized.symbol}"></c:out></td>
							<td><c:out value="${realized.numShares}"></c:out></td>
							<td><c:out value="${realized.price}"></c:out></td>
							<td><c:out value="${realized.realizedProfit}"></c:out></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>


	<div class="row">


		<div class="col-md-12" align="center">
			<h1>Transactions</h1>
			<table class="table" align="center" style="width: 70%">

				<tr>
					<th>Symbol</th>    
					<th>Shares</th>    
					<th>Price of Shares</th>    
					<th>Date of Transaction</th>    

				</tr>
				<tbody>

					<c:forEach var="stock" items="${stock}">

						<tr>
							<td><c:out value="${stock.symbol}"></c:out></td>
							<td><c:out value="${stock.numShares}"></c:out></td>
							<td><c:out value="${stock.price}"></c:out></td>
							<td><c:out value="${stock.added}"></c:out></td>


						</tr>

					</c:forEach>
					 
				</tbody>


			</table>
		</div>
	</div>
</body>
</html>