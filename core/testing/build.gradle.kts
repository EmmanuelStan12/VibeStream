plugins {
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.spring") version "1.9.22"
    kotlin("plugin.jpa") version "1.9.22"
    java
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    // https://mvnrepository.com/artifact/com.h2database/h2
    testApi("com.h2database:h2:2.3.232")


    // https://mvnrepository.com/artifact/org.springframework/spring-test
    testApi("org.springframework:spring-test:6.1.13")
    // https://mvnrepository.com/artifact/org.skyscreamer/jsonassert
    testApi("org.skyscreamer:jsonassert:1.5.3")
    // https://mvnrepository.com/artifact/org.mockito/mockito-junit-jupiter
    testApi("org.mockito:mockito-junit-jupiter:5.13.0")
    // https://mvnrepository.com/artifact/org.mockito/mockito-core
    testApi("org.mockito:mockito-core:5.13.0")
    // https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter
    testApi("org.junit.jupiter:junit-jupiter:5.11.1")
    // https://mvnrepository.com/artifact/org.hamcrest/hamcrest
    testApi("org.hamcrest:hamcrest:3.0")
    // https://mvnrepository.com/artifact/org.awaitility/awaitility
    testApi("org.awaitility:awaitility:4.2.2")
    // https://mvnrepository.com/artifact/org.assertj/assertj-core
    testApi("org.assertj:assertj-core:3.26.3")
}

tasks.withType<Test> {
    useJUnitPlatform()
}