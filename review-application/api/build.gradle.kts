import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
	id("org.springframework.boot")
	id("io.spring.dependency-management")
	kotlin("jvm")
	kotlin("plugin.spring")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:2021.0.0")
	}
}

dependencies {
	implementation(project(":review-domain"))
	implementation(project(":review-util"))
	implementation(project(":review-persistence:mongo"))
	implementation(project(":review-external:naver-api"))
	implementation(project(":review-external:github-api"))

	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.jsonwebtoken:jjwt:0.9.1")
	implementation("javax.xml.bind:jaxb-api:2.3.1")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.boot:spring-boot-starter-webflux")
}

tasks.getByName<BootJar>("bootJar") {
	enabled = true
	mainClass.set("dev.milzipmoza.review.ReviewApiApplicationKt")
}

tasks.getByName<Jar>("jar") {
	enabled = false
}