# cmb-task
Interview task for CMB.

### The task:
Create a Spring Boot application that will have one controller with a single GET method. Method should accept an optional query parameter and call two services in parallel.
First service should have a single method that returns String as a response with a possibility to set the value of the beforementioned String at runtime using the optional query parameter. Default value of the String should be injected from application’s properties.
Second service should also have a single method returning String representation of the current date at UTC+1 in ISO 8601 format excl. time.
Controller method should then combine results of both services and return them to the caller as a custom response object with 2 attributes.
Integration should be covered by a JUnit test asserting that correct response is being returned after invocation of the controller method.

### Usage

GET - http://localhost:8080/home?queryParam=anyvalue

GET - http://localhost:8080/home
