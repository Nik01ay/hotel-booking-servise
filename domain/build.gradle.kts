plugins {
    id("java")
}

group = "com.example"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.projectlombok:lombok:1.18.34")
    implementation("javax.validation:validation-api:2.0.0.Final")
    implementation("jakarta.persistence:jakarta.persistence-api:3.2.0")
    implementation("org.springframework.data:spring-data-jpa:3.3.4")
}

tasks.test {
    useJUnitPlatform()
}