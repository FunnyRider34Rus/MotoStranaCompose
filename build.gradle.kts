buildscript {
    dependencies {
        classpath(libs.gradleKotlin)
        classpath(libs.gradleHilt)
        classpath(libs.gradleGMS)
    }

    repositories {
        mavenCentral()
    }
}

plugins {
    id("com.android.application") version "8.1.0-alpha06" apply false
    id("com.android.library") version "8.1.0-alpha06" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
}