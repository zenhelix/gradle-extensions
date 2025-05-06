package io.github.zenhelix.gradle.test.dsl.utils

import io.github.zenhelix.gradle.test.dsl.GradleDsl
import kotlin.reflect.KProperty

public class PropertyDelegate<T>(
    private val dsl: GradleDsl,
    private val propertyName: String? = null,
    private val customFormatter: ((T) -> String)? = null
) {
    public operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        throw UnsupportedOperationException("Property values can only be set, not retrieved in this DSL")
    }

    public operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        val name = propertyName ?: property.name
        val formattedValue = customFormatter?.invoke(value) ?: FieldFormatter.formatValue(value)
        dsl.line("$name = $formattedValue")
    }

}
