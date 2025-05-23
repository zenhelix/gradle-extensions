plugins { `version-catalog` }

val catalogComponentName: String = "versionCatalog"

publishing {
    publications {
        create<MavenPublication>(project.name) {
            from(components[catalogComponentName])
        }
    }
}

val currentVersion = rootProject.version.toString()

catalog {
    versionCatalog {
        if (currentVersion != Project.DEFAULT_VERSION) {
            version("gradle-extensions") { strictly(currentVersion) }
        }

        rootProject.subprojects
            .filter {
                it.name.contains("gradle-extensions-platform-").not() && it.name != project.name
            }
            .forEach { project ->
                project.publishing.publications
                    .filterIsInstance<MavenPublication>()
                    .forEach {
                        library(it.artifactId, it.groupId, it.artifactId).apply {
                            if (currentVersion != Project.DEFAULT_VERSION) {
                                version { strictly(currentVersion) }
                            } else {
                                withoutVersion()
                            }
                        }
                    }
            }

    }
}