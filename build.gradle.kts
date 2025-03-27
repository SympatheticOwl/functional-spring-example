import org.springframework.boot.gradle.plugin.SpringBootPlugin

plugins {
	java
	id("org.springframework.boot") version "3.3.5"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "org.ldamler"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
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
	implementation(platform("org.springframework.cloud:spring-cloud-dependencies:2023.0.3"))
	implementation(platform("com.fasterxml.jackson:jackson-bom:2.18.1"))

	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("io.github.wimdeblauwe:htmx-spring-boot:3.5.0")
	implementation("org.apache.commons:commons-lang3:3.17.0")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")

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
