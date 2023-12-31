plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.salon"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.salon"
        minSdk = 24
        targetSdk = 34
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
    buildFeatures {
        viewBinding = true
        //noinspection DataBindingWithoutKapt
        dataBinding = true

    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.databinding:databinding-runtime:8.1.4")
    implementation("com.google.android.gms:play-services-maps:18.2.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    val nav_version = "2.7.4"

    // Java language implementation
    implementation("androidx.navigation:navigation-fragment:$nav_version")
    implementation("androidx.navigation:navigation-ui:$nav_version")

    // Kotlin
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")

    // Feature module Support
    implementation("androidx.navigation:navigation-dynamic-features-fragment:$nav_version")

    // Testing Navigation
    androidTestImplementation("androidx.navigation:navigation-testing:$nav_version")

    //SmoothBottomBar
    implementation("com.github.ibrahimsn98:SmoothBottomBar:1.7.9")

    //retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    //Gson
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    //picasso
    implementation ("com.squareup.picasso:picasso:2.8")

    //Animation
    implementation("com.airbnb.android:lottie:6.1.0")

    //fragment
    implementation ("androidx.fragment:fragment:1.6.2")

    //Gps
    implementation ("com.google.android.gms:play-services-location:21.0.1")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    implementation ("com.google.dagger:hilt-android:2.48.1")
    kapt("com.google.dagger:hilt-compiler:2.48.1")


    implementation(project(":tools"))


    // For instrumentation tests
    androidTestImplementation  ("com.google.dagger:hilt-android-testing:2.48.1")
    kaptAndroidTest ("com.google.dagger:hilt-compiler:2.48.1")

    // For local unit tests
    testImplementation ("com.google.dagger:hilt-android-testing:2.48.1")
    kaptTest ("com.google.dagger:hilt-compiler:2.48.1")



}
kapt {
    correctErrorTypes = true
}