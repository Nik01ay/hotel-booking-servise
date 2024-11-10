plugins {
	java
	id("org.springframework.boot") version "3.3.4"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "hbs"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("javax.persistence:javax.persistence-api:2.2")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	//implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
	testImplementation("org.testcontainers:junit-jupiter:1.17.6")
	testImplementation("org.testcontainers:postgresql:1.17.6")

	implementation("org.mapstruct:mapstruct:1.5.5.Final")
	annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	runtimeOnly("org.postgresql:postgresql")

}


tasks.withType<Test> {
	useJUnitPlatform()
}
