pluginManagement {
	repositories {
		maven { url = uri("https://repo.spring.io/milestone") }
		maven { url = uri("https://repo.spring.io/snapshot") }
		gradlePluginPortal()
	}
}
rootProject.name = "review"
include(
		"review-domain",
		"review-application",
		"review-application:api",
		"review-persistence",
		"review-persistence:mongo"
)