
val gradle_version: String = "3.5.2"

buildscript {


    repositories {
        google()
        jcenter()
        flatDir {
            dirs("libs")
        }
        maven("https://jitpack.io")
        mavenCentral()
        maven("https://dl.bintray.com/umsdk/release")
        maven("https://dl.bintray.com/thelasterstar/maven/")
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.70")
    }

}

apply {
    plugin("java")
    plugin("kotlin")
}

repositories {
    google()
    jcenter()
    flatDir {
        dirs("libs")
    }
    maven("https://jitpack.io")
    mavenCentral()
    maven("https://dl.bintray.com/umsdk/release")
    maven("https://dl.bintray.com/thelasterstar/maven/")
}

dependencies {
    implementation(gradleApi())
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.70")
    implementation("com.android.tools.build:gradle:${gradle_version}")
}
