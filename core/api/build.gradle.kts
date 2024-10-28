/*
* 1. kotlin("jvm"):
    This applies the Kotlin JVM plugin.
    It allows you to write Kotlin code that targets the Java Virtual Machine (JVM).
    This plugin adds support for compiling Kotlin code and running it on the JVM.
* 2. id("org.springframework.boot") version "3.3.1":
    This applies the Spring Boot plugin with the specified version.
    The Spring Boot plugin provides tasks and configurations to easily create Spring Boot applications.
    It helps in packaging the application, managing dependencies, and setting up the classpath.
* 3. id("io.spring.dependency-management") version "1.1.5":
    This applies the Spring Dependency Management plugin.
    It allows you to use a dependency management section within your build.gradle.kts file, similar to how you would in a Maven pom.xml file.
    This is useful for managing versions of dependencies across all subprojects consistently.
* 4. kotlin("plugin.spring") version "1.9.24":
    This applies the Kotlin Spring plugin.
    It makes working with Spring Framework in Kotlin easier by adding additional compiler support and features.
    This includes things like opening Kotlin classes and methods for Spring’s reflection-based APIs.
* 5. kotlin("plugin.allopen") version "1.9.24":
    This applies the Kotlin All-Open plugin.
    It configures the Kotlin compiler to automatically open classes and methods that are annotated with specific annotations.
    This is particularly useful in a Spring context where classes and methods often need to be open for proxies and other reflection-based features.
**/

/*
* In Spring Boot Starter Web, the Starter JSON module includes Jackson and Spring Web-related libraries because they are essential for handling JSON data and web requests in modern Spring applications. Let’s break down the components:

1. Jackson Databind (com.fasterxml.jackson.core):
Purpose: Jackson Databind is the core library for converting between Java objects and JSON (serialization and deserialization).
Why included: Web applications often deal with JSON for communication between the client and the server. Jackson is the most widely used library for handling JSON in Java. When you send or receive JSON in a Spring Web app, Jackson is responsible for converting Java objects to JSON and vice versa.
2. Jackson Datatype JDK8 (com.fasterxml.jackson.datatype):
Purpose: This module provides support for additional types introduced in Java 8, such as Optional.
Why included: Java 8 introduced new data types like Optional, which are common in modern Java applications. To handle these types properly during serialization/deserialization, Jackson needs this module to ensure smooth handling of Optional types in JSON payloads.
3. Jackson Datatype JSR310 (com.fasterxml.jackson.datatype):
Purpose: This module provides support for the Java 8 Date and Time API (JSR 310), like LocalDate, LocalDateTime, ZonedDateTime, etc.
Why included: The new date and time API in Java 8 is widely used in modern applications, and Jackson needs this module to properly serialize and deserialize these types. Without this, you might encounter issues when trying to handle date and time objects in JSON.
4. Jackson Module Parameter Names (com.fasterxml.jackson.module):
Purpose: This module allows Jackson to use parameter names directly from constructors and methods, without needing annotations like @JsonProperty.
Why included: It simplifies the JSON serialization/deserialization process by inferring parameter names from the method or constructor signatures directly, improving the readability of your code and reducing the need for explicit annotations. It's particularly useful for immutable objects with constructors.
* */

/*
*
1. Hibernate Core (org.hibernate.orm:hibernate-core:6.6.1.Final)
Purpose: Hibernate is the underlying ORM (Object-Relational Mapping) framework used by Spring Data JPA to map Java objects to database tables.
Why included: Spring Data JPA uses Hibernate as the default JPA provider to perform CRUD operations and manage entities. Hibernate allows you to define mappings between Java classes and database tables, and it handles the database interactions behind the scenes.
2. Spring Aspects (org.springframework:spring-aspects:6.1.13)
Purpose: This provides support for aspect-oriented programming (AOP) in Spring.
Why included: Spring Aspects allows you to use AOP, which is useful for cross-cutting concerns like transaction management, logging, and security. For instance, transaction management in Spring JPA is often implemented using AOP, allowing you to define @Transactional annotations without manually managing transactions.
3. Spring Data JPA (org.springframework.data:spring-data-jpa:3.3.4)
Purpose: Spring Data JPA simplifies data access by providing a high-level abstraction on top of JPA (typically Hibernate) for easier repository creation and database interaction.
Why included: This is the core library for working with JPA in a Spring application. It provides repository abstractions that make it easier to interact with your database through JPA (using Hibernate under the hood), handling the boilerplate code like entity management, query creation, and data retrieval.
4. Spring JDBC (org.springframework:spring-jdbc:6.1.13)
Purpose: Spring JDBC provides utilities for interacting with relational databases using raw JDBC (Java Database Connectivity).
Why included: Even though you’re using Spring Data JPA, sometimes direct JDBC operations are required for more complex or optimized queries. Spring JDBC provides a simplified approach to managing these connections, executing SQL queries, and mapping result sets. It is often used for advanced data access or fallback when JPA does not provide sufficient control.
5. HikariCP (com.zaxxer:HikariCP:6.0.0)
Purpose: HikariCP is a high-performance JDBC connection pool used to manage database connections efficiently.
Why included: In any database-driven application, managing database connections is crucial for performance. HikariCP is included as the default connection pool for Spring applications (and JPA/Hibernate setups) because of its speed and resource efficiency. It helps your application maintain a pool of database connections, reducing the overhead of creating new connections and ensuring efficient database access.

* Hibernate Core: Provides the JPA implementation (Hibernate) for interacting with the database.
Spring Aspects: Handles cross-cutting concerns like transactions that can affect multiple parts of your application.
Spring Data JPA: The main component that allows you to use JPA (via Hibernate) with Spring’s repository abstractions and simplifies data access.
Spring JDBC: Provides an optional layer for low-level database access using JDBC directly, useful in scenarios where JPA might not be sufficient.
HikariCP: Efficiently manages database connections to optimize performance and reduce overhead.
*
* */

plugins {
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.spring") version "1.9.22"
    kotlin("plugin.jpa") version "1.9.22"
    java
}

sourceSets {
    main {
        java {
            setSrcDirs(listOf("src/main/java", "src/main/kotlin"))
        }
        resources {
            setSrcDirs(listOf("src/main/resources"))
        }
    }
    /*test {
        java {
            setSrcDirs(listOf("src/main/java", "src/main/kotlin"))
        }
        resources {
            setSrcDirs(listOf("src/main/resources"))
        }
    }*/
}

dependencies {
    api(kotlin("stdlib"))
    /**
     * Provides annotations from the Jakarta EE framework, such as @PostConstruct, @PreDestroy, @Resource, etc.
     * These are used by Spring for lifecycle management of beans and resource injection.
     */
    // https://mvnrepository.com/artifact/jakarta.annotation/jakarta.annotation-api
    api("jakarta.annotation:jakarta.annotation-api:3.0.0")

    /**
     * This is the core module of the Spring Framework. It provides essential utilities and infrastructure,
     * including dependency injection, core utilities, reflection, etc. spring-core is mandatory for
     * all Spring-based applications (whether you use Spring Boot or raw Spring Framework).
     */
    // https://mvnrepository.com/artifact/org.springframework/spring-core
     api("org.springframework:spring-core:6.1.13")

    /**
     * The dependency org.springframework:spring-context is essential when working with the Spring Framework,
     * whether you're using Spring Boot or raw Spring. It provides the core features for dependency injection,
     * event handling, and AOP (Aspect-Oriented Programming).
     */
    // https://mvnrepository.com/artifact/org.springframework/spring-context
    api("org.springframework:spring-context:6.1.13")

    // https://mvnrepository.com/artifact/ch.qos.logback/logback-classic
    api("ch.qos.logback:logback-classic:1.5.8")

    // https://mvnrepository.com/artifact/org.springframework.security/spring-security-core
    api("org.springframework.security:spring-security-core:6.3.3")

    // https://mvnrepository.com/artifact/org.springframework.security/spring-security-web
    api("org.springframework.security:spring-security-web:6.3.3")

    // https://mvnrepository.com/artifact/org.springframework.security/spring-security-config
    api("org.springframework.security:spring-security-config:6.3.3")

    // https://mvnrepository.com/artifact/org.springframework/spring-webmvc
    api("org.springframework:spring-webmvc:6.1.13")
    // https://mvnrepository.com/artifact/org.springframework/spring-web
    api("org.springframework:spring-web:6.1.13")

    // https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind
    api("com.fasterxml.jackson.core:jackson-databind:2.17.2")
    // https://mvnrepository.com/artifact/com.fasterxml.jackson.datatype/jackson-datatype-jdk8
    api("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.17.2")
    // https://mvnrepository.com/artifact/com.fasterxml.jackson.datatype/jackson-datatype-jsr310
    api("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.17.2")
    // https://mvnrepository.com/artifact/com.fasterxml.jackson.module/jackson-module-parameter-names
    api("com.fasterxml.jackson.module:jackson-module-parameter-names:2.17.2")

    // https://mvnrepository.com/artifact/org.hibernate.orm/hibernate-core
    api("org.hibernate.orm:hibernate-core:6.6.1.Final")
    // https://mvnrepository.com/artifact/org.springframework/spring-aspects
    api("org.springframework:spring-aspects:6.1.13")
    // https://mvnrepository.com/artifact/org.springframework.data/spring-data-jpa
    api("org.springframework.data:spring-data-jpa:3.3.4")
    // https://mvnrepository.com/artifact/org.springframework/spring-jdbc
    api("org.springframework:spring-jdbc:6.1.13")
    // https://mvnrepository.com/artifact/com.zaxxer/HikariCP
    api("com.zaxxer:HikariCP:6.0.0")

    // https://mvnrepository.com/artifact/org.hibernate.validator/hibernate-validator
    api("org.hibernate.validator:hibernate-validator:8.0.1.Final")

    // https://mvnrepository.com/artifact/org.postgresql/postgresql
    implementation("org.postgresql:postgresql:42.7.4")

    api("org.jetbrains.kotlin:kotlin-reflect")
    api("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    api("jakarta.servlet:jakarta.servlet-api:5.0.0")
}
