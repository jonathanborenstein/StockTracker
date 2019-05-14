$(document).ready(function() {

	fire_ajax_submit();
	realizedProfit();
	transactions();
	profits();


	$("#search-form2").submit(function(event) {

		event.preventDefault();
		stock();

	});
	
	$("#sample-purchase").submit(function(event) {

		event.preventDefault();
		samplePurchase();

	});
	
	$("#sample-sale").submit(function(event) {

		event.preventDefault();
		sampleSale();

	});

});


function realizedProfit() {


	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "/realized",
		// data: JSON.stringify(search),
		dataType : 'json',
		cache : false,
		timeout : 600000,
		success : function(data) {

			var txt = "";

			txt += "<table> <tr><th style='padding-right: 25px'>Symbol</th> " +
			"<th style='padding-right: 25px'>Number of Shares</th> " +
			"<th style='padding-right: 25px'>Price</th>" +
			"<th style='padding-right: 25px'>Proceeds</th>" +
			"<th style='padding-right: 25px'>Cost</th>" +
			"<th style='padding-right: 25px'>Realized Profit</th></tr>"
			for (x in data) {
				txt += "<tr><td style='padding-right: 25px'>" + data[x].symbol + 
				"</td><td style='padding-right: 25px'>" + data[x].numShares +
				"</td><td style='padding-right: 25px'>" + data[x].price +
				"</td><td style='padding-right: 25px'>" + data[x].proceeds+
				"</td><td style='padding-right: 25px'>" + data[x].cost +
				"</td><td style='padding-right: 25px'>" + data[x].realizedProfit +
				"</td></tr>";
			}
			txt += "</table>"        
				document.getElementById("messages").innerHTML = txt;


			$('#messages').html(txt);

			console.log("SUCCESS : ", data);
			$("#btn-search").prop("disabled", false)
			//setTimeout(realizedProfit, 10000);
		},
		error : function(e) {


		}
	});

}

function samplePurchase() {


	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "/sample",
		// data: JSON.stringify(search),
		dataType : 'text',
		cache : false,
		timeout : 600000,
		success : function(data) {


			console.log("SUCCESS : ", data);
			profits();
			transactions();
			realizedProfit();

		},
		error : function(e) {

		}
	});

}

function sampleSale() {


	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "/samplesale",
		// data: JSON.stringify(search),
		dataType : 'text',
		cache : false,
		timeout : 600000,
		success : function(data) {


			console.log("SUCCESS : ", data);
			profits();
			transactions();
			realizedProfit();

		},
		error : function(e) {

		}
	});

}

function profits() {


	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "/profits",
		// data: JSON.stringify(search),
		dataType : 'text',
		cache : false,
		timeout : 600000,
		success : function(data) {


			$('#feed').html(data);

			console.log("SUCCESS : ", data);

		},
		error : function(e) {

		}
	});

}
function transactions() {


	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "/transactions",
		// data: JSON.stringify(search),
		dataType : 'json',
		cache : false,
		timeout : 600000,
		success : function(data) {

			var txt = "";

			txt += "<table> <tr><th style='padding-right: 25px'>Symbol</th> " +
			"<th style='padding-right: 25px'>Number of Shares</th> " +
			"<th style='padding-right: 25px'>Price Of Shares</th>" +
			"<th style='padding-right: 25px'>Date</th></tr>"


			for (x in data) {

				var nowDate = new Date(parseInt(data[x].added));

				txt += "<tr><td style='padding-right: 25px'>" + data[x].symbol + 
				"</td><td style='padding-right: 25px'>" + data[x].numOfShares +
				"</td><td style='padding-right: 25px'>" + data[x].priceOfShares +
				"</td><td style='padding-right: 25px'>" + nowDate +


				"</td></tr>";
			}
			txt += "</table>"        
				document.getElementById("stocks").innerHTML = txt;

			$('#stocks').html(txt);

			console.log("SUCCESS : ", data);
			$("#btn-search").prop("disabled", false)
			//setTimeout(transactions, 10000);
		},
		error : function(e) {

			console.log("ERROR : ", e);

		}
	});

}

function fire_ajax_submit() {

	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : "/current",
		// data: JSON.stringify(search),
		dataType : 'json',
		cache : false,
		timeout : 600000,
		success : function(data) {


			var txt = "";

			txt += "<table> <tr><th style='padding-right: 25px'>Symbol</th> " +
			"<th style='padding-right: 25px'>Current Price</th> " +
			"<th style='padding-right: 25px'>Total Shares</th>" +
			"<th style='padding-right: 25px'>Total Price</th>" +
			"<th style='padding-right: 25px'>Average Price</th>" +
			"<th style='padding-right: 25px'>Profit</th></tr>"
			for (x in data) {
				txt += "<tr><td style='padding-right: 25px'>" + data[x].symbol + 
				"</td><td style='padding-right: 25px'>" + data[x].currentPrice +
				"</td><td style='padding-right: 25px'>" + data[x].totalShares +
				"</td><td style='padding-right: 25px'>" + data[x].totalPrice+
				"</td><td style='padding-right: 25px'>" + data[x].averagePrice +
				"</td><td style='padding-right: 25px'>" + data[x].profit +
				"</td></tr>";
			}
			txt += "</table>"        
				document.getElementById("feedback").innerHTML = txt;

			$('#feedback').html(txt);
			profits();

			console.log("SUCCESS : ", data);
			setTimeout(fire_ajax_submit, 10000);
		},
		error : function(e) {

			var json = "<h4>Ajax Response</h4><pre>" + e.responseText
			+ "</pre>";
			$('#feedback').html(json);

			console.log("ERROR : ", e);
			$("#btn-search").prop("disabled", false);

		}
	});

}

function stock() {

	var data = new Object();
	data.symbol = $('#symbol').val()
	data.numOfShares = $('#shares').val();
	data.priceOfShares = $('#price').val();
	data.state = $('#state').val();


	$.ajax({
		type: "POST",
		contentType: "application/json",
		url: "/post",
		data: JSON.stringify(data),
		dataType: 'json',
		cache: false,
		timeout: 600000,
		success: function (data) {

			$('input[type="text"],textarea').val('');
			$('input[type="number"],textarea').val('');

			profits();
			transactions();
			realizedProfit();



		},
		error: function (e) {

			console.log("ERROR : ", e);


		}
	});

}