plugins {
	id("org.springframework.boot")
	id("io.spring.dependency-management")
	kotlin("jvm")
	kotlin("plugin.spring")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:Hoxton.SR8")
	}
}

dependencies {
	implementation(project(":review-domain"))
	implementation("org.springframework.cloud:spring-cloud-starter-openfeign")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
}