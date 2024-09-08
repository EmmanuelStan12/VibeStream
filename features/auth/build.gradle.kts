plugins {
    kotlin("jvm")
    id("org.springframework.boot") version "3.3.1"
    id("io.spring.dependency-management") version "1.1.5"
    kotlin("plugin.spring") version "1.9.24"
    kotlin("plugin.allopen") version "1.9.24"
    id("io.freefair.lombok") version "8.10"
}

dependencies {
    implementation(project(":core:api"))
    implementation("io.jsonwebtoken:jjwt:0.12.5")
}