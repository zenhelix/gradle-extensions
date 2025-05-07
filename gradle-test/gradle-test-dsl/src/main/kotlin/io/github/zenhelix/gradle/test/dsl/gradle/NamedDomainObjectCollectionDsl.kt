package io.github.zenhelix.gradle.test.dsl.gradle

import io.github.zenhelix.gradle.test.dsl.DslPath
import io.github.zenhelix.gradle.test.dsl.GradleDsl
import io.github.zenhelix.gradle.test.dsl.utils.DslReference

public interface NamedDomainObjectCollectionDsl<T, C : GradleDsl> {

    public val parent: GradleDsl

    public val collectionName: String
        get() = ""

    public val dslPath: DslPath
        get() = if (collectionName.isEmpty()) parent.dslPath
        else parent.dslPath.append(collectionName)

    public fun asReference(): DslReference = DslReference(dslPath, parent.dslPath)

    public fun createConfigurator(dsl: GradleDsl): C

    public fun getByName(name: String, init: C.() -> Unit = {})
    public fun named(name: String, init: C.() -> Unit = {})
    public fun all(init: C.() -> Unit = {})
    public fun configureEach(init: C.() -> Unit)

    public fun getting(name: String, init: C.() -> Unit) {
        parent.block("val $name by getting") {
            createConfigurator(this).apply(init)
        }
    }

    public fun getContextPrefix(): String = if (collectionName.isEmpty() || dslPath.isCurrentContext(collectionName)) {
        ""
    } else {
        "$collectionName."
    }

}

public interface NamedDomainObjectContainerDsl<T, C : GradleDsl> : NamedDomainObjectCollectionDsl<T, C> {

    public fun <S : T> create(type: String, name: String, init: C.() -> Unit = {})
    public fun <S : T> register(type: String, name: String, init: C.() -> Unit = {})
}

public abstract class AbstractNamedDomainObjectCollectionDsl<T, C : GradleDsl>(
    override val parent: GradleDsl,
    override val collectionName: String = ""
) : NamedDomainObjectCollectionDsl<T, C>, GradleDsl by parent {

    override val dslPath: DslPath
        get() = if (collectionName.isEmpty()) {
            parent.dslPath
        } else {
            parent.dslPath.append(collectionName)
        }

    override fun getContextPrefix(): String = dslPath.getContextPrefix(collectionName)

    public override fun getByName(name: String, init: C.() -> Unit) {
        val prefix = getContextPrefix()
        parent.block("${prefix}getByName(${formatValue(name)})") {
            withDsl(createConfigurator(this), init)
        }
    }

    public override fun named(name: String, init: C.() -> Unit) {
        val prefix = getContextPrefix()
        parent.block("${prefix}named(${formatValue(name)})") {
            withDsl(createConfigurator(this), init)
        }
    }

    public inline fun <reified S : T> withType(noinline init: C.() -> Unit) {
        val prefix = getContextPrefix()
        parent.block("${prefix}withType<${S::class.simpleName}>") {
            withDsl(createConfigurator(this), init)
        }
    }

    public override fun all(init: C.() -> Unit) {
        val prefix = getContextPrefix()
        parent.block("${prefix}all") {
            withDsl(createConfigurator(this), init)
        }
    }

    public override fun configureEach(init: C.() -> Unit) {
        val prefix = getContextPrefix()
        parent.block("${prefix}configureEach") {
            withDsl(createConfigurator(this), init)
        }
    }

}

public abstract class AbstractNamedDomainObjectContainerDsl<T, C : GradleDsl>(
    parent: GradleDsl,
    collectionName: String = ""
) : AbstractNamedDomainObjectCollectionDsl<T, C>(parent, collectionName), NamedDomainObjectContainerDsl<T, C> {

    public inline fun <reified S : T> create(name: String, noinline init: C.() -> Unit) {
        val prefix = getContextPrefix()
        parent.block("${prefix}create<${S::class.simpleName}>(${formatValue(name)})") {
            withDsl(createConfigurator(this), init)
        }
    }

    override fun <S : T> create(type: String, name: String, init: C.() -> Unit) {
        val prefix = getContextPrefix()
        parent.block("${prefix}create<$type>(${formatValue(name)})") {
            withDsl(createConfigurator(this), init)
        }
    }

    override fun <S : T> register(type: String, name: String, init: C.() -> Unit) {
        val prefix = getContextPrefix()
        parent.block("${prefix}register<$type>(${formatValue(name)})") {
            withDsl(createConfigurator(this), init)
        }
    }

}
