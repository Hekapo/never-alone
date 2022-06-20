plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("io.gitlab.arturbosch.detekt")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

kapt {
    correctErrorTypes = true
    useBuildCache = true
}

android {
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "ru.itis.neveralone"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.tagetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.toString()
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

    buildFeatures {
        buildConfig = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.get()
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.core)

    implementation(project(":core"))
    implementation(project(":core:ui"))
    implementation(project(":core:domain"))
    implementation(project(":core:data"))
    implementation(project(":features:splash"))
    implementation(project(":features:signin"))
    implementation(project(":features:signup"))
    implementation(project(":features:main-screen"))
    implementation(project(path = ":features:settings-screen"))
    implementation(project(path = ":features:onboarding"))
    implementation(project(path = ":features:user-form"))

    implementation(platform(libs.google.firebase.bom))
    implementation(libs.google.firebase.crashlytics)

    implementation(libs.dagger.runtime)
    kapt(libs.dagger.compiler)
}
