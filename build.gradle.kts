// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false

    // ФИКС КОНФЛИКТА: ЯВНО устанавливаем версию Kotlin 1.9.22
    id("org.jetbrains.kotlin.android") version "1.9.22" apply false

    // ФИКС КОНФЛИКТА: Совместимая версия KSP для 1.9.22
    id("com.google.devtools.ksp") version "1.9.22-1.0.17" apply false

    id("com.google.gms.google-services") version "4.4.1" apply false
}