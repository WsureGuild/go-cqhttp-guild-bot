plugins {
    kotlin("jvm") version "1.6.0"
    kotlin("plugin.serialization") version "1.6.0"
}

group = "top.wsure.guild"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    api("top.wsure.guild:wsure-guild-common:1.0-SNAPSHOT")
    implementation(kotlin("stdlib"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}