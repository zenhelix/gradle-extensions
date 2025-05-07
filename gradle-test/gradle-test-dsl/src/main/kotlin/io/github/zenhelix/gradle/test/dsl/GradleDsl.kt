package io.github.zenhelix.gradle.test.dsl

import io.github.zenhelix.gradle.test.dsl.utils.DslPath
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
