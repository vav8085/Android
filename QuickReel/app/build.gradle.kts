plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    // Enables the Kotlin Annotation Processing Tool (kapt).
    // It's required for libraries like Hilt that generate code at compile-time.
    id("kotlin-kapt")

    // The Hilt Gradle plugin that sets up all the necessary configurations for Hilt to work.
    id("com.google.dagger.hilt.android")
}

kapt {
    correctErrorTypes = true
}

android {
    namespace = "com.vav.quickreel"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.vav.quickreel"
        minSdk = 24
        targetSdk = 36
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
// ## Core Android & Compose
    // Provides Kotlin extension functions for core Android APIs to make code more concise.
    implementation("androidx.core:core-ktx:1.13.1")
    // Provides lifecycle-aware coroutine scopes like `lifecycleScope` and `repeatOnLifecycle`.
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.2")
    // Provides the `setContent { ... }` extension function for ComponentActivity to host Compose UI.
    implementation("androidx.activity:activity-compose:1.9.0")
    // Bill of Materials (BOM): Manages the versions of all Jetpack Compose libraries to ensure they are compatible.
    implementation(platform("androidx.compose:compose-bom:2024.05.00"))
    // The fundamental library for Jetpack Compose. Provides `@Composable`, `Modifier`, `Column`, `Row`, etc.
    implementation("androidx.compose.ui:ui")
    // Provides core graphics primitives for Compose like `Color`, `Brush`, `Shape`.
    implementation("androidx.compose.ui:ui-graphics")
    // Enables the `@Preview` annotation to see your Composables in the Android Studio preview pane.
    implementation("androidx.compose.ui:ui-tooling-preview")
    // Provides Material Design 3 components like `Scaffold`, `Button`, `Card`, `Text`.
    implementation("androidx.compose.material3:material3")

    // ## Dagger - Hilt (Dependency Injection)
    // The core Hilt library. Provides annotations like `@HiltAndroidApp`, `@AndroidEntryPoint`, and `@Inject`.
    implementation("com.google.dagger:hilt-android:2.51.1")
    // The Hilt compiler (used by kapt) that generates the dependency injection code.
    kapt("com.google.dagger:hilt-compiler:2.51.1")
    // Integrates Hilt with Jetpack Navigation for Compose. Provides the `hiltViewModel()` function.
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    // ## Retrofit & Moshi (Networking & JSON Parsing)
    // A type-safe HTTP client for Android. Provides `Retrofit.Builder` and annotations like `@GET`, `@POST`, `@Body`.
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    // A converter that allows Retrofit to use Moshi for serializing and deserializing JSON.
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    // A modern JSON library for Kotlin. Provides annotations like `@Json` for mapping JSON keys to properties.
    implementation("com.squareup.moshi:moshi-kotlin:1.15.1")
    // An OkHttp interceptor that logs HTTP request and response data to Logcat, great for debugging.
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // ## ViewModel & Navigation for Compose
    // Provides the `viewModel()` function to easily get a `ViewModel` instance in a Composable.
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.2")
    // Enables navigation between different screens in a Compose application. Provides `NavHost` and `rememberNavController()`.
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // ## Coil (Image Loading)
    // A coroutine-based image loading library. Provides the `AsyncImage` Composable for displaying images from URLs.
    implementation("io.coil-kt:coil-compose:2.6.0")

    // ## Testing
    // The standard library for writing unit tests in Java/Kotlin. Provides `@Test` annotation and `assertEquals()`.
    testImplementation("junit:junit:4.13.2")
    // AndroidX extensions for JUnit that allow tests to run on a device or emulator.
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    // A framework for writing UI tests (instrumentation tests). Provides `onView()`, `perform(click())`, etc.
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    // Aligns the versions for Compose testing libraries.
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.05.00"))
    // The core library for testing Compose UIs. Provides `createComposeRule()` and finders like `onNodeWithText()`.
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    // Provides tools for inspecting and interacting with the UI tree in tests.
    debugImplementation("androidx.compose.ui:ui-tooling")
    // Helps configure the manifest for UI tests.
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}