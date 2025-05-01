package io.github.zenhelix.gradle.testkit.parser

import io.github.zenhelix.gradle.testkit.GradleTasksOutputAssert.Companion.assertThat
import org.junit.jupiter.api.Test

class GradleTaskOutputParserTest {

    @Test
    fun parseGradleTasksOutput() {
        val output = """
            > Configure project :

            > Task :tasks

            ------------------------------------------------------------
            Tasks runnable from root project 'test'
            ------------------------------------------------------------

            Android tasks
            -------------
            androidDependencies - Displays the Android dependencies of the project.
            signingReport - Displays the signing info for the base and test modules
            sourceSets - Prints out all the source sets defined in this project.

            Build tasks
            -----------
            allMetadataJar - Assembles a jar archive containing the metadata for all Kotlin source sets.
            androidDebugSourcesJar - Assembles a jar archive containing the sources of target 'androidDebug'.
            androidReleaseSourcesJar - Assembles a jar archive containing the sources of target 'androidRelease'.
            assemble - Assemble main outputs for all the variants.
            assembleAndroidTest - Assembles all the Test applications.
            build - Assembles and tests this project.
            buildDependents - Assembles and tests this project and all projects that depend on it.
            buildKotlinToolingMetadata - Build metadata json file containing information about the used Kotlin tooling
            buildNeeded - Assembles and tests this project and all projects it depends on.
            clean - Deletes the build directory.
            cleanAllTests - Deletes all the test results.
            compileDebugAndroidTestSources
            compileDebugSources
            compileDebugUnitTestSources
            compileKotlinLinuxX64 - Compiles a klibrary from the 'main' compilation in target 'linuxX64'.
            compileReleaseSources
            compileReleaseUnitTestSources
            compileTestKotlinLinuxX64 - Compiles a klibrary from the 'test' compilation in target 'linuxX64'.
            extractDebugAnnotations - Extracts Android annotations for the debug variant into the archive file
            extractReleaseAnnotations - Extracts Android annotations for the release variant into the archive file
            jvmJar - Assembles an archive containing the main classes.
            jvmMainClasses - Assembles outputs for compilation 'main' of target 'jvm'
            jvmSourcesJar - Assembles a jar archive containing the sources of target 'jvm'.
            jvmTestClasses - Assembles outputs for compilation 'test' of target 'jvm'
            linkDebugTestLinuxX64 - Links a test executable 'debugTest' for a target 'linuxX64'.
            linkLinuxX64 - Links all binaries for target 'linuxX64'.
            linuxX64Binaries - Assembles outputs for target 'linuxX64'.
            linuxX64MainBinaries - Links all binaries for compilation 'main' of target 'linuxX64'.
            linuxX64MainKlibrary - Assembles outputs for compilation 'main' of target 'linuxX64'
            linuxX64SourcesJar - Assembles a jar archive containing the sources of target 'linuxX64'.
            linuxX64TestBinaries - Links all binaries for compilation 'test' of target 'linuxX64'.
            linuxX64TestKlibrary - Assembles outputs for compilation 'test' of target 'linuxX64'
            metadataCommonMainClasses - Assembles outputs for compilation 'commonMain' of target 'metadata'
            metadataMainClasses - Assembles outputs for compilation 'main' of target 'metadata'
            metadataSourcesJar - Assembles a jar archive containing the sources of target 'metadata'.
            sourcesJar - Assembles a jar archive containing the sources of target 'kotlin'.

            Build Setup tasks
            -----------------
            init - Initializes a new Gradle build.
            updateDaemonJvm - Generates or updates the Gradle Daemon JVM criteria.
            wrapper - Generates Gradle wrapper files.

            Help tasks
            ----------
            buildEnvironment - Displays all buildscript dependencies declared in root project 'test'.
            dependencies - Displays all dependencies declared in root project 'test'.
            dependencyInsight - Displays the insight into a specific dependency in root project 'test'.
            help - Displays a help message.
            javaToolchains - Displays the detected java toolchains.
            kotlinDslAccessorsReport - Prints the Kotlin code for accessing the currently available project extensions and conventions.
            outgoingVariants - Displays the outgoing variants of root project 'test'.
            projects - Displays the sub-projects of root project 'test'.
            properties - Displays the properties of root project 'test'.
            resolvableConfigurations - Displays the configurations that can be resolved in root project 'test'.
            tasks - Displays the tasks runnable from root project 'test'.

            Ide tasks
            ---------
            resolveIdeDependencies - Debugging/Diagnosing task that will resolve dependencies for the IDE

            Install tasks
            -------------
            installDebugAndroidTest - Installs the android (on device) tests for the Debug build.
            uninstallAll - Uninstall all applications.
            uninstallDebugAndroidTest - Uninstalls the android (on device) tests for the Debug build.

            Publishing tasks
            ----------------
            checksumAllPublications - Generate checksums
            checksumJvm - Generate checksums for jvm
            checksumKotlinMultiplatform - Generate checksums for kotlinMultiplatform
            checksumLinuxX64 - Generate checksums for linuxX64
            generateMetadataFileForAndroidReleasePublication - Generates the Gradle metadata file for publication 'androidRelease'.
            generateMetadataFileForJvmPublication - Generates the Gradle metadata file for publication 'jvm'.
            generateMetadataFileForKotlinMultiplatformPublication - Generates the Gradle metadata file for publication 'kotlinMultiplatform'.
            generateMetadataFileForLinuxX64Publication - Generates the Gradle metadata file for publication 'linuxX64'.
            generatePomFileForAndroidReleasePublication - Generates the Maven POM file for publication 'androidRelease'.
            generatePomFileForJvmPublication - Generates the Maven POM file for publication 'jvm'.
            generatePomFileForKotlinMultiplatformPublication - Generates the Maven POM file for publication 'kotlinMultiplatform'.
            generatePomFileForLinuxX64Publication - Generates the Maven POM file for publication 'linuxX64'.
            publish - Publishes all publications produced by this project.
            publishAllPublicationsToMavenCentralPortalRepository - Publishes all Maven publications produced by this project to the mavenCentralPortal repository.
            publishAllPublicationsToMavenLocalRepository - Publishes all Maven publications produced by this project to the MavenLocal repository.
            publishAndroidReleasePublicationToMavenLocal - Publishes Maven publication 'androidRelease' to the local Maven repository.
            publishAndroidReleasePublicationToMavenLocalRepository - Publishes Maven publication 'androidRelease' to Maven repository 'MavenLocal'.
            publishJvmPublicationToMavenCentralPortal - Publishes Maven publication 'jvm' to Maven repository 'mavenCentralPortal'.
            publishJvmPublicationToMavenLocal - Publishes Maven publication 'jvm' to the local Maven repository.
            publishJvmPublicationToMavenLocalRepository - Publishes Maven publication 'jvm' to Maven repository 'MavenLocal'.
            publishKotlinMultiplatformPublicationToMavenCentralPortal - Publishes Maven publication 'kotlinMultiplatform' to Maven repository 'mavenCentralPortal'.
            publishKotlinMultiplatformPublicationToMavenLocal - Publishes Maven publication 'kotlinMultiplatform' to the local Maven repository.
            publishKotlinMultiplatformPublicationToMavenLocalRepository - Publishes Maven publication 'kotlinMultiplatform' to Maven repository 'MavenLocal'.
            publishLinuxX64PublicationToMavenCentralPortal - Publishes Maven publication 'linuxX64' to Maven repository 'mavenCentralPortal'.
            publishLinuxX64PublicationToMavenLocal - Publishes Maven publication 'linuxX64' to the local Maven repository.
            publishLinuxX64PublicationToMavenLocalRepository - Publishes Maven publication 'linuxX64' to Maven repository 'MavenLocal'.
            publishToMavenLocal - Publishes all Maven publications produced by this project to the local Maven cache.
            zipDeploymentAllPublications - Deployment bundle for all publications
            zipDeploymentJvmPublication - Deployment bundle for jvm
            zipDeploymentKotlinMultiplatformPublication - Deployment bundle for kotlinMultiplatform
            zipDeploymentLinuxX64Publication - Deployment bundle for linuxX64

            Run tasks
            ---------
            jvmRun - Jvm Run task for target 'jvm' and compilation 'main'. This task can act as carrier for the IDE to execute jvm based code

            Verification tasks
            ------------------
            allTests - Runs the tests for all targets and create aggregated report
            check - Runs all checks.
            checkJetifier - Checks whether Jetifier is needed for the current project
            checkKotlinGradlePluginConfigurationErrors - Checks that Kotlin Gradle Plugin hasn't reported project configuration errors, failing otherwise. This task always runs before compileKotlin* or similar tasks.
            connectedAndroidTest - Installs and runs instrumentation tests for all flavors on connected devices.
            connectedCheck - Runs all device checks on currently connected devices.
            connectedDebugAndroidTest - Installs and runs the tests for debug on connected devices.
            deviceAndroidTest - Installs and runs instrumentation tests using all Device Providers.
            deviceCheck - Runs all device checks using Device Providers and Test Servers.
            jvmTest - Runs the tests of the test test run.
            lint - Runs lint on the default variant.
            lintDebug - Print text output from the corresponding lint report task
            lintFix - Runs lint on the default variant and applies any safe suggestions to the source code.
            lintRelease - Print text output from the corresponding lint report task
            linuxX64Test - Executes Kotlin/Native unit tests for target linuxX64.
            test - Run unit tests for all variants.
            testDebugUnitTest - Run unit tests for the debug build.
            testReleaseUnitTest - Run unit tests for the release build.
            updateLintBaseline - Updates the lint baseline using the default variant.

            Other tasks
            -----------
            analyzeDebugAndroidTestDependencies
            analyzeDebugDependencies
            analyzeDebugUnitTestDependencies
            analyzeReleaseDependencies
            analyzeReleaseUnitTestDependencies
            assembleDebug - Assembles main output for variant debug
            assembleDebugAndroidTest - Assembles main output for variant debugAndroidTest
            assembleDebugUnitTest - Assembles main output for variant debugUnitTest
            assembleRelease - Assembles main output for variant release
            assembleReleaseUnitTest - Assembles main output for variant releaseUnitTest
            bundleDebugAar - Assembles a bundle containing the library in debug.
            bundleDebugLocalLintAar - Assembles a bundle containing the library in debug.
            bundleLibCompileToJarDebug
            bundleLibCompileToJarRelease
            bundleLibRuntimeToDirDebug
            bundleLibRuntimeToDirRelease
            bundleLibRuntimeToJarDebug
            bundleLibRuntimeToJarRelease
            bundleReleaseAar - Assembles a bundle containing the library in release.
            bundleReleaseLocalLintAar - Assembles a bundle containing the library in release.
            checkDebugAndroidTestAarMetadata
            checkDebugAndroidTestDuplicateClasses
            checkDebugManifest
            checkReleaseManifest
            compileCommonMainKotlinMetadata - Compiles the kotlin sources in compilation 'commonMain' in target 'metadata' to Metadata.
            compileDebugAndroidTestJavaWithJavac
            compileDebugAndroidTestKotlinAndroid - Compiles the debugAndroidTest kotlin.
            compileDebugAndroidTestShaders
            compileDebugJavaWithJavac
            compileDebugKotlinAndroid - Compiles the debug kotlin.
            compileDebugLibraryResources
            compileDebugShaders
            compileDebugUnitTestJavaWithJavac
            compileDebugUnitTestKotlinAndroid - Compiles the debugUnitTest kotlin.
            compileKotlinJvm - Compiles the compilation 'main' in target 'jvm'.
            compileKotlinMetadata - Compiles the kotlin sources in compilation 'main' in target 'metadata' to Metadata.
            compileLint
            compileLintChecks
            compileReleaseJavaWithJavac
            compileReleaseKotlinAndroid - Compiles the release kotlin.
            compileReleaseLibraryResources
            compileReleaseShaders
            compileReleaseUnitTestJavaWithJavac
            compileReleaseUnitTestKotlinAndroid - Compiles the releaseUnitTest kotlin.
            compileTestKotlinJvm - Compiles the compilation 'test' in target 'jvm'.
            components - Displays the components produced by root project 'test'. [deprecated]
            compressDebugAndroidTestAssets
            consumeConfigAttr
            copyDebugJniLibsProjectAndLocalJars
            copyDebugJniLibsProjectOnly
            copyReleaseJniLibsProjectAndLocalJars
            copyReleaseJniLibsProjectOnly
            createDebugAndroidTestApkListingFileRedirect
            createDebugVariantModel
            createFullJarDebug
            createFullJarRelease
            createMockableJar
            createReleaseVariantModel
            debugAssetsCopyForAGP
            dependentComponents - Displays the dependent components of components in root project 'test'. [deprecated]
            desugarDebugAndroidTestFileDependencies
            dexBuilderDebugAndroidTest
            exportDebugConsumerProguardFiles
            exportReleaseConsumerProguardFiles
            extractDebugSupportedLocales
            extractDeepLinksDebug
            extractDeepLinksForAarDebug
            extractDeepLinksForAarRelease
            extractDeepLinksRelease
            extractProguardFiles
            extractReleaseSupportedLocales
            generateDebugAndroidTestAssets
            generateDebugAndroidTestResources
            generateDebugAndroidTestResValues
            generateDebugAndroidTestSources
            generateDebugAssets
            generateDebugExternalPublicTxt
            generateDebugLintModel
            generateDebugLintReportModel
            generateDebugLintVitalModel
            generateDebugResources
            generateDebugResValues
            generateDebugRFile
            generateDebugSources
            generateDebugUnitTestAssets
            generateDebugUnitTestResources
            generateDebugUnitTestSources
            generateDebugUnitTestStubRFile
            generateProjectStructureMetadata - Generates serialized project structure metadata of the current project (for tooling)
            generateReleaseAssets
            generateReleaseExternalPublicTxt
            generateReleaseLintModel
            generateReleaseLintReportModel
            generateReleaseLintVitalModel
            generateReleaseResources
            generateReleaseResValues
            generateReleaseRFile
            generateReleaseSources
            generateReleaseUnitTestAssets
            generateReleaseUnitTestResources
            generateReleaseUnitTestSources
            generateReleaseUnitTestStubRFile
            javaPreCompileDebug
            javaPreCompileDebugAndroidTest
            javaPreCompileDebugUnitTest
            javaPreCompileRelease
            javaPreCompileReleaseUnitTest
            jvmProcessResources - Processes file collection.
            jvmTestProcessResources - Processes file collection.
            lintAnalyzeDebug - Run lint analysis on the debug variant
            lintAnalyzeRelease - Run lint analysis on the release variant
            lintFixDebug - Fix lint on the debug variant
            lintFixRelease - Fix lint on the release variant
            lintReportDebug - Run lint on the debug variant
            lintReportRelease - Run lint on the release variant
            lintVitalAnalyzeDebug - Run lint analysis with only the fatal issues enabled on the debug variant
            lintVitalAnalyzeRelease - Run lint analysis with only the fatal issues enabled on the release variant
            linuxX64Klib
            linuxX64ProcessResources - Processes file collection.
            linuxX64TestProcessResources - Processes file collection.
            mapDebugAndroidTestSourceSetPaths
            mapDebugSourceSetPaths
            mapReleaseSourceSetPaths
            mergeDebugAndroidTestAssets
            mergeDebugAndroidTestGeneratedProguardFiles
            mergeDebugAndroidTestJavaResource
            mergeDebugAndroidTestJniLibFolders
            mergeDebugAndroidTestNativeLibs
            mergeDebugAndroidTestResources
            mergeDebugAndroidTestShaders
            mergeDebugAssets
            mergeDebugConsumerProguardFiles
            mergeDebugGeneratedProguardFiles
            mergeDebugJavaResource
            mergeDebugJniLibFolders
            mergeDebugNativeLibs
            mergeDebugResources
            mergeDebugShaders
            mergeExtDexDebugAndroidTest
            mergeLibDexDebugAndroidTest
            mergeProjectDexDebugAndroidTest
            mergeReleaseAssets
            mergeReleaseConsumerProguardFiles
            mergeReleaseGeneratedProguardFiles
            mergeReleaseJavaResource
            mergeReleaseJniLibFolders
            mergeReleaseNativeLibs
            mergeReleaseResources
            mergeReleaseShaders
            metadataCommonMainProcessResources - Processes file collection.
            metadataProcessResources - Processes file collection.
            model - Displays the configuration model of root project 'test'. [deprecated]
            packageDebugAndroidTest
            packageDebugAssets
            packageDebugRenderscript
            packageDebugResources
            packageReleaseAssets
            packageReleaseRenderscript
            packageReleaseResources
            parseDebugLocalResources
            parseReleaseLocalResources
            preBuild
            preDebugAndroidTestBuild
            preDebugBuild
            preDebugUnitTestBuild
            prepareDebugArtProfile
            prepareKotlinBuildScriptModel
            prepareKotlinIdeaImport - Umbrella for all tasks required to run before IDEA/Gradle import
            prepareLintJarForPublish
            prepareReleaseArtProfile
            preReleaseBuild
            preReleaseUnitTestBuild
            processDebugAndroidTestJavaRes
            processDebugAndroidTestManifest
            processDebugAndroidTestResources
            processDebugJavaRes
            processDebugManifest
            processDebugUnitTestJavaRes
            processReleaseJavaRes
            processReleaseManifest
            processReleaseUnitTestJavaRes
            releaseAssetsCopyForAGP
            resolveConfigAttr
            setupLldbScript - Generate lldbinit file with imported konan_lldb.py script
            signAndroidReleasePublication - Signs all artifacts in the 'androidRelease' publication.
            signingConfigWriterDebugAndroidTest
            signJvmPublication - Signs all artifacts in the 'jvm' publication.
            signKotlinMultiplatformPublication - Signs all artifacts in the 'kotlinMultiplatform' publication.
            signLinuxX64Publication - Signs all artifacts in the 'linuxX64' publication.
            stripDebugAndroidTestDebugSymbols
            stripDebugDebugSymbols
            stripReleaseDebugSymbols
            syncDebugLibJars
            syncReleaseLibJars
            transformCommonMainDependenciesMetadata - Generates serialized dependencies metadata for compilation 'commonMain' (for tooling)
            transformDependenciesMetadata
            updateLintBaselineDebug - Update the lint baseline using the debug variant
            updateLintBaselineRelease - Update the lint baseline using the release variant
            validateSigningDebugAndroidTest
            verifyReleaseResources
            writeDebugAarMetadata
            writeDebugAndroidTestSigningConfigVersions
            writeDebugLintModelMetadata
            writeReleaseAarMetadata
            writeReleaseLintModelMetadata

            Rules
            -----
            Pattern: clean<TaskName>: Cleans the output files of a task.
            Pattern: build<ConfigurationName>: Assembles the artifacts of a configuration.

            BUILD SUCCESSFUL in 21s
            1 actionable task: 1 executed
        """.trimIndent()

        assertThat(parseGradleTasksOutput(output))
            .hasCategory("android", "build", "build setup", "help", "ide", "install", "publishing", "run", "verification", "other")
            .hasRule("clean<TaskName>", "build<ConfigurationName>")
            .hasTasksInCategory("run", "jvmRun")
            .hasTasksInCategory("install", "installDebugAndroidTest", "uninstallAll", "uninstallDebugAndroidTest")
    }

}