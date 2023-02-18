buildscript {
    val kotlin_version by extra("1.8.10")
    dependencies {
        classpath("com.google.gms:google-services:4.3.15")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.44.2")
    }
    repositories {
        mavenCentral()
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.0-alpha05" apply false
    id("com.android.library") version "8.1.0-alpha05" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
}