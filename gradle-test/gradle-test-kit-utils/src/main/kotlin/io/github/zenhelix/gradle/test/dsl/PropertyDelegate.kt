package io.github.zenhelix.gradle.test.dsl

/**
 * Property delegate for DSL properties
 */
public class PropertyDelegate<T>(
    private val dsl: GradleDsl,
    private val propertyName: String? = null,
    private val customFormatter: ((T) -> String)? = null
) {
    public operator fun getValue(thisRef: Any?, property: kotlin.reflect.KProperty<*>): T {
        throw UnsupportedOperationException("Property values can only be set, not retrieved in this DSL")
    }

    public operator fun setValue(thisRef: Any?, property: kotlin.reflect.KProperty<*>, value: T) {
        val name = propertyName ?: property.name
        val formattedValue = customFormatter?.invoke(value) ?: formatValue(value)
        dsl.line("$name = $formattedValue")
    }

    private fun formatValue(value: Any?): String {
        return when (value) {
            is String -> "\"$value\""
            is Boolean, is Number -> value.toString()
            else -> value.toString()
        }
    }
}

/**
 * Extension function to create property delegate with custom formatter
 */
public fun <T> GradleDsl.propertyOf(
    propertyName: String? = null,
    formatter: ((T) -> String)? = null
): PropertyDelegate<T> = PropertyDelegate(this, propertyName, formatter)