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
	implementation("org.springframework.cloud:spring-cloud-starter-openfeign")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
}