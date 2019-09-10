#### Task details by mobile.de ####

Given is a Spring Boot application that implements a REST service for manipulating ads and customers. 
This application is already runnable, but does not do anything useful yet. 
Your task is to make it more useful. 
The scenario of this application is a very simplified version of mobile.de's domain - we have customers, and these customers want to list ads on our platform.

It is very likely that you will not be able to finish all subtasks in the given time frame. You are basically free to choose which subtasks you work on, but keep in mind that some of the subtasks depend on others.
 They are listed here in no particular order. Some of the subtasks will have a bigger impact on the overall assessment than others.

1. The classes in this artifact are all in one package called "de.mobile". Create a proper package structure and move the classes to where they belong.
2. Make the domain objects "MobileAd" and "MobileCustomer" persist, either on a local mysqld or a local mongodb.
3. Currently the application only handles read use cases for ads. Extend the application so that ads can also be created.
4. There is a MobileCustomer class in the artifact. Extend the application so that it is also possible to create and delete customers.
5. Make sure that the Ad related use cases become customer-aware (meaning that an ad needs a customer in every case).
6. Add validation to the calls that create new entities. The following rules should apply:
   - An ad needs a customer id, a make name, a model name and a category.
   - A customer needs a needs a formally valid email address, a firstname and a lastname composed of alphanumeric characters.
7. Configure logging properly so that log messages are logged to a file.
8. Implement a proper error handling for 404 and 500, with different error messages. Please describe how to reproduce both error statuses.
9. Change the project so that it builds an executable jar.
10. Create a simple HTML/Javascript application that talks to the above REST service. It should be able to list all ads. Making it look pretty would be a plus.
11. If the dealer data and the customer data were not accessible from the database but from other RESTful services, what approach would you use to integrate these services? 
12. If you decide not to write tests for your work for reasons of timeboxing, please spend a few minutes to describe what parts of the application you would write tests for, and what aspects these tests would cover, and what role they play in the development process.
####

#### Solution details by Sandeep Vedavyasachar ####

API implementation details

1. Proper packaging structure
2. Persisting both MobileAd and MobileCustomer domains
3. Extended application to handle create,update,delete,list, getByid for both MobileCustomer and MobileAd domains
4. Extended application to handle create,update,delete,list, getByid for both MobileCustomer
5. Ad domain is handled as customer-aware with customer reference for all opetaions
6. Validations for both MobileCustomer and MobileAd
7. Error Handling for 404 and 500 tasks with custom exception handling 
8. Made project as executable jar with spring boot maven build
9. Test cases -- added basic MVC mokito test stub , could not implement due to time constraint, For implementation we can mock both AdResource and CustomerResource using mokito and assert for the successful data fetch and also to verify the exception cases. Refer - CustomerResourceTest for basic stub code which not functional but can be extended to test all scenarios with similar code.
10. Logging -- logging trace to common logger file. Could not add more loggers to info after successful work flow due to time constraint. we can add more loggers after successful operations like save update and delete.
11. UI -- Implemented listing api using angular with themes , due to time constraint could not not complete other operations and linking customer and ad. 
12 If the dealer data and the customer data were not accessible from the database but from other RESTful services, what approach would you use to integrate these services? -- We can access the external data api through RestTemplate or if it is microservice we can connect through Spring cloud concepts like feign and configure communication through eureka naming server and Zull for load balancing,
13. Other best practices followed  - Comtent negotiation for both JSON and XML request, response , HATEOS, Swagger Documentation for API's
Sample code for Rest Template 
14. Connected to mysql database - connection and db details are embedded in application.properties , please change username password to connect as per your local configuration. 
15. Cross orgin filter enabled for angular side communication which was blocking the requests earlier
-----------
private static void getDealerInfo()
{
    final String uri = "http://localhost:8080/example/dealer";
     
    RestTemplate restTemplate = new RestTemplate();
     
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
     
    ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
-------------

Customer API - 

1. GET http://localhost:8080/customer/

Sample response -

[
    {
        "id": 10001,
        "firstName": "Customer1",
        "lastName": "lastname123",
        "companyName": "mobile.de",
        "email": "test@gmail.com"
    },
    {
        "id": 10002,
        "firstName": "Customer2",
        "lastName": "lastname123",
        "companyName": "ebay.de",
        "email": "mno@gmail.com"
    }
]


XML Sample response - with HTTP headers - Accept application/xml

<Resource>
    <id>10001</id>
    <firstName>Customer1</firstName>
    <lastName>lastname123</lastName>
    <companyName>mobile.de</companyName>
    <email>test@gmail.com</email>
    <links>
        <links>
            <rel>all-customers</rel>
            <href>http://localhost:8080/customer</href>
            <hreflang/>
            <media/>
            <title/>
            <type/>
            <deprecation/>
        </links>
    </links>
</Resource>

2. POST - http://localhost:8080/customer/

Acceptable Request Headers - 
Accept application/json,application/xml
Content-Type - application/json, application/xml

Request Payload - 
	{
        "firstName": "test user",
        "lastName": "test lastname",
        "companyName": "mobile.de",
        "email": "test@gmail.com"
    }

 Response -  201 status with location URI location →http://localhost:8080/customer/{id} Notes: (Custom response implemented for created) with HATEOS link.

3. Get by Id - http://localhost:8080/customer/{id}

Sample Response - with HATEOS implemented to relative url's 
{
    "id": 1,
    "firstName": "test user",
    "lastName": "test lastname",
    "companyName": "mobile.de",
    "email": "test@gmail.com",
    "_links": {
        "all-customers": {
            "href": "http://localhost:8080/customer"
        }
    }
}  


4. Update - PUT call - http://localhost:8080/customer/1 
Acceptable Request Headers -  
Accept application/json,application/xml
Content-Type - application/json, application/xml
Payload - 
{
        "firstName": "test user1111",
        "lastName": "test lastname2222",
        "companyName": "mobile.de",
        "email": "test@gmail.com"
    }

Response , 201 status with location URI location →http://localhost:8080/customer/1/ 


5. DELETE - http://localhost:8080/customer/1

Request - id 

Response - status code 200. 


6. 404 error check 

execute PUT, or DELETE or GET by id with non existing id as parameter. 

Request - DELETE - http://localhost:8080/customer/567

Response - 
{
    "timestamp": "2019-09-09T23:35:36.047+0000",
    "message": "Could not find customer with id: 567",
    "details": "uri=/customer/567"
}

7 . Validation check - example http://localhost:8080/customer/

Request - 
	{
        "firstName": "test user",
        "lastName": "test lastname",
        "companyName": "mobile.de",
        "email": "testgmail.com"
    }

{
    "timestamp": "2019-09-09T23:39:23.508+0000",
    "message": "Validation Failed",
    "details": "org.springframework.validation.BeanPropertyBindingResult: 1 errors\nField error in object 'customer' on field 'email': rejected value [testgmail.com]; codes [Email.customer.email,Email.email,Email.java.lang.String,Email]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [customer.email,email]; arguments []; default message [email],[Ljavax.validation.constraints.Pattern$Flag;@512b6521,org.springframework.validation.beanvalidation.SpringValidatorAdapter$ResolvableAttribute@780982df]; default message [must be a well-formed email address]"
}


Ad API details - loaded some test data for customer with Id 10001 on application bootstrap itself. 

1. GET - http://localhost:8080/customer/10001/ad/

Sample response - Response 

[
    {
        "id": 20001,
        "make": "518 d Business Eur6 Xenon",
        "model": "car",
        "description": "test BMW car data",
        "category": "BMW",
        "price": 30000
    },
    {
        "id": 20002,
        "make": "Golf VII Estate 2.0 TDI2",
        "model": "car",
        "description": "Volkswagen test  data",
        "category": "Volkswagen",
        "price": 25000
    },
    {
        "id": 20003,
        "make": "Vinci 490 TD promotional",
        "model": "truck",
        "description": "test truck data",
        "category": "Tabbert Da",
        "price": 21950
    }
]

XML response - with Http header - Accept application/xml


<Resource>
    <id>1</id>
    <make>Audi</make>
    <model>A4</model>
    <description> audi latest a4 mobile.de ad</description>
    <category>car</category>
    <price>30000.00</price>
    <links>
        <links>
            <rel>all-adds-by-customer</rel>
            <href>http://localhost:8080/customer/10001/ad</href>
            <hreflang/>
            <media/>
            <title/>
            <type/>
            <deprecation/>
        </links>
    </links>
</Resource>

2. Create POST - http://localhost:8080/customer/10001/ad/ 

Request Payload - 
{
    "make": "Audi",
    "model": "A4",
    "description": " audi latest a4 mobile.de ad",
    "category": "car",
    "price": 30000
}

Response - created with status 201. (Custom response implemented for created)



3. Update PUT - http://localhost:8080/customer/10001/ad/1 

Acceptable Request Headers - 
Accept application/json,application/xml
Content-Type - application/json, application/xml

Request 
{
    "make": "Audi",
    "model": "A5",
    "description": " updated audi latest a4 mobile.de ad",
    "category": "car",
    "price": 30000
}

Response - created with status 201. (Custom response implemented for created)


4. GET by Id - http://localhost:8080/customer/10001/ad/1

Response - 

{
    "id": 1,
    "make": "Audi",
    "model": "A5",
    "description": " updated audi latest a4 mobile.de ad",
    "category": "car",
    "price": 30000,
    "_links": {
        "all-adds-by-customer": {
            "href": "http://localhost:8080/customer/10001/ad"
        }
    }
}

5. DELETE - http://localhost:8080/customer/10001/ad/1

Response Success 

6. Exception testing 404 - when customer is not found with request to get/ add/ update/detele/ getbyId operations

a.  Customer doesnt exist

Request - http://localhost:8080/customer/10001/ad/1

Response - 
{
    "timestamp": "2019-09-10T09:09:38.315+0000",
    "message": "Could not find customer with id: 5,000",
    "details": "uri=/customer/5000/ad/1"
}

b. Customer exists but ad for customer doesnt exist anymore 

Request - http://localhost:8080/customer/10001/ad/1000 

Response - 

{
    "timestamp": "2019-09-10T09:10:37.627+0000",
    "message": "Could not find ad with id: 1,000",
    "details": "uri=/customer/10001/ad/1000"
}

###

