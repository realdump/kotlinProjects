plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.example.compass"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.compass"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.compose.ui:ui:1.6.5")
    implementation("androidx.compose.material:material:1.6.5")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
}