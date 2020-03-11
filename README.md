# Coding Task from SearchMetrics created using SpringBoot.
This will get the latest and historical price of 1 Bitcoin to USD with date range at regular interval which can be configured.

#Rest Points
It has 2 rest points.
1. For fetching latest price: Price for 1 Bitcoin to USD, it should also regularly check the latest price for which 
I have used @Scheduled annotation provided by Spring along with time interval which is in seconds and configurable in application.properties.
2. For checking historical rates of 1 Bitcoin to USD along with date range: This endpoint is not schedulable, it is a normal api, 
which gives rate of 1 bitcoin to USD in range of start and end date given in api.

#Framework used:
1. I have used Spring-Boot along with Spring rest-webservices, as creating production ready applications using Spring Boot is very easy and 
fast.
2. Created controllers which calls the services and the business logic is written in services.
3. We are hitting 3rd Party api to fetch latest as well as historical rates for 1 Bitcoin to USD, the response of this api is in
application/javascript so to convert this in Json, I have used MappingJackson2HttpMessageConverter.
4. Rest web-services are created using RestTemplate.
5. I have used Sonar-Lint to check for any coding issues.
