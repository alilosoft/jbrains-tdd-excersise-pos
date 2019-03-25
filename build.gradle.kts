import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.21"
}

object Version {
    const val junit5 = "5.4.0"
    const val spek2 = "2.0.1"
    const val kotlintest = "3.3.1"
}

group = "dev.elegantcode.learning"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    // junit5
    testImplementation("org.junit.jupiter:junit-jupiter-api:${Version.junit5}")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${Version.junit5}")

    // include Spek2 test framework
    testImplementation("org.spekframework.spek2:spek-dsl-jvm:${Version.spek2}")
    testRuntimeOnly("org.spekframework.spek2:spek-runner-junit5:${Version.spek2}")

    // kotlintest assertions
    testImplementation("io.kotlintest:kotlintest-assertions:${Version.kotlintest}")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.test {
    useJUnitPlatform()
}
