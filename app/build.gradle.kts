plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.kapt")
    id("com.google.gms.google-services")


}

android {
    namespace = "com.example.robbllezze"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.robbllezze"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
//    ROOM LIBRARY: LOCAL DATABASE MANAGEMENT
    implementation("androidx.room:room-runtime:2.7.1")
    implementation("androidx.room:room-ktx:2.7.1")


    kapt("androidx.room:room-compiler:2.7.1")

    //NAVIGATION
    implementation("androidx.navigation:navigation-compose:2.7.7")
    //hilt
    //hilt core
    implementation("com.google.dagger:hilt-android:2.56.1")
    kapt("com.google.dagger:hilt-android-compiler:2.56.1")
//    kapt("com.google.dagger.hilt-android-compiler:2.48")
    //for viewModel support
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    //IF YPU USE rOMM OR OTHER JETPACK COMPONENTS
//    kapt("androidx.hilt:hilt-compiler:1.2.0")
    implementation(platform("com.google.firebase:firebase-bom:33.13.0"))
    implementation("com.google.firebase:firebase-analytics")
//    firebase authentication
    implementation("com.google.firebase:firebase-auth-ktx")
//    FIREBASE DATABASE
    implementation("com.google.firebase:firebase-database-ktx")
    //firebase storage
    implementation("com.google.firebase:firebase-storage-ktx")
// coil : image loader
    implementation("io.coil-kt:coil-compose:2.4.0")

    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}