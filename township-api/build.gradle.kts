plugins {
    java
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
    compileOnly("org.spongepowered:spongeapi:8.0.0-SNAPSHOT")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}