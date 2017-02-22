<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Stock Transaction</title>
<link href="${contextRoot}/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextRoot}/css/main.css" rel="stylesheet">
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

	<h1 align="center">Add Stock Transaction</h1>

	<div class="container">


		<form:form method="post" modelAttribute="stock">

			<div class="input-group">
				<label class="col-sm-10 col-form-label">Number Of Shares
					(Put a minus sign in front if selling, example: -200)</label>
				<form:input type="text" path="numShares" class="form-control" />
			</div>

			<div class="input-group">
				<label class="col-sm-10 col-form-label">Price Of Shares (The price paid for
				shares, or if selling, the price the shares were sold at) :</label>
				<form:input type="text" path="price" class="form-control" />
			</div>

			<div class="input-group">
				<label class="col-sm-10 col-form-label">Stock Symbol (e.g. AAPL):</label>
				<form:input type="text" path="symbol" class="form-control" />
				<form:errors path="symbol"></form:errors>

			</div>


			<div class="input-group">
				<button type="submit" class="btn-primary pull-right">Add</button>
			</div>

		</form:form>

	</div>

</body>
</html>