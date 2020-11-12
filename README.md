# Project Title
# Console Application

CRUD API for Customer with fields of First Name , Last Name, IBAN
and simple Rest Api security

## Description
Create a RESTful service that manage (CRUD) users and their bank account data (IBAN). Required fields are first name, last name and IBAN. 

1. Administrators should be able to create, list, update and delete users via API
2. Users should be able to list users via API
3. Restrict manipulation operations on a user to the administrator who created them
4. Use H2 in memory as the database backend
5. Integration -tests for the implementation


## Getting Started

### Dependencies

No dependency

### Installing

* copy  c

### Executing program

Run
* 

* http://localhost:8080/api/v1/customers ---> in post man with Post method and Select Simple Authentication with username: admin , password :password and json body
{ ibans : "DE89370400440535654300" , fName : "Sami" , lName : "Schneider" }

* http://localhost:8080/api/v1/customers ---> with get method for see the list of customers
* http://localhost:8080/api/v1/customers/id ---> with get method to retrieve customer information with id 
* http://localhost:8080/api/v1/customers/id --> in the postman with delete method select Simple Authentication with username: admin 
, password for deleting customer with specified id in url
*  http://localhost:8080/api/v1/customers/id -->  in the postman with put method and body of json select Simple Authentication with 
username: admin , password for updating user with specified id in url
