plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.gms.google-services")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.motostranacompose"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.example.motostranacompose"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
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
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()

    }
}

dependencies {

    //Core
    implementation(platform(libs.composeBOM))
    implementation(libs.composeUI)
    implementation(libs.composePreview)
    implementation(libs.composeM3)

    //Hilt
    implementation(libs.hilt)
    kapt(libs.hiltKapt)

    //Navigation
    implementation(libs.navigation)
    implementation(libs.navigationHilt)

    //Firebase
    implementation(platform(libs.firebaseBOM))
    implementation(libs.firebaseAUTH)
    implementation(libs.firebaseRealtimeDatabase)
    implementation(libs.firebaseFirestore)
    implementation(libs.firebaseStorage)
    //implementation(libs.firebaseCrashlytics)

    //Play Services Auth
    implementation(libs.playservicesAUTH)
    
    //Google Fonts
    implementation(libs.googleFont)

    //Coroutines
    implementation(libs.coroutinesCore)
    implementation(libs.coroutinesAndroid)
    implementation(libs.coroutinesPlayservices)

    //Tests
    testImplementation(libs.testJUnit)
    androidTestImplementation(libs.testJUnitUI)
    debugImplementation(libs.testUITooling)
}

kapt {
    correctErrorTypes = true
}