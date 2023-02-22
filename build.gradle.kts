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
    id("com.android.application") version "7.4.1" apply false
    id("com.android.library") version "7.4.1" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
}