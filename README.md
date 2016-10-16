# userMicroservice

Microservice application built with Spring. CRUD operations with Users

## Clone and SetUp

Better way should be cloning this repo to a local folder and opening it from Eclipse or IntelliJ as a maven project

## Command line:

   1. From command line or IDE: 
   * `mvn clean package` 
   1. Start registration server 
   * `cd target`
   * `java -jar user-mservice-1.0.jar registration`
   * `http://localhost:1111` in a web browser should open Eureka status
  
   1. Start Users microservice 
   * `cd target`
   * `java -jar user-mservice-1.0.jar users`
  
   
   1. Start administration microservice
   * `cd target`
   * `java -jar user-mservice-1.0.jar administration`
   
## IDE:

From IntelliJ we can create 3 new Java application Configurations. 
1. Main Class: org.mservice.server.Main. Program arguments: registration
2. Main Class: org.mservice.server.Main. Program arguments: users
2. Main Class: org.mservice.server.Main. Program arguments: administration

All should be started in this order.

##Start
If all is up. We should be able to acess administration from any browser with: 
`http://localhost:3333`

   
 
