package io.github.zenhelix.gradle.test.dsl

import io.github.zenhelix.gradle.test.dsl.utils.FieldFormatter

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
     * Adds text as is
     */
    public fun raw(text: String)

    /**
     * Returns the built file content
     */
    public fun build(): String

    /**
     * Returns the current DSL context path
     */
    public val dslPath: DslPath

    public fun <T : GradleDsl> withDsl(dsl: T, init: T.() -> Unit)

    /**
     * Property delegation for DSL properties
     */
    public operator fun <T> setValue(thisRef: Any?, property: kotlin.reflect.KProperty<*>, value: T) {
        line("${property.name} = ${formatValue(value)}")
    }

    /**
     * Formats a value for output in generated code
     */
    public fun formatValue(value: Any?): String

}

/**
 * Base implementation of GradleDsl for building Gradle file contents
 */
public open class GradleDslImpl(
    override val dslPath: DslPath = DslPath.ROOT
) : GradleDsl {
    private val content = StringBuilder()
    private var indent = 0

    override fun line(text: String) {
        if (text.isNotEmpty()) {
            content.append("    ".repeat(indent)).append(text).append("\n")
        } else {
            content.append("\n")
        }
    }

    override fun <T : GradleDsl> withDsl(dsl: T, init: T.() -> Unit) {
        init(dsl)
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

    /**
     * Creates a child DSL with updated path
     */
    protected open fun createChildDsl(blockName: String): GradleDslImpl {
        return GradleDslImpl(dslPath.append(blockName))
    }

    /**
     * Property delegation support - implemented from GradleDsl interface
     */
    override operator fun <T> setValue(thisRef: Any?, property: kotlin.reflect.KProperty<*>, value: T) {
        line("${property.name} = ${formatValue(value)}")
    }

    override fun formatValue(value: Any?): String = FieldFormatter.formatValue(value)

}

/**
 * Represents a path in the DSL hierarchy
 */
public class DslPath(
    internal val segments: List<String> = emptyList()
) {
    /**
     * Adds a segment to the current path
     */
    public fun append(segment: String): DslPath =
        DslPath(segments + segment)

    /**
     * Checks if this path is inside the specified path
     */
    public fun isInside(other: DslPath): Boolean =
        segments.size >= other.segments.size &&
                segments.take(other.segments.size) == other.segments

    /**
     * Checks if we are in the context of the specified segment
     */
    public fun isInside(segment: String): Boolean =
        segments.contains(segment)

    /**
     * Forms a full path as a string
     */
    public fun toFullPath(): String = segments.joinToString(".")

    /**
     * Creates a relative path from current to the specified
     */
    public fun pathTo(target: DslPath): String {
        val commonPrefixSize = segments.zip(target.segments)
            .takeWhile { (a, b) -> a == b }
            .size

        val relativePath = target.segments.drop(commonPrefixSize)
        return relativePath.joinToString(".")
    }

    public fun isCurrentContext(segment: String): Boolean =
        segments.isNotEmpty() && segments.last() == segment

    override fun toString(): String = toFullPath()

    public companion object {
        public val ROOT: DslPath = DslPath()
    }
}