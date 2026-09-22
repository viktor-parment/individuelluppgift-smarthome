plugins {
    id("java")
}

group = "se.chasacademy.fjax26.smarthome"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.mockito:mockito-all:1.10.19")
    
}

tasks.test {
    useJUnitPlatform()
}