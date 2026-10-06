plugins {
    id("java")
    application
}
application {
    mainClass.set("se.chasacademy.fjax26.smarthome.Main")
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
    testImplementation("org.mockito:mockito-junit-jupiter:5.23.0")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "se.chasacademy.fjax26.smarthome.Main"
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<JavaExec> {
    systemProperty("file.encoding", "UTF-8")
}
tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}