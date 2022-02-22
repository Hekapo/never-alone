buildscript {

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.1.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${libs.versions.kotlin.get()}")
        classpath("com.google.gms:google-services:4.3.10")
        classpath(kotlin("serialization", version = libs.versions.kotlin.get()))
        classpath(libs.dagger.hilt.agp)

    }
}

plugins {
    id("io.gitlab.arturbosch.detekt") version("1.19.0") apply(false)
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
