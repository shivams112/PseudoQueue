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
###### receiver.uri=http://127.0.0.1:8080/pseudoqueue/receiver
`and then simply follow the below steps:` <br />
There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.natwestgroup.challenge` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Application REST APIs

There are only two REST APIs in this application, both of them are POST type which expects JSON body.

`/pseudoqueue/sender` <br />
`/pseudoqueue/receiver`

### Request Body Format

```shell
{
	"accountNumber": "1234",
	"type": "credit",
	"amount":"1000",
	"currency":"INR",
	"accountFrom":"9876543"
}
```

### API Response 
#### Success
```shell
{
    "resultCode": 0,
    "result": "success"
}
```
#### Failure

```shell
{
    "resultCode": 1,
    "result": "Fail"
}
```

### Application flow 
User will hit `/pseudoqueue/sender` API with transaction data and then application will pocess it by encrypting (base64) the data and then calling receiver API internally.
Now, `/pseudoqueue/receiver` API will decrypt the data and save it to database.

## Deployment details

The application has been deployed on AWS. You can check out from below URL =>
#### http://natwestchallenge-env.eba-pb22h8uy.us-west-1.elasticbeanstalk.com/pseudoqueue

### Live Application Database details
I am also providing database details of live application, so that anyone can check whether the correct data is going or not. This is
just for testing purpose
```shell
DB IP: database-1.cnmk451yynwm.us-west-1.rds.amazonaws.com
usernam: root
password: password
DB Name: natwest
Table Name: pseudo_transaction
