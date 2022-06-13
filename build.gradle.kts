buildscript {

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()

    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.2.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${libs.versions.kotlin.get()}")
        classpath("com.google.gms:google-services:4.3.10")
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.9.0")
        classpath(kotlin("serialization", version = libs.versions.kotlin.get()))
    }
}

allprojects {
    repositories {
        mavenCentral()
        google()
        maven { url = uri("https://jitpack.io") }
    }
}
plugins {
    id("io.gitlab.arturbosch.detekt").version("1.20.0").apply(true)

}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
