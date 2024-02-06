plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.jml.github.challenge"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.jml.github.challenge"
        minSdk = 21

        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
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

    packagingOptions.resources.excludes.apply {
        add("/META-INF/LICENSE.md")
        add("META-INF/LICENSE-notice.md")
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

    implementation(project(mapOf("path" to ":data")))
    implementation(project(mapOf("path" to ":domain")))

    with(Dependencies.UI) {
        implementation(activity)
        implementation(coil)
        implementation(constraint)
        implementation(appCompat)
        implementation(material)
    }

    with(Dependencies.Core) {
        implementation(lifecycle)
        implementation(core)
        implementation(platform(kotlinBOM))

        implementation(koinDI)
    }


    with(Dependencies.Testing) {
        testImplementation(junit5)
        testImplementation(mockkUnitTests)
        testImplementation(coroutinesTest)
    }

    with(Dependencies.InsTests) {
        androidTestImplementation(junit)
        androidTestImplementation(espresso)
        androidTestImplementation(espressoCont)
        androidTestImplementation(mockkInstrumentalTests)
    }


}

tasks.withType<Test> {
    useJUnitPlatform()
}