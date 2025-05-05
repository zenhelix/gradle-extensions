package io.github.zenhelix.gradle.test.dsl.gradle

import io.github.zenhelix.gradle.test.dsl.DslPath

/**
 * Представляет ссылку на DSL компонент для использования в других DSL
 */
public class DslReference<T>(
    private val targetPath: DslPath,
    private val currentPath: DslPath
) {
    /**
     * Создает строковое представление ссылки с учетом текущего контекста
     */
    override fun toString(): String {
        if (currentPath.isInside(targetPath)) {
            return targetPath.segments.last()
        }

        return targetPath.toFullPath()
    }
}