import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.0"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	kotlin("plugin.jpa") version "1.6.21"
}

group = "br.com.creditas"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.flywaydb:flyway-core")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation ("org.springframework.cloud:spring-cloud-starter-openfeign:3.1.3")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("org.postgresql:postgresql")
	implementation("io.swagger:swagger-annotations:1.5.0")
	implementation("io.springfox:springfox-swagger-ui:2.9.2")
	implementation("io.springfox:springfox-swagger2:2.9.2")


	//  Test and lint dependencies
	testRuntimeOnly("com.h2database:h2")
	testImplementation("org.testcontainers:postgresql:1.16.2")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.amshove.kluent:kluent:1.68")
	testImplementation("com.ninja-squad:springmockk:2.0.0")
	testImplementation("io.mockk:mockk:1.10.0")
	testImplementation("com.github.tomakehurst:wiremock:2.19.0")
	testImplementation("com.tngtech.archunit:archunit-junit5-engine:0.14.1")
	testImplementation("io.zonky.test:embedded-database-spring-test:1.6.3")
	testImplementation("com.tngtech.archunit:archunit-junit5:0.14.1")
	testImplementation ("org.testcontainers:junit-jupiter:1.17.3")
	implementation ("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation ("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.3")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
