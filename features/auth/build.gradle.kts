plugins {
    java
    kotlin("jvm") version "1.9.22"
    kotlin("kapt")
    kotlin("plugin.spring") version "1.9.22"
    kotlin("plugin.jpa") version "1.9.22"

    id("io.spring.dependency-management") version "1.1.4"
    id("io.freefair.lombok") version "8.10"
    id("org.jetbrains.kotlin.plugin.lombok") version "1.5.20-RC"
}

kotlinLombok {
    lombokConfigurationFile(file("lombok.config"))
}

kapt {
    keepJavacAnnotationProcessors = true
}

dependencies {
    implementation(project(":core:api"))
    implementation("io.jsonwebtoken:jjwt:0.12.5")
//    testImplementation(project(":core:testing"))
//    kapt("org.projectlombok:lombok:1.18.34")
//    annotationProcessor("org.projectlombok:lombok:1.18.34")
}
