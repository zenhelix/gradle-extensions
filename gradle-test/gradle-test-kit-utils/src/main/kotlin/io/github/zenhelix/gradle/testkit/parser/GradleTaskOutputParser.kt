package io.github.zenhelix.gradle.testkit.parser

public data class GradleTask(val name: String, val description: String? = null)
public data class TaskCategory(val name: String, val tasks: List<GradleTask>)
public data class GradleRule(val pattern: String, val description: String)
public data class GradleOutput(val categories: List<TaskCategory>, val rules: List<GradleRule>)

public fun parseGradleTasksOutput(output: String): GradleOutput {
    val lines = output.lines()
    val categories = mutableListOf<TaskCategory>()
    val rules = mutableListOf<GradleRule>()

    var i = 0
    while (i < lines.size) {
        val line = lines[i].trim()

        if (isCategoryHeaderLine(line) && hasFollowingDivider(lines, i)) {
            val categoryName = normalizeCategoryName(line)

            val contentIndex = findContentIndexAfterHeader(lines, i)
            i = contentIndex

            val (tasks, newIndex) = collectTasks(lines, i)
            i = newIndex

            if (tasks.isNotEmpty()) {
                categories.add(TaskCategory(categoryName, tasks))
            }
        }
        else if (isRulesHeaderLine(line) && hasFollowingDivider(lines, i)) {
            val contentIndex = findContentIndexAfterHeader(lines, i)
            i = contentIndex

            val (collectedRules, newIndex) = collectRules(lines, i)
            rules.addAll(collectedRules)
            i = newIndex
        }
        else {
            i++
        }
    }

    return GradleOutput(categories, rules)
}

private fun normalizeCategoryName(categoryLine: String): String {
    return categoryLine.replace(" tasks", "").lowercase()
}

private fun isCategoryHeaderLine(line: String): Boolean {
    return line.contains(" tasks")
}

private fun isRulesHeaderLine(line: String): Boolean {
    return line == "Rules"
}

private fun isDividerLine(line: String): Boolean {
    return line.isNotEmpty() && line.all { it == '-' }
}

private fun hasFollowingDivider(lines: List<String>, index: Int): Boolean {
    return index + 1 < lines.size && isDividerLine(lines[index + 1].trim())
}

private fun isNewSectionHeader(lines: List<String>, index: Int): Boolean {
    val line = lines[index].trim()
    return (isCategoryHeaderLine(line) && hasFollowingDivider(lines, index)) ||
            (isRulesHeaderLine(line) && hasFollowingDivider(lines, index))
}

private fun findContentIndexAfterHeader(lines: List<String>, headerIndex: Int): Int {
    var index = headerIndex + 1

    while (index < lines.size) {
        if (isDividerLine(lines[index].trim())) {
            return index + 1
        }
        index++
    }

    return index
}

private fun collectTasks(lines: List<String>, startIndex: Int): Pair<List<GradleTask>, Int> {
    val tasks = mutableListOf<GradleTask>()
    var i = startIndex

    while (i < lines.size) {
        val line = lines[i].trim()

        if (line.isEmpty()) {
            i++
            continue
        }

        if (isNewSectionHeader(lines, i)) {
            break
        }

        val parts = line.split(" - ", limit = 2)
        tasks.add(
            if (parts.size == 2) {
                GradleTask(parts[0].trim(), parts[1].trim())
            } else {
                GradleTask(line)
            }
        )

        i++
    }

    return Pair(tasks, i)
}

private fun collectRules(lines: List<String>, startIndex: Int): Pair<List<GradleRule>, Int> {
    val rules = mutableListOf<GradleRule>()
    var i = startIndex

    while (i < lines.size) {
        val line = lines[i].trim()

        if (line.startsWith("Pattern:")) {
            val parts = line.removePrefix("Pattern:").split(":", limit = 2)
            if (parts.size == 2) {
                rules.add(GradleRule(parts[0].trim(), parts[1].trim()))
            }
        } else if (line.contains("BUILD SUCCESSFUL")) {
            break
        }

        i++
    }

    return Pair(rules, i)
}