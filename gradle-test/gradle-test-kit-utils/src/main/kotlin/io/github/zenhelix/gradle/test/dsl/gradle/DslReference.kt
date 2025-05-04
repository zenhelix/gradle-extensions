package io.github.zenhelix.gradle.test.dsl.gradle

public class DslReference<T>(
    private val path: String
) {
    override fun toString(): String = path
}