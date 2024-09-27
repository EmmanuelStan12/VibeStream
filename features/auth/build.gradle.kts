plugins {
    id("org.springframework.boot") version "3.2.2"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.spring") version "1.9.22"
    kotlin("plugin.jpa") version "1.9.22"
    id("io.freefair.lombok") version "8.10"
}

dependencies {
    implementation(project(":core:api"))
    implementation("io.jsonwebtoken:jjwt:0.12.5")
//    testImplementation(project(":core:testing"))
}