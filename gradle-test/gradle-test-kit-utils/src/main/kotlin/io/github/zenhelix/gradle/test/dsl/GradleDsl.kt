package io.github.zenhelix.gradle.test.dsl

/**
 * Base interface for building Gradle files content
 */
public interface GradleDsl {
    /**
     * Добавляет строку в содержимое
     */
    public fun line(text: String)

    /**
     * Добавляет блок с именем и опциональными параметрами
     */
    public fun block(name: String, params: String = "", init: GradleDsl.() -> Unit)

    /**
     * Добавляет текст как есть
     */
    public fun raw(text: String)

    /**
     * Возвращает построенное содержимое файла
     */
    public fun build(): String

    /**
     * Возвращает текущий контекстный путь DSL
     */
    public val dslPath: DslPath

    public fun <T : GradleDsl> withDsl(dsl: T, init: T.() -> Unit)
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
     * Создает дочерний DSL с обновленным путем
     */
    protected open fun createChildDsl(blockName: String): GradleDslImpl {
        return GradleDslImpl(dslPath.append(blockName))
    }
}

/**
 * Представляет путь в иерархии DSL
 */
public class DslPath(
    internal val segments: List<String> = emptyList()
) {
    /**
     * Добавляет сегмент к текущему пути
     */
    public fun append(segment: String): DslPath =
        DslPath(segments + segment)

    /**
     * Проверяет, находится ли данный путь внутри указанного пути
     */
    public fun isInside(other: DslPath): Boolean =
        segments.size >= other.segments.size &&
                segments.take(other.segments.size) == other.segments

    /**
     * Проверяет, находимся ли в контексте указанного сегмента
     */
    public fun isInside(segment: String): Boolean =
        segments.contains(segment)

    /**
     * Формирует полный путь в виде строки
     */
    public fun toFullPath(): String = segments.joinToString(".")

    /**
     * Создает относительный путь от текущего до указанного
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