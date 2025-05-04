package io.github.zenhelix.gradle.test.dsl.gradle

import io.github.zenhelix.gradle.test.dsl.GradleDsl

/**
 * Base interface for DSLs that work with Gradle's NamedDomainObjectCollection pattern
 *
 * @param T the type of objects in the collection
 * @param C the type of the configurator/handler for items in the collection
 */
public interface NamedDomainObjectCollectionDsl<T, C> {

    /**
     * Gets the parent DSL context
     */
    public val parent: GradleDsl

    /**
     * Gets the name of the collection, defaults to empty
     */
    public val collectionName: String
        get() = ""

    /**
     * Creates the configurator/handler for items in this collection
     */
    public fun createConfigurator(dsl: GradleDsl): C

    /**
     * Gets an object by name and configures it
     */
    public fun getByName(name: String, init: C.() -> Unit) {
        val prefix = if (collectionName.isNotEmpty()) "$collectionName." else ""
        parent.block("${prefix}getByName(\"$name\")") {
            createConfigurator(this).apply(init)
        }
    }

    /**
     * Gets an object by name using the Provider API
     */
    public fun named(name: String, init: C.() -> Unit) {
        val prefix = if (collectionName.isNotEmpty()) "$collectionName." else ""
        parent.block("${prefix}named(\"$name\")") {
            createConfigurator(this).apply(init)
        }
    }

    /**
     * Creates a new named object in the collection
     */
    public fun create(name: String, init: C.() -> Unit) {
        val prefix = if (collectionName.isNotEmpty()) "$collectionName." else ""
        parent.block("${prefix}create(\"$name\")") {
            createConfigurator(this).apply(init)
        }
    }

    /**
     * Configures all objects in the collection
     */
    public fun all(init: C.() -> Unit) {
        val prefix = if (collectionName.isNotEmpty()) "$collectionName." else ""
        parent.block("${prefix}all") {
            createConfigurator(this).apply(init)
        }
    }

    /**
     * Configures each object in the collection (more efficient for large collections)
     */
    public fun configureEach(init: C.() -> Unit) {
        val prefix = if (collectionName.isNotEmpty()) "$collectionName." else ""
        parent.block("${prefix}configureEach") {
            createConfigurator(this).apply(init)
        }
    }

    /**
     * Gets an object by name using Kotlin's property delegate pattern
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
        val prefix = if (collectionName.isNotEmpty()) "$collectionName." else ""
        parent.block("${prefix}withType<$type>()") {
            createConfigurator(this).apply(init)
        }
    }
}

/**
 * Base implementation of the NamedDomainObjectCollectionDsl
 */
public abstract class AbstractNamedDomainObjectCollectionDsl<T, C>(
    override val parent: GradleDsl,
    override val collectionName: String = ""
) : NamedDomainObjectCollectionDsl<T, C>, GradleDsl by parent