plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs")
//    id("com.google.firebase.crashlytics")
    id("kotlin-android")
    kotlin("kapt")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("plugin.serialization")
}

android {
    compileSdkVersion(30)
    buildToolsVersion = "30.0.3"
    flavorDimensions("version")

    defaultConfig {
        applicationId("app.test.uzGardens")
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 38
        versionName = "1.0.5"
        consumerProguardFiles("consumer-rules.pro")
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            buildConfigField("String", "BASE_URL", "\"http://garden.inone.uz/api/\"")
            signingConfig = signingConfigs.getByName("debug")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "$project.rootDir/tools/proguard-rules-debug.pro"
            )
        }

        named("release") {
//            buildConfigField("String", "ARCHIVE_BASE_NAME", "\"oziq-ovqat\"")
            buildConfigField("String", "BASE_URL", "\"http://garden.inone.uz/api/\"")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "$project.rootDir/tools/proguard-rules.pro"
            )
        }
    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    lintOptions {
        isCheckDependencies = true
        isAbortOnError = true
        isCheckReleaseBuilds = false
        isWarningsAsErrors = true
        isCheckAllWarnings = true
    }

    bundle {
        language { enableSplit = false }
    }

}

tasks {
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
}

val commonCompilerArgs = emptyList<String>()

val jvmCompilerArgs = listOf(
    "-Xuse-experimental=kotlinx.serialization.ExperimentalSerializationApi"
)

tasks.withType<org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompile> {
    kotlinOptions {
        freeCompilerArgs = commonCompilerArgs + jvmCompilerArgs
    }
}

androidExtensions {
    isExperimental = true
}

kapt {
    useBuildCache = true
    correctErrorTypes = true
    arguments {
        arg("dagger.formatGeneratedSource", "disabled")
        arg("dagger.fastInit", "enabled")
    }
}

dependencies {
//    implementation(project(mapOf("path" to ":dateandtimepicker")))
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.4.31")
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.3.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.3.5")

    /**
     * Utils
     */
    implementation("com.facebook.shimmer:shimmer:0.5.0")
    implementation("com.pnikosis:materialish-progress:1.7")



    /**
     * lifecycle
     */
    //noinspection LifecycleAnnotationProcessorWithJava8
    annotationProcessor("androidx.lifecycle:lifecycle-compiler:2.3.0")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.0")

    /**
     * REST API: retrofit
     */
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.7.0")
    implementation("com.jakewharton.timber:timber:4.7.1")

    /**
     * Okhttp3
     */
    implementation("com.squareup.okhttp3:okhttp:4.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")

    implementation("androidx.recyclerview:recyclerview:1.2.0")

    /**
     * Hilt
     */
    implementation("com.google.dagger:hilt-android:2.31-alpha")
    kapt("com.google.dagger:hilt-android-compiler:2.31-alpha")
    implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
    kapt("androidx.hilt:hilt-compiler:1.0.0-alpha03")

    /**
     *  Coroutines
     */
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.2")

    implementation("io.coil-kt:coil:1.1.0")
    implementation("io.coil-kt:coil-svg:0.12.0")


    /**
     *  Chuck
     */
    debugImplementation("com.readystatesoftware.chuck:library:1.1.0")
    releaseImplementation("com.readystatesoftware.chuck:library-no-op:1.1.0")

    /**
     * Gson
     * */
    implementation("com.google.code.gson:gson:2.8.6")

}