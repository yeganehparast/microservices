#### Backend Assignment

This is the solution provided for backend technical assignment of BESTSELLER. 

###### 1- Design

The design of application is based on REST design principles.

###### 2- Technology

Application is based on SpringBoot. The application is dockerized as well. Open Api is used. The application is deployed
Heroku as well as Circle Ci. The link below redirects to deployed application on Heroku. A BitBucket repository is used to
show the CI/CD practices as well. (please let me know if you need more details)


###### 3- Tests

This application is tested carefully. There are unit tests, mocking tests, integration test and endpoint tests available.

###### 4- Log

Different logging approach were provided including request interceptors and endpoint level, AOP-based method level logging 
and simple logging approach inside the methods are available

###### 5- Assumption

Products should be saved first. Then, an order can be taken place.
Any string except topping in the product type definition will be considered as drink.
For making order, the important thing that should be filled is the product name and client name. The rest of the fields 
are optional. 

Thanks for your review in advance!
