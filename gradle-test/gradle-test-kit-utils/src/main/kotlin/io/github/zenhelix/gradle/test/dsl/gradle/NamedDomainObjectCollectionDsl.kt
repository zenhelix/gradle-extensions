package io.github.zenhelix.gradle.test.dsl.gradle

import io.github.zenhelix.gradle.test.dsl.DslPath
import io.github.zenhelix.gradle.test.dsl.GradleDsl

/**
 * Интерфейс для DSL контейнеров с поддержкой создания типизированных объектов
 *
 * @param T базовый тип объектов в контейнере
 * @param C тип конфигуратора для объектов в контейнере
 */
public interface PolymorphicDomainObjectContainerDsl<T, C : GradleDsl> : NamedDomainObjectCollectionDsl<T, C> {

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
public abstract class AbstractPolymorphicDomainObjectContainerDsl<T, C : GradleDsl>(
    parent: GradleDsl,
    collectionName: String = ""
) : AbstractNamedDomainObjectCollectionDsl<T, C>(parent, collectionName),
    PolymorphicDomainObjectContainerDsl<T, C> {

    override fun <S : T> create(type: String, name: String, init: C.() -> Unit) {
        val prefix = if (dslPath.isCurrentContext(collectionName)) "" else "$collectionName."
        parent.block("${prefix}create<$type>(\"$name\")") {
            withDsl(createConfigurator(this), init)
        }
    }

    override fun registerType(type: String, implementationType: String) {
        val prefix = if (dslPath.isCurrentContext(collectionName)) "" else "$collectionName."
        parent.line("${prefix}registerBinding(bind($type::class.java, $implementationType::class.java))")
    }
}

/**
 * Базовый интерфейс для DSL, работающих с коллекциями объектов в Gradle
 *
 * @param T тип объектов в коллекции
 * @param C тип конфигуратора/обработчика для элементов коллекции
 */
public interface NamedDomainObjectCollectionDsl<T, C : GradleDsl> {

    /**
     * Получает родительский DSL контекст
     */
    public val parent: GradleDsl

    /**
     * Получает имя коллекции
     */
    public val collectionName: String
        get() = ""

    /**
     * Получает полный путь DSL, включая имя коллекции
     */
    public val dslPath: DslPath
        get() = if (collectionName.isEmpty()) parent.dslPath
        else parent.dslPath.append(collectionName)

    /**
     * Возвращает ссылку на эту коллекцию для использования в других DSL
     */
    public fun asReference(): DslReference<T> = DslReference(dslPath, parent.dslPath)

    /**
     * Создает конфигуратор/обработчик для элементов этой коллекции
     */
    public fun createConfigurator(dsl: GradleDsl): C

    /**
     * Получает объект по имени и конфигурирует его
     */
    public fun getByName(name: String, init: C.() -> Unit) {
        val prefix = if (dslPath.isCurrentContext(collectionName)) "" else "$collectionName."
        parent.block("${prefix}getByName(\"$name\")") {
            withDsl(createConfigurator(this), init)
        }
    }

    /**
     * Получает объект по имени, используя Provider API
     */
    public fun named(name: String, init: C.() -> Unit) {
        val prefix = if (dslPath.isCurrentContext(collectionName)) "" else "$collectionName."
        parent.block("${prefix}named(\"$name\")") {
            withDsl(createConfigurator(this), init)
        }
    }

    /**
     * Создает новый именованный объект в коллекции
     */
    public fun create(name: String, init: C.() -> Unit) {
        val prefix = if (dslPath.isCurrentContext(collectionName)) "" else "$collectionName."
        parent.block("${prefix}create(\"$name\")") {
            withDsl(createConfigurator(this), init)
        }
    }

    /**
     * Конфигурирует все объекты в коллекции
     */
    public fun all(init: C.() -> Unit) {
        val prefix = if (dslPath.isCurrentContext(collectionName)) "" else "$collectionName."
        parent.block("${prefix}all") {
            withDsl(createConfigurator(this), init)
        }
    }

    /**
     * Конфигурирует каждый объект в коллекции (более эффективно для больших коллекций)
     */
    public fun configureEach(init: C.() -> Unit) {
        val prefix = if (dslPath.isCurrentContext(collectionName)) "" else "$collectionName."
        parent.block("${prefix}configureEach") {
            withDsl(createConfigurator(this), init)
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
        val prefix = if (dslPath.isCurrentContext(collectionName)) "" else "$collectionName."
        parent.block("${prefix}withType<$type>()") {
            withDsl(createConfigurator(this), init)
        }
    }
}

/**
 * Базовая реализация NamedDomainObjectCollectionDsl
 */
public abstract class AbstractNamedDomainObjectCollectionDsl<T, C : GradleDsl>(
    override val parent: GradleDsl,
    override val collectionName: String = ""
) : NamedDomainObjectCollectionDsl<T, C>, GradleDsl by parent