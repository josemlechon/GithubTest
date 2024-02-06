object Dependencies {

    object UI {
        const val activity = "androidx.activity:activity-compose:1.5.1"
        const val coil = "io.coil-kt:coil-compose:2.2.2"
        const val constraint = "androidx.constraintlayout:constraintlayout:2.1.4"
        const val material = "com.google.android.material:material:1.5.0"
        const val appCompat = "androidx.appcompat:appcompat:1.6.1"

    }

    object Data{
        val retrofit ="com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
        val retrofitLoging = "com.squareup.okhttp3:logging-interceptor:4.8.1"
        val gson = "com.google.code.gson:gson:${Versions.gson}"
        val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
        val googleLocation = "com.google.android.gms:play-services-location:21.0.1"
    }

    object Core {
        val core = "androidx.core:core-ktx:1.8.0"
        val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:2.3.1"
        val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        val koinDI = "io.insert-koin:koin-android:3.5.0"

        val kotlinBOM = "org.jetbrains.kotlin:kotlin-bom:1.8.10"
    }

    object Testing {
        val junit5 = "org.junit.jupiter:junit-jupiter:5.8.2"
        val mockkUnitTests = "io.mockk:mockk:${Versions.mockK}"

        val mockwebserver = "com.squareup.okhttp3:mockwebserver:${Versions.mockwebserver}"

        val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    }


    object InsTests {
        val junit = "androidx.test.ext:junit:1.1.5"
        val espresso = "androidx.test.espresso:espresso-core:3.5.1"
        val espressoCont = "androidx.test.espresso:espresso-contrib:3.5.1"
        val mockkInstrumentalTests = "io.mockk:mockk-android:${Versions.mockK}"
    }

    object Debug {
        const val uiTooling = "androidx.compose.ui:ui-tooling"
        const val uiManifes = "androidx.compose.ui:ui-test-manifest"
    }

    object Versions {
        val mockK = "1.13.3"

        val mockwebserver = "4.11.0"
        val retrofit = "2.9.0"

        val gson = "2.10.1"
        val okhttp = "4.11.0"

        val coroutines = "1.7.3"
    }
}