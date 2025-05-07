package io.github.zenhelix.gradle.test.dsl

import io.github.zenhelix.gradle.test.dsl.utils.FieldFormatter

@DslMarker
public annotation class GradlePluginTestDsl

//@GradlePluginTestDsl
public interface GradleDsl {

    public fun line(text: String)

    public fun block(name: String, params: String = "", init: GradleDsl.() -> Unit)

    public fun build(): String

    public val dslPath: DslPath

    public fun <T : GradleDsl> withDsl(dsl: T, init: T.() -> Unit)

    public fun formatValue(value: Any?): String

}

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

    override fun build(): String = content.toString()

    override fun formatValue(value: Any?): String = FieldFormatter.formatValue(value)

}

public class DslPath(
    internal val segments: List<String> = emptyList()
) {

    public fun append(segment: String): DslPath = DslPath(segments + segment)

    public fun isInside(other: DslPath): Boolean = segments.size >= other.segments.size
            && segments.take(other.segments.size) == other.segments

    public fun isInside(segment: String): Boolean = segments.contains(segment)

    public fun toFullPath(): String = segments.joinToString(".")

    public fun pathTo(target: DslPath): String {
        val commonPrefixSize = segments.zip(target.segments)
            .takeWhile { (a, b) -> a == b }
            .size

        val relativePath = target.segments.drop(commonPrefixSize)
        return relativePath.joinToString(".")
    }

    public fun isCurrentContext(segment: String): Boolean = segments.isNotEmpty() && segments.last() == segment

    public fun getContextPrefix(segment: String = ""): String {
        return if (segment.isEmpty() || isCurrentContext(segment)) "" else "$segment."
    }

    override fun toString(): String = toFullPath()

    public companion object {
        public val ROOT: DslPath = DslPath()
    }
}