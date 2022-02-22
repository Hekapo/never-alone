plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("io.gitlab.arturbosch.detekt")
    id("dagger.hilt.android.plugin")
}

kapt {
    correctErrorTypes = true
    useBuildCache = true
}

dependencies {
    implementation(libs.dagger.hilt.android)
    kapt(libs.dagger.hilt.compiler)
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "ru.itis.neveralone"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.tagetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        jvmTarget = "1.8"
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
    implementation(libs.androidx.appcompat)
    implementation(libs.google.material)
    implementation(libs.androidx.navigation.compose)

    implementation(libs.bundles.androidx.compose)
    implementation(libs.google.accompanist.insets)

    implementation(libs.bundles.androidx.lifecycle)

    testImplementation(libs.junit4)

    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.bundles.androidx.test.espresso)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)

    debugImplementation(libs.androidx.compose.ui.tooling)

    implementation(libs.androidx.hilt.navigation.compose)
}
