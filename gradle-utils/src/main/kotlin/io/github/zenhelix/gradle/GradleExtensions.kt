package io.github.zenhelix.gradle

import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.DependencySet

public fun DependencySet.find(group: String?, module: String, version: String? = null): Dependency? = this.find {
    it.group == group && it.name == module && it.version == version
}

public fun DependencySet.exist(group: String?, module: String, version: String? = null): Boolean = this.find(group, module, version) != null
