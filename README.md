# This Repository is a collection of java spring boot maven projects
These are simple maven spring boot java projects to help beginners to learn different microservices design principles

## Softwares required
1. JDK 17
2. maven 3.8.6
3. spring boot 3.2.3

## Steps to run the projects
1. First run DiscoverService and then run others, just to avoid any connection related errors
2. Use any IDE such as STS or eclipse or IntelliJ
3. Clone any of the repo and run as spring boot app and hit the APIs in swagger end point

## Swagger end points
1. UserService - http://localhost:8085/swagger-ui/index.html
2. GalleryService - http://localhost:8086/swagger-ui/index.html

## Actuator end points
1. UserService - http://localhost:8085/actuator
2. GalleryService - http://localhost:8086/actuator
3. GatewayService - http://host.docker.internal:8011/actuator

## NOTE
1. When you generate docker image and run these apps as containers on localhost, please use the application-cont profile, otherwise you will run into some connection issues between the containers. By the way dockerfile is added in all services which work fine without any issues
2. If you run into initial setup issues, better you look at the JAVA_HOME, MAVEN_HOME and any other routine env issues
3. When you install docker desktop, please go for a prior version. With latest I faced some connection and build issues