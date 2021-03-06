package grg.app.open.build.dependencies

import com.android.build.gradle.internal.dependency.VariantDependencies
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler

class JetPack :
    Plugin<Project> {
    private object Group {
        const val kotlin = "org.jetbrains.kotlin"
        const val x_appcompat = "androidx.appcompat"
        const val x_constraintLayout = "androidx.constraintlayout"
        const val x_core = "androidx.core"
        const val x_lifecycle = "androidx.lifecycle"
        const val x_activity = "androidx.activity"
        const val x_fragment = "androidx.fragment"
        const val x_paging = "androidx.paging"
    }

    private object Version {
        const val kotlin = "1.3.72"
        const val x_appcompat = "1.1.0"
        const val x_constraintLayout = "2.0.0-beta7"
        const val core_ktx = "1.3.0"
        const val x_lifecycle = "2.2.0"
        const val activity_ktx = "1.1.0"
        const val fragment_ktx = "1.2.4"
        const val x_paging = "2.1.2"
    }

    private object Name {
        const val kotlin = "kotlin-stdlib"
        const val kotlin_classpath = "kotlin-gradle-plugin"
        const val x_appcompat = "appcompat"
        const val x_constraintLayout = "constraintlayout"
        const val core_ktx = "core-ktx"
        const val lifecycle_viewmodel_ktx = "lifecycle-viewmodel-ktx"
        const val lifecycle_livedata_ktx = "lifecycle-livedata-ktx"
        const val lifecycle_common_java8 = "lifecycle-common-java8"
        const val lifecycle_compiler = "lifecycle-compiler"
        const val lifecycle_runtime = "lifecycle-runtime"
        const val activity_ktx = "activity-ktx"
        const val fragment_ktx = "fragment-ktx"
        const val x_paging = "paging-runtime-ktx"
    }

    object Lib {
        const val kotlin = "${Group.kotlin}:${Name.kotlin}:${Version.kotlin}"
        const val core_ktx = "${Group.x_core}:${Name.core_ktx}:${Version.core_ktx}"

        const val kotlin_classpath = "${Group.kotlin}:${Name.kotlin_classpath}:${Version.kotlin}"

        const val x_appcompat = "${Group.x_appcompat}:${Name.x_appcompat}:${Version.x_appcompat}"

        const val x_constraintLayout =
            "${Group.x_constraintLayout}:${Name.x_constraintLayout}:${Version.x_constraintLayout}"

        const val lifecycle_viewmodel_ktx =
            "${Group.x_lifecycle}:${Name.lifecycle_viewmodel_ktx}:${Version.x_lifecycle}"

        const val lifecycle_livedata_ktx =
            "${Group.x_lifecycle}:${Name.lifecycle_livedata_ktx}:${Version.x_lifecycle}"

        const val lifecycle_common_java8 =
            "${Group.x_lifecycle}:${Name.lifecycle_common_java8}:${Version.x_lifecycle}"

        const val lifecycle_compiler =
            "${Group.x_lifecycle}:${Name.lifecycle_compiler}:${Version.x_lifecycle}"

        const val lifecycle_runtime =
            "${Group.x_lifecycle}:${Name.lifecycle_runtime}:${Version.x_lifecycle}"

        const val activity_ktx =
            "${Group.x_activity}:${Name.activity_ktx}:${Version.activity_ktx}"

        const val fragment_ktx =
            "${Group.x_fragment}:${Name.fragment_ktx}:${Version.fragment_ktx}"

        const val x_paging =
            "${Group.x_paging}:${Name.x_paging}:${Version.x_paging}"
    }

    override fun apply(target: Project) {
        val byType = target.extensions.getByType(BaseAppModuleExtension::class.java)
        //compile group: 'com.google.code.gson', name: 'gson', version: '2.8.6'
        target.dependencies.apply {
            implementation(Lib.kotlin)
            implementation(Lib.x_appcompat)
            implementation(Lib.x_constraintLayout)
            implementation(Lib.core_ktx)

//            implementation(Lib.lifecycle_common_java8)
//            implementation(Lib.lifecycle_compiler)
            implementation(Lib.lifecycle_livedata_ktx)
            implementation(Lib.lifecycle_viewmodel_ktx)
            implementation(Lib.lifecycle_runtime)

            implementation(Lib.activity_ktx)
//            implementation(Lib.x_paging)
            implementation(Lib.fragment_ktx)


        }

    }

    private fun DependencyHandler.implementation(dependencyStr: String) {
        add(VariantDependencies.CONFIG_NAME_IMPLEMENTATION, dependencyStr)
    }

}