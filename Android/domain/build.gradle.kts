plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    alias(libs.plugins.kotlin.kapt)
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
    dependencies{
        kapt(libs.hilt.compiler)
        implementation(libs.kotlinx.coroutines.core)
        testImplementation(libs.junit)
        testImplementation(libs.kotlinx.coroutines.test)
    }
}
