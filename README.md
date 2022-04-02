# PseudoQueue App for Natwest



## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

Before running, you need to configure below 3 properties in application.properties file=>
###### spring.datasource.url=
###### spring.datasource.username=
###### spring.datasource.password=
###### receiver.uri=http://127.0.0.1:8080/natwest/receiver
`and then simply follow the below steps`
There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.natwestgroup.challenge` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Application end-points

There are only two end points in this application, both of them are POST

1) /natwest/sender
2) /natwest/receiver

#### Request Body Format

```shell
{
	"accountNumber": "1234",
	"type": "credit",
	"amount":"1000",
	"currency":"INR",
	"accountFrom":"9876543"
}
```
### Deployment details

The application has been deployed on AWS. You can check out from below URL =>
#### http://natwestchallenge-env.eba-pb22h8uy.us-west-1.elasticbeanstalk.com/pseudoqueue/sender
