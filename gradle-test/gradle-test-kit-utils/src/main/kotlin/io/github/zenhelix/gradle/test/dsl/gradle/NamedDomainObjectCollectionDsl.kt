package io.github.zenhelix.gradle.test.dsl.gradle

import io.github.zenhelix.gradle.test.dsl.GradleDsl

/**
 * Базовый интерфейс для DSL, работающих с коллекциями объектов в Gradle
 *
 * @param T тип объектов в коллекции
 * @param C тип конфигуратора/обработчика для элементов коллекции
 */
public interface NamedDomainObjectCollectionDsl<T, C> {

    /**
     * Получает родительский DSL контекст
     */
    public val parent: GradleDsl

    /**
     * Получает имя коллекции, по умолчанию пустая строка
     */
    public val collectionName: String
        get() = ""

    /**
     * Возвращает ссылку на эту коллекцию для использования в других DSL
     */
    public fun asReference(): DslReference<T> = DslReference(collectionName.ifEmpty { "this" })

    /**
     * Создает конфигуратор/обработчик для элементов этой коллекции
     */
    public fun createConfigurator(dsl: GradleDsl): C

    /**
     * Получает объект по имени и конфигурирует его
     */
    public fun getByName(name: String, init: C.() -> Unit) {
        val prefix = if (collectionName.isNotEmpty()) "$collectionName." else ""
        parent.block("${prefix}getByName(\"$name\")") {
            createConfigurator(this).apply(init)
        }
    }

    /**
     * Получает объект по имени, используя Provider API
     */
    public fun named(name: String, init: C.() -> Unit) {
        val prefix = if (collectionName.isNotEmpty()) "$collectionName." else ""
        parent.block("${prefix}named(\"$name\")") {
            createConfigurator(this).apply(init)
        }
    }

    /**
     * Создает новый именованный объект в коллекции
     */
    public fun create(name: String, init: C.() -> Unit) {
        val prefix = if (collectionName.isNotEmpty()) "$collectionName." else ""
        parent.block("${prefix}create(\"$name\")") {
            createConfigurator(this).apply(init)
        }
    }

    /**
     * Конфигурирует все объекты в коллекции
     */
    public fun all(init: C.() -> Unit) {
        val prefix = if (collectionName.isNotEmpty()) "$collectionName." else ""
        parent.block("${prefix}all") {
            createConfigurator(this).apply(init)
        }
    }

    /**
     * Конфигурирует каждый объект в коллекции (более эффективно для больших коллекций)
     */
    public fun configureEach(init: C.() -> Unit) {
        val prefix = if (collectionName.isNotEmpty()) "$collectionName." else ""
        parent.block("${prefix}configureEach") {
            createConfigurator(this).apply(init)
        }
    }

    /**
     * Получает объект по имени, используя шаблон делегата свойств Kotlin
     */
    public fun getting(name: String, init: C.() -> Unit) {
        parent.block("val $name by getting") {
            createConfigurator(this).apply(init)
        }
    }

    /**
     * Фильтрует объекты по типу и конфигурирует их
     */
    public fun withType(type: String, init: C.() -> Unit) {
        val prefix = if (collectionName.isNotEmpty()) "$collectionName." else ""
        parent.block("${prefix}withType<$type>()") {
            createConfigurator(this).apply(init)
        }
    }
}

/**
 * Базовая реализация NamedDomainObjectCollectionDsl
 */
public abstract class AbstractNamedDomainObjectCollectionDsl<T, C>(
    override val parent: GradleDsl,
    override val collectionName: String = ""
) : NamedDomainObjectCollectionDsl<T, C>, GradleDsl by parent