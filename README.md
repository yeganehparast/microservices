#### Backend Assignment

This is the solution provided for backend technical assignment demonstrating Microservices development.

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

###### 6- Problem description

starbux Coffee - Backend API 

We need a backend for our online coffee place startup, starbux coffee, where users can order drinks/toppings and admins can create/update/delete drinks/toppings and have access to reports.

Functional Requirements

    ● Develop an API that will be used to order drinks with any of the topping combinations.
    
    ● Visitor journeys should be transparent, the current amount of the cart and the products should be communicated back to the caller of the API.
    
    ● When finalizing the order, the original amount and the discounted amount should be communicated back to the caller of the API.

    ● Reports are present with the criteria suggested in the admin API requirements.
    
 Drinks:
 
    ● Black Coffee - 4 eur
    
    ● Latte - 5 eur
    
    ● Mocha - 6 eur
    
    ● Tea - 3 eur
    
Toppings/sides:

    ● Milk - 2 eur
    
    ● Hazelnut syrup - 3 eur
    
    ● Chocolate sauce - 5 eur
    
    ● Lemon - 2 eur
    
Discount logic:

    ● If the total of the cart is more than 12 euros, there should be a 25% discount.
    
    ● If there are 3 or more drinks in the cart, the one with the lowest amount (including toppings) should be free.
    
    ● If the cart is eligible for both promotions, the promotion with the lowest cart amount should be used and the other one should be ignored.

Admin api:

    ● Should be able to create/update/delete products and toppings.
    ● Reports:
        ○ Total amount of the orders per customer.
        ○ Most used toppings for drinks.
