# Programming Exercise for Campspot
## Jeff Orthober Jr - 2017-10-29

## Execution and Building

### How to exercise endpoint remotely
The application is implemented as a microservice and is hosted live at
jefforthoberjr.sloppy.zone

Note: Hosting for this site is only temporary; this endpoint will no longer work after 2017-11-29.

Make a POST REST call to the /query endpoint.
jefforthoberjr.sloppy.zone/query

The body of the request should contain JSON, using the format described via the email instructions.

There is also an example test-case.json provided in this repository, which can be used to exercise the /query endpoint.

This repository also contains STSCampLogic.postman_collection , which can be imported into Postman. This collection of example REST calls also includes fully populated JSON bodies.


### Response format

A response from the /query endpoint will be in the following JSON format:
Example:

[
    {
        "name": "FOOBAR CAMP SITE A"
    },
    {
        "name": "FOOBAR CAMP SITE B"
    }
]

The email instructions were a bit loose on the response format, so I chose this JSON format (as opposed to plain/text response). Currently the JSON only contains a list of camp site names, but I feel this format allows for future flexibility, in case the the output format requirements become more complex.


### How to exercise endpoint locally (via docker)
The entire web application is encapsulated in a docker image, and housed in a public docker hub repository. Any system with docker install will be able to download and run the image via the command-line.
Example:
docker pull pretzels1337/stscamplogic:latest
docker run -i -p 1234:8080 pretzels1337/stscamplogic:latest
//This run command will start a local instace of Tomcat, ready to receive REST calls to localhost on the specifed port
//In this example a port mapping is made from the host machine's port 1234 to the docker container port 8080
//Note: Running with the -i flag will allow the display of log output from tomcat. Useful for debugging.


### How to exercise endpoint locally (via jar)
The primary artifact for this project is an uberjar. This repository container a pre-built version of the jar. Any system with java version 8u151 installed can download and run this jar via the command-line.
Example:
java -jar ~/Downloads/STSCampLogic-1.0.0-SNAPSHOT.jar orthober.jeff.STSCampLogic.App
//This command will start a local instance of Tomcat, ready to receive REST calls to localhost on port 8080


### How to exercise endpoint locally (via STS IDE)
The source can be compiled and executed on any system with the STS IDE installed. Download the whole repository, and import the project into STS (as a 'maven project'). After importing the project, right click on the project and select Maven > Update Project (in order to download all the library dependencies). Setup a run configuration (Java Application) with uses the Main class orthober.jeff.STSCampLogic . Click Run.
Running via IDE will start a local instace of Tomcat, ready to receive REST calls to localhost on port 8080


### How to build jar from source code (via STS IDE)
This project is managed using Maven. The STS IDE comes pre-packaged with Maven. Download the whole repository, and import the project into STS (as a 'maven project'). Setup a run configuration (Maven Build), select the project's directory for "Base Directory" and enter "package" under Goals. Click Run.


### How to execute junit test cases (via STS IDE)
The Junit test cases are automatically run when maven packages a jar. Otherwise the test can be triggered manually from within the IDE. In the Package Explorer tab, right click on the src/test/java directory > Run As > JUnit test.


## Architecture and Technologies Used

Developed on a MacBook Pro
IDE: [STS (Spring Tool Suite) v9.3.1](https://spring.io/tools)
Written in Java, [JDK v1.8 (8u151)](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
Depencies managed using [Maven 4.0.0](https://maven.apache.org/pom.html)
Web framwork: Spring
Spring Boot 1.5.8
(Tomcat 8.5.23)
(Spring Framework 4.3.12)
(Jackson 2.8.10)
Git and [Github](https://github.com/) used for repo management
Containerized using docker and [DockerHub](https://hub.docker.com/r/pretzels1337/stscamplogic/builds/)

Build and deployment process:
1) Hack out code in STS IDE
2) Commit src code to repo
3) Build uberjar and commit it to repo
4) Automated build trigger setup between github and dockerhub. Automatically builds a new docker image with the latet uberjar
5) Container hosting on sloppy.io automatically grabs the latest version of the docker image





