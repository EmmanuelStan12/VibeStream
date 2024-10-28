plugins {
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.spring") version "1.9.22"
    kotlin("plugin.jpa") version "1.9.22"
    java
    war
}

tasks.named<War>("war") {
    archiveBaseName.set("app")
    archiveFileName.set("app.war")
}

dependencies {
    implementation(project(":core:api"))
    implementation(project(":features:auth"))
    implementation("javax.servlet:javax.servlet-api:3.1.0")
}
