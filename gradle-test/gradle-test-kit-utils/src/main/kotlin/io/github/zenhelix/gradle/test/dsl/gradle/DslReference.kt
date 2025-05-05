package io.github.zenhelix.gradle.test.dsl.gradle

import io.github.zenhelix.gradle.test.dsl.DslPath

/**
 * Represents a reference to a DSL component for use in other DSLs
 */
public class DslReference<T>(
    private val targetPath: DslPath,
    private val currentPath: DslPath
) {
    /**
     * Creates a string representation of the reference taking into account the current context
     */
    override fun toString(): String {
        if (currentPath.isInside(targetPath)) {
            return targetPath.segments.last()
        }

        return targetPath.toFullPath()
    }
}