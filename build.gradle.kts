import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
	repositories {
		maven { url = uri("https://plugins.gradle.org/m2/") }
		mavenCentral()
	}

	dependencies {
		classpath("io.spring.gradle:dependency-management-plugin:1.0.10.RELEASE")
	}
}

plugins {
	id("org.springframework.boot") version "2.6.1"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.0-RC"
	kotlin("plugin.spring") version "1.6.0-RC"
}

group = "dev.milzipmoza"
version = "20220212"
java.sourceCompatibility = JavaVersion.VERSION_11

allprojects {
	apply(plugin = "kotlin")

	repositories {
		mavenCentral()
		maven { url = uri("https://repo.spring.io/milestone") }
		maven { url = uri("https://repo.spring.io/snapshot") }
	}

	dependencies {
		implementation("org.jetbrains.kotlin:kotlin-reflect")
		implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

		testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
		testImplementation("org.junit.jupiter:junit-jupiter-engine:5.8.1")
	}

	tasks.withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict")
			jvmTarget = "11"
		}
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}
}
