# Coral Marketplace backend
This project has been created with [Spring Boot](https://spring.io/projects/spring-boot).

## System requirements
- Java 11
- Maven 3.5+

## Run the application using embedded Tomcat
Generate sources
```sh
mvn generate-sources
```

The application can be run in an embedded Tomcat servlet container. To do so generate a new JAR file with the command
```sh
mvn clean install
```
A new JAR file will be generated in _/target_.

Then run the application with the command
```sh
java -jar coral-marketplace-1.0.0.jar
```

## Deployment
Package the application in a JAR file using Maven command and then deployed in a servlet container or a Docker container.
```sh
mvn clean package
```

## License

Distributed under the MIT License. See [LICENSE](LICENSE) for more information.
