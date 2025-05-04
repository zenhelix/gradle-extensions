package io.github.zenhelix.gradle.test.dsl.gradle

import io.github.zenhelix.gradle.test.dsl.GradleDsl

/**
 * Абстракция для типизированных коллекций с поддержкой withType
 */
public class TypedCollectionDsl<T>(
    private val parent: GradleDsl,
    private val name: String
) : GradleDsl by parent {

    /**
     * Фильтрует коллекцию по типу и конфигурирует отфильтрованные элементы
     */
    public fun withType(typeName: String, init: GradleDsl.() -> Unit) {
        block("withType<$typeName>") {
            init(this)
        }
    }

    /**
     * Возвращает строковое представление ссылки на эту коллекцию
     */
    override fun toString(): String = name
}
