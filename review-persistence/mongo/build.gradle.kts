plugins {
	id("org.springframework.boot")
	id("io.spring.dependency-management")
	kotlin("jvm")
	kotlin("plugin.spring")
}

dependencies {
	testImplementation("de.flapdoodle.embed:de.flapdoodle.embed.mongo")
}