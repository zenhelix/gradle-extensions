package io.github.zenhelix.gradle.test.dsl.task.android

import io.github.zenhelix.gradle.test.dsl.GradleDsl
import io.github.zenhelix.gradle.test.dsl.utils.PropertyDelegate
import org.gradle.api.JavaVersion

public class VectorDrawablesOptionsDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public var useSupportLibrary: Boolean by PropertyDelegate(parent)
}

public class AndroidSourceSetsDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public fun main(init: AndroidSourceSetDsl.() -> Unit) {
        block("named(\"main\")") {
            AndroidSourceSetDsl(this).apply(init)
        }
    }

    public fun test(init: AndroidSourceSetDsl.() -> Unit) {
        block("named(\"test\")") {
            AndroidSourceSetDsl(this).apply(init)
        }
    }

    public fun androidTest(init: AndroidSourceSetDsl.() -> Unit) {
        block("named(\"androidTest\")") {
            AndroidSourceSetDsl(this).apply(init)
        }
    }

    public fun named(name: String, init: AndroidSourceSetDsl.() -> Unit) {
        block("named(${formatValue(name)})") {
            AndroidSourceSetDsl(this).apply(init)
        }
    }
}

public class AndroidSourceSetDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public fun java(vararg dirs: String) {
        val dirsStr = dirs.joinToString("\", \"", "\"", "\"")
        line("java.setSrcDirs(listOf($dirsStr))")
    }

    public fun res(vararg dirs: String) {
        val dirsStr = dirs.joinToString("\", \"", "\"", "\"")
        line("res.setSrcDirs(listOf($dirsStr))")
    }

    public fun assets(vararg dirs: String) {
        val dirsStr = dirs.joinToString("\", \"", "\"", "\"")
        line("assets.setSrcDirs(listOf($dirsStr))")
    }

    public fun manifest(file: String) {
        line("manifest.srcFile(${formatValue(file)})")
    }
}

public class AndroidCompileOptionsDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public var sourceCompatibility: JavaVersion by PropertyDelegate(parent) { "JavaVersion.${it.name}" }

    public var targetCompatibility: JavaVersion by PropertyDelegate(parent) { "JavaVersion.${it.name}" }

    public var isCoreLibraryDesugaringEnabled: Boolean by PropertyDelegate(parent)
}

public class AndroidPackagingDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public fun resources(init: AndroidPackagingResourcesDsl.() -> Unit) {
        block("resources") {
            AndroidPackagingResourcesDsl(this).apply(init)
        }
    }
}

public class AndroidPackagingResourcesDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public fun excludes(vararg patterns: String) {
        val patternsStr = patterns.joinToString("\", \"", "\"", "\"")
        line("excludes += setOf($patternsStr)")
    }

    public fun pickFirsts(vararg patterns: String) {
        val patternsStr = patterns.joinToString("\", \"", "\"", "\"")
        line("pickFirsts += setOf($patternsStr)")
    }

    public fun merges(vararg patterns: String) {
        val patternsStr = patterns.joinToString("\", \"", "\"", "\"")
        line("merges += setOf($patternsStr)")
    }
}

public class AndroidTestOptionsDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public fun unitTests(init: AndroidUnitTestsDsl.() -> Unit) {
        block("unitTests") {
            AndroidUnitTestsDsl(this).apply(init)
        }
    }
}

public class AndroidUnitTestsDsl(private val parent: GradleDsl) : GradleDsl by parent {

    public var isIncludeAndroidResources: Boolean by PropertyDelegate(parent)

    public var isReturnDefaultValues: Boolean by PropertyDelegate(parent)
}