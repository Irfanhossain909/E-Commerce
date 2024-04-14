buildscript {
    repositories {
        google()
        maven("https://www.jitpack.io")

    }
    dependencies {
        val nav_version = "2.7.7"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
        classpath(libs.google.services)
        classpath("com.google.gms:google-services:4.4.1")

    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.google.gms.google-services") version "4.4.1" apply false

    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
}