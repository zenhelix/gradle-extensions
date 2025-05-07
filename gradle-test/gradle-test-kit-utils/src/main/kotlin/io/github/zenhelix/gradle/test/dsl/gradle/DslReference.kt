package io.github.zenhelix.gradle.test.dsl.gradle

import io.github.zenhelix.gradle.test.dsl.DslPath

public class DslReference<T>(
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