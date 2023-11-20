import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    application
    id("jacoco")
    id("checkstyle")
    id("io.freefair.lombok") version "8.3"
    id("com.github.ben-manes.versions") version "0.47.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("java")
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("hexlet.code.App")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jsoup:jsoup:1.16.2")
    implementation("com.h2database:h2:2.2.222")
    implementation("org.postgresql:postgresql:42.6.0")
    implementation("com.zaxxer:HikariCP:5.0.1")
    implementation("com.konghq:unirest-java:3.14.5")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")
    implementation("org.apache.commons:commons-text:1.10.0")
    implementation("gg.jte:jte:3.1.0")
    implementation("org.slf4j:slf4j-simple:2.0.9")
    implementation("io.javalin:javalin:5.6.2")
    implementation("io.javalin:javalin-bundle:5.6.2")
    implementation("io.javalin:javalin-rendering:5.6.2")

    testImplementation("com.squareup.okhttp3:mockwebserver:4.11.0")
    testImplementation("org.assertj:assertj-core:3.24.2")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        exceptionFormat = TestExceptionFormat.FULL
        events = mutableSetOf(TestLogEvent.FAILED, TestLogEvent.PASSED, TestLogEvent.SKIPPED)
        showStandardStreams = true
        finalizedBy(tasks.jacocoTestReport)
    }
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required = true
    }
}
