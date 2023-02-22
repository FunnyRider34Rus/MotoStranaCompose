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
    alias(libs.plugins.gradleAppID) apply false
    alias(libs.plugins.gradleLibID) apply false
    alias(libs.plugins.gradleKotlinID) apply false
}