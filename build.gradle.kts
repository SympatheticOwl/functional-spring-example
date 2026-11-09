import org.springframework.boot.gradle.plugin.SpringBootPlugin

plugins {
	java
	id("org.springframework.boot") version "4.0.9"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "org.ldamler"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(23)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	annotationProcessor(platform(SpringBootPlugin.BOM_COORDINATES))
	implementation(platform(SpringBootPlugin.BOM_COORDINATES))
	implementation(platform("org.springframework.cloud:spring-cloud-dependencies:2025.1.4"))

	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-restclient")
	implementation("io.github.wimdeblauwe:htmx-spring-boot:6.0.1")
	implementation("org.apache.commons:commons-lang3:3.21.0")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:3.2.0")

	compileOnly("org.projectlombok:lombok")
//	developmentOnly("org.springframework.boot:spring-boot-devtools")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	annotationProcessor("org.projectlombok:lombok")

	testAnnotationProcessor(platform(SpringBootPlugin.BOM_COORDINATES))
	testImplementation(platform(SpringBootPlugin.BOM_COORDINATES))
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
