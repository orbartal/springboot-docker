# springboot-docker

### Description

A  springboot docker demo project with 1 docker only.

### Instructions on how to build/run the backend locally 

How to build, test, run and use the project server

Download the project from its github [repo](https://github.com/orbartal/springboot-docker)
You can downalod it as zip and extract it. Or use git clone.

Install java 17 from [oracle](https://www.oracle.com/java/technologies/downloads/#java17) 

Install maven, on any OS, using [baeldung](https://www.baeldung.com/install-maven-on-windows-linux-mac) guide. 

In the terminal (or cmd) cd into dir "..\springboot-docker\backend" and run:

mvn clean install

mvn spring-boot:run

Open url in browser and use the [swagger-ui](http://localhost:8080/swagger-ui/index.html)

### Instructions on how to build/run the docker 

Install docker on your host.

In the terminal (or cmd) cd into dir "..\springboot-docker\backend" and run:

docker build -t orbartal/spring-swagger-demo .

docker run -p 8080:8080 orbartal/spring-swagger-demo -d 

Open url in browser and use the [swagger-ui](http://localhost:8080/swagger-ui/index.html)