package io.github.zenhelix.gradle.test.dsl.utils

internal object FieldFormatter {

    fun formatValue(value: Any?): String = when (value) {
        null -> "null"
        is String -> formatStringValue(value)
        else -> value.toString()
    }

    private fun formatStringValue(value: String): String = if (value.contains('\n') || value.contains('\r') || value.contains('"')) {
        "\"\"\"${value.replace("\"\"\"", "\"\"\" + \"\"\"")}\"\"\""
    } else {
        "\"$value\""
    }

}