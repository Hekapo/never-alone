buildscript {

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()

    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.1.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${libs.versions.kotlin.get()}")
        classpath("com.google.gms:google-services:4.3.10")
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.8.1")
        classpath(kotlin("serialization", version = libs.versions.kotlin.get()))
    }
}

allprojects {
    repositories {
        mavenCentral()
        google()
    }
}
plugins {
    id("io.gitlab.arturbosch.detekt") version("1.19.0") apply(false)
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
