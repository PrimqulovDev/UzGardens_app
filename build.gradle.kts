buildscript {

    val kotlinVersion by extra("1.4.21")

    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath("com.google.gms:google-services:4.3.4")
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.4.1")

        classpath(kotlin("gradle-plugin", version = kotlinVersion))
        classpath(kotlin("serialization", version = kotlinVersion))

        classpath("com.android.tools.build:gradle:4.1.1")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.28.3-alpha")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.3.1")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

tasks {
    val clean by registering(Delete::class) {
        delete(buildDir)
    }
}
