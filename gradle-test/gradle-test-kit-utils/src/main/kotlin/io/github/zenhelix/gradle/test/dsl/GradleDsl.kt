package io.github.zenhelix.gradle.test.dsl

/**
 * Base interface for building Gradle files content
 */
public interface GradleDsl {
    /**
     * Adds a line to the content
     */
    public fun line(text: String)

    /**
     * Adds a block with name and optional parameters
     */
    public fun block(name: String, params: String = "", init: GradleDsl.() -> Unit)

    /**
     * Adds text as-is
     */
    public fun raw(text: String)

    /**
     * Returns the built file content
     */
    public fun build(): String
}

/**
 * Base implementation of GradleDsl for building Gradle file contents
 */
public open class GradleDslImpl : GradleDsl {
    private val content = StringBuilder()
    private var indent = 0

    override fun line(text: String) {
        if (text.isNotEmpty()) {
            content.append("    ".repeat(indent)).append(text).append("\n")
        } else {
            content.append("\n")
        }
    }

    override fun block(name: String, params: String, init: GradleDsl.() -> Unit) {
        content.append("    ".repeat(indent)).append(name)
        if (params.isNotEmpty()) {
            content.append(" ").append(params)
        }
        content.append(" {\n")
        indent++
        init(this)
        indent--
        content.append("    ".repeat(indent)).append("}\n")
    }

    override fun raw(text: String) {
        text.lines().forEach { line(it) }
    }

    override fun build(): String = content.toString()
}