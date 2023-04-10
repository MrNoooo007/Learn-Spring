# Learn-Spring
# What is Spring Boot?
- You know, Spring Boot is a project of the Spring platform or Spring ecosystem. It is a way that aims at helping developers get started with Spring framework with minimum effort, easier and more convenient - as development with pure Spring framework is well known as boilerplate with lots of XML configuration and dependency mess.
   Spring Boot makes it easy to create stand-alone, production-grade Spring-based applications. So developers can save time and increase productivity by using Spring Boot.

# Key Features of Spring Boot
- Create stand-alone Spring applications with embedded server
- Automatic configuration of Spring and 3rd party libraries
- Furthermore, Spring Boot provides production-ready features such as metrics, health checks and externalized configuration - so developer could easily monitor their apps with ease.

# Connect Mysql to Springboot 

## 1. Add config dependencies in pom.xml: mysql / JPA / Lombok
```
		<dependency>
			<groupId>com.mysql</groupId>
			<artifactId>mysql-connector-j</artifactId>
		</dependency>

		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
```

## 2. Test connection with mysql in application.properties

```spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/learn-spring
spring.datasource.username=root
spring.datasource.password=password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.show-sql= true 
```

## 3. Add an entity file to test.

```
package com.example.learnspring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String username;

    private String fullName;
}



```
