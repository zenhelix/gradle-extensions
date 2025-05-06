package io.github.zenhelix.gradle.test.dsl.gradle

import io.github.zenhelix.gradle.test.dsl.DslPath
import io.github.zenhelix.gradle.test.dsl.GradleDsl

/**
 * Interface for DSL containers with support for creating typed objects
 *
 * @param T base type of objects in the container
 * @param C type of configurator for objects in the container
 */
public interface PolymorphicDomainObjectContainerDsl<T, C : GradleDsl> : NamedDomainObjectCollectionDsl<T, C> {

    /**
     * Creates an object of the specified type
     *
     * @param type type name
     * @param name name of the created object
     * @param init object initialization block
     */
    public fun <S : T> create(type: String, name: String, init: C.() -> Unit)

    /**
     * Registers a new type in the container
     *
     * @param type type name
     * @param implementationType full implementation class name
     */
    public fun registerType(type: String, implementationType: String)
}

/**
 * Base implementation of polymorphic container DSL
 *
 * @param T base type of objects in the container
 * @param C type of configurator for objects
 */
public abstract class AbstractPolymorphicDomainObjectContainerDsl<T, C : GradleDsl>(
    parent: GradleDsl,
    collectionName: String = ""
) : AbstractNamedDomainObjectCollectionDsl<T, C>(parent, collectionName),
    PolymorphicDomainObjectContainerDsl<T, C> {

    override fun <S : T> create(type: String, name: String, init: C.() -> Unit) {
        val prefix = if (dslPath.isCurrentContext(collectionName)) "" else "$collectionName."
        parent.block("${prefix}create<$type>(${formatValue(name)})") {
            withDsl(this@AbstractPolymorphicDomainObjectContainerDsl.createConfigurator(this), init)
        }
    }

    override fun registerType(type: String, implementationType: String) {
        val prefix = if (dslPath.isCurrentContext(collectionName)) "" else "$collectionName."
        parent.line("${prefix}registerBinding(bind($type::class.java, $implementationType::class.java))")
    }
}

/**
 * Base interface for DSLs that work with object collections in Gradle
 *
 * @param T type of objects in the collection
 * @param C type of configurator/handler for collection elements
 */
public interface NamedDomainObjectCollectionDsl<T, C : GradleDsl> {

    /**
     * Gets the parent DSL context
     */
    public val parent: GradleDsl

    /**
     * Gets the collection name
     */
    public val collectionName: String
        get() = ""

    /**
     * Gets the full DSL path, including the collection name
     */
    public val dslPath: DslPath
        get() = if (collectionName.isEmpty()) parent.dslPath
        else parent.dslPath.append(collectionName)

    /**
     * Returns a reference to this collection for use in other DSLs
     */
    public fun asReference(): DslReference<T> = DslReference(dslPath, parent.dslPath)

    /**
     * Creates a configurator/handler for elements of this collection
     */
    public fun createConfigurator(dsl: GradleDsl): C

    /**
     * Gets an object by name and configures it
     */
    public fun getByName(name: String, init: C.() -> Unit) {
        val prefix = if (dslPath.isCurrentContext(collectionName)) "" else "$collectionName."
        parent.block("${prefix}getByName(\"$name\")") {
            withDsl(createConfigurator(this), init)
        }
    }

    /**
     * Gets an object by name using Provider API
     */
    public fun named(name: String, init: C.() -> Unit) {
        val prefix = if (dslPath.isCurrentContext(collectionName)) "" else "$collectionName."
        parent.block("${prefix}named(\"$name\")") {
            withDsl(createConfigurator(this), init)
        }
    }

    /**
     * Creates a new named object in the collection
     */
    public fun create(name: String, init: C.() -> Unit) {
        val prefix = if (dslPath.isCurrentContext(collectionName)) "" else "$collectionName."
        parent.block("${prefix}create(\"$name\")") {
            withDsl(createConfigurator(this), init)
        }
    }

    /**
     * Configures all objects in the collection
     */
    public fun all(init: C.() -> Unit) {
        val prefix = if (dslPath.isCurrentContext(collectionName)) "" else "$collectionName."
        parent.block("${prefix}all") {
            withDsl(createConfigurator(this), init)
        }
    }

    /**
     * Configures each object in the collection (more efficient for large collections)
     */
    public fun configureEach(init: C.() -> Unit) {
        val prefix = if (dslPath.isCurrentContext(collectionName)) "" else "$collectionName."
        parent.block("${prefix}configureEach") {
            withDsl(createConfigurator(this), init)
        }
    }

    /**
     * Gets an object by name using the Kotlin property delegate pattern
     */
    public fun getting(name: String, init: C.() -> Unit) {
        parent.block("val $name by getting") {
            createConfigurator(this).apply(init)
        }
    }

    /**
     * Filters objects by type and configures them
     */
    public fun withType(type: String, init: C.() -> Unit) {
        val prefix = if (dslPath.isCurrentContext(collectionName)) "" else "$collectionName."
        parent.block("${prefix}withType<$type>()") {
            withDsl(createConfigurator(this), init)
        }
    }
}

/**
 * Base implementation of NamedDomainObjectCollectionDsl
 */
public abstract class AbstractNamedDomainObjectCollectionDsl<T, C : GradleDsl>(
    override val parent: GradleDsl,
    override val collectionName: String = ""
) : NamedDomainObjectCollectionDsl<T, C>, GradleDsl by parent {
    override val dslPath: DslPath
        get() = parent.dslPath
}