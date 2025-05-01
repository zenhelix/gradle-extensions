plugins { `java-platform` }

val javaPlatformComponentName: String = "javaPlatform"

javaPlatform {
    allowDependencies()
}

publishing {
    publications {
        create<MavenPublication>(project.name) {
            from(components.getByName(javaPlatformComponentName))
        }
    }
}

dependencies {
    constraints {
        rootProject.subprojects.filter { it.childProjects.isEmpty() }.filter { it.name.contains("gradle-extensions-platform-").not() }.forEach {
            api(it)
        }
    }
}
