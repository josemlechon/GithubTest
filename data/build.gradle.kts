plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.jml.github.challenge.data"
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField("String", "TOKEN_GITHUB_API", "\"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\"")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":domain"))

    with(Dependencies.Core) {
        implementation(this.coroutinesAndroid)
        implementation(this.koinDI)
    }

    with(Dependencies.Data) {
        implementation(this.retrofit)
        implementation(this.retrofitGson)
        implementation(this.retrofitLoging)
        implementation(this.okhttp)
    }

    with(Dependencies.Testing) {
        testImplementation(junit5)
        testImplementation(mockkUnitTests)
        testImplementation(mockwebserver)
        testImplementation(coroutinesTest)
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}