package io.github.zenhelix.gradle.test.dsl.utils

public class DslReference(
    private val targetPath: DslPath,
    private val currentPath: DslPath
) {
    override fun toString(): String {
        if (currentPath.isInside(targetPath)) {
            return targetPath.segments.lastOrNull() ?: ""
        }

        if (targetPath.isInside(currentPath)) {
            val relativePath = currentPath.pathTo(targetPath)
            return relativePath
        }

        return targetPath.toFullPath()
    }
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