import java.util.Properties

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

val localProps = Properties()
val localProperties = File(rootProject.rootDir, "local.properties")
if (localProperties.exists() && localProperties.isFile) {
    localProperties.inputStream().use { input ->
        localProps.load(input)
    }
}

android {
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()

        val databaseUrl = checkNotNull(localProps.getProperty("database.url") ?: System.getenv("DATABASE_URL"))
        buildConfigField("String","DATABASE_URL", "\"$databaseUrl\"")

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    api(project(":core:domain"))

    api(platform(libs.google.firebase.bom))
    api(libs.google.firebase.database)
    api(libs.google.firebase.analytics)
    api(libs.google.firebase.auth)
    api(libs.google.firebase.crashlytics)
    api(libs.androidx.datastore.core)
    api(libs.androidx.datastore.preferences)

    api(libs.dagger.runtime)
    kapt(libs.dagger.compiler)
}