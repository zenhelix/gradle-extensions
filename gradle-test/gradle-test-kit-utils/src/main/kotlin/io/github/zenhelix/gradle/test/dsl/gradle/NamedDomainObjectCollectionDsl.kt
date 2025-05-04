package io.github.zenhelix.gradle.test.dsl.gradle

import io.github.zenhelix.gradle.test.dsl.GradleDsl

/**
 * Интерфейс для DSL контейнеров с поддержкой создания типизированных объектов
 *
 * @param T базовый тип объектов в контейнере
 * @param C тип конфигуратора для объектов в контейнере
 */
public interface PolymorphicDomainObjectContainerDsl<T, C> : NamedDomainObjectCollectionDsl<T, C> {

    /**
     * Создает объект указанного типа
     *
     * @param type имя типа
     * @param name имя создаваемого объекта
     * @param init блок инициализации объекта
     */
    public fun <S : T> create(type: String, name: String, init: C.() -> Unit)

    /**
     * Регистрирует новый тип в контейнере
     *
     * @param type имя типа
     * @param implementationType полное имя класса реализации
     */
    public fun registerType(type: String, implementationType: String)
}

/**
 * Базовая реализация полиморфного контейнера DSL
 *
 * @param T базовый тип объектов в контейнере
 * @param C тип конфигуратора для объектов
 */
public abstract class AbstractPolymorphicDomainObjectContainerDsl<T, C>(
    parent: GradleDsl,
    collectionName: String = ""
) : AbstractNamedDomainObjectCollectionDsl<T, C>(parent, collectionName),
    PolymorphicDomainObjectContainerDsl<T, C> {

    override fun <S : T> create(type: String, name: String, init: C.() -> Unit) {
        val prefix = if (collectionName.isNotEmpty()) "$collectionName." else ""
        parent.block("${prefix}create<$type>(\"$name\")") {
            createConfigurator(this).apply(init)
        }
    }

    override fun registerType(type: String, implementationType: String) {
        val prefix = if (collectionName.isNotEmpty()) "$collectionName." else ""
        parent.line("${prefix}registerBinding(bind($type::class.java, $implementationType::class.java))")
    }
}

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