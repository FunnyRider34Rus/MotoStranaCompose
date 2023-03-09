plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.gms.google-services")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.motostranacompose"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.motostranacompose"
        minSdk = 22
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.2"

    }
}

dependencies {

    //Core
    implementation("androidx.core:core-ktx:1.9.0")
    implementation(platform("androidx.compose:compose-bom:2022.10.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("com.google.android.gms:play-services-auth:20.4.1")

    //Hilt
    val hilt_version = "2.45"
    implementation("com.google.dagger:hilt-android:$hilt_version")
    kapt("com.google.dagger:hilt-compiler:$hilt_version")

    //Navigation
    val nav_version = "2.5.3"
    implementation("androidx.navigation:navigation-compose:$nav_version")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    //Firebase
    implementation(platform("com.google.firebase:firebase-bom:31.2.2"))
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-database-ktx")
    implementation("com.google.firebase:firebase-firestore-ktx")
    implementation("com.google.firebase:firebase-storage-ktx")
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-appcheck-playintegrity")
    //implementation("com.google.firebase:firebase-crashlytics-ktx")

    //Play Services Auth
    implementation("com.google.android.gms:play-services-auth:20.4.1")

    //Google Fonts
    implementation("androidx.compose.ui:ui-text-google-fonts:1.3.3")

    //val Google Icon
    implementation("androidx.compose.material:material-icons-core:1.3.1")
    implementation("androidx.compose.material:material-icons-extended:1.3.1")

    //Coroutines
    val coroutines_version = "1.6.4"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:$coroutines_version")

    //Coil
    val coil_version = "2.2.2"
    implementation("io.coil-kt:coil-compose:$coil_version")
    implementation("com.github.skydoves:landscapist-coil:2.1.5")

    //Effects
    implementation("com.github.skydoves:landscapist-transformation:2.1.5")

    //WebView
    implementation("com.google.accompanist:accompanist-webview:0.29.1-alpha")

    //Tests
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2022.10.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}

kapt {
    correctErrorTypes = true
}