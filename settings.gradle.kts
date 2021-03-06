pluginManagement {
	repositories {
		maven { url = uri("https://repo.spring.io/milestone") }
		maven { url = uri("https://repo.spring.io/snapshot") }
		gradlePluginPortal()
	}

	plugins {
		id("org.springframework.boot") version "2.6.1"
		id("org.springframework.cloud") version "2.6.1"
		id("io.spring.dependency-management") version "1.0.11.RELEASE"

		kotlin("jvm") version "1.6.0-RC"
		kotlin("plugin.spring") version "1.6.0-RC"
	}
}
rootProject.name = "review"
include(
		"review-domain",
		"review-util",
		"review-application",
		"review-application:api",
		"review-persistence",
		"review-persistence:mongo",
		"review-external",
		"review-external:naver-api",
		"review-external:github-api",
		"review-external:slack-api",
)