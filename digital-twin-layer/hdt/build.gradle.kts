plugins {
    id("buildlogic.kotlin-application-conventions")
}

application {
    // Define the main class for the application.
    mainClass = "org.example.app.AppKt"
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
}