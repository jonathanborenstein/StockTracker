# StockTracker
A web application for tracking your portfolio

This program is built with the Spring Framework, such as the Spring Boot, Spring MVC, and Spring JPA projects. It uses Google's GSON, as well as an emedded H2 database. If you don't want to use the H2 database you can save the information to a database in the application.properties file. The program already has the connector jar for MySQL.

The program is very simple to use. Just run the program and go to Add Stock Transactions. Here you are just entering information about stocks you have bought. For example, if you purchased 10 shares of NFLX at 100 dollars per share you would add that. You are then shown the current unrealized profit on those shares.

Perhaps you would add 20 shares of VZ bought at 40 dollars per share. You would add that and see it in your unrealized profits list. You will also see the dates of these transactions in a table.

If you decide to sell 5 shares of your NFLX stock at a price of 105 dollars per share ($25 profit in this case) you will see that in your realized gains. In order to sell shares all you have to do is simply put a minus sign when you are adding a stock transaction (-5 in this case).

An easy way to run the project is:
git clone https://github.com/jonathanborenstein/StockTracker.git

Go to the directory where you cloned it and go to StockTracker/StockProject

Once there run    
mvn clean spring-boot:run

Once the program is running go to localhost:8080/stocks as your starting point.
