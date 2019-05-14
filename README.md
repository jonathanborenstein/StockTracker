# StockTracker
A web application for tracking your portfolio

UPDATE: An example can be found here: https://stocktracker1.herokuapp.com/

UPDATE: The program has been updated so that when you sell some of your shares, the realized profit will be taken from the first lot of shares you bought that has not been sold off yet. For example, if you purchase 10 shares of AAPL at $100, purchase another 10 shares of AAPL at $105, and then decide to sell 12 shares at $110, the first 10 shares will be sold and recorded with a profit of ($110 - $100), while the next 2 shares will be sold and recorded at a price of ($110 - 105). Once a lot is completely sold off it is not taken into account anymore.

This program is built with the Spring Framework, such as the Spring Boot, Spring MVC, and Spring JPA projects. It uses Google's GSON, as well as an emedded H2 database. If you don't want to use the H2 database you can save the information to a database in the application.properties file. The program already has the connector jar for MySQL.

The program is very simple to use. Just run the program and go to Add Stock Transactions. Here you are just entering information about stocks you have bought. For example, if you purchased 10 shares of NFLX at 100 dollars per share you would add that. You are then shown the current unrealized profit on those shares.

Perhaps you would add 20 shares of VZ bought at 40 dollars per share. You would add that and see it in your unrealized profits list. You will also see the dates of these transactions in a table.

If you decide to sell 5 shares of your NFLX stock at a price of 105 dollars per share ($25 profit in this case) you will see that in your realized gains. In order to sell shares all you have to do is simply put a minus sign when you are adding a stock transaction (-5 in this case).

An easy way to run the project is:
<code>git clone https://github.com/jonathanborenstein/StockTracker.git</code>

Go to the directory where you cloned it and go to StockTracker/StockProject

Once there run    
<code>mvn clean spring-boot:run</code>

Once the program is running go to localhost:8080 as your starting point.
