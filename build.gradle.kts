plugins {
    java
    kotlin("jvm") version "1.3.72"
}

group = "com.expansemc"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    // SpongeAPI
    maven("https://repo-new.spongepowered.org/repository/maven-public/")
    // Configurate
    maven("https://repo.spongepowered.org/maven")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    implementation(project(":township-api"))

    compileOnly("org.spongepowered:spongeapi:8.0.0-SNAPSHOT")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}