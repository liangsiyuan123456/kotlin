import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

description = "Kotlin Library metadata manipulation library"

plugins {
    kotlin("jvm")
    id("jps-compatible")
}

group = "org.jetbrains.kotlinx"

// TODO: Use own version?
val deployVersion = findProperty("kotlinxMetadataDeployVersion") as String?
version = deployVersion ?: "0.1-SNAPSHOT"

jvmTarget = "1.6"
javaHome = rootProject.extra["JDK_16"] as String

sourceSets {
    "main" { projectDefault() }
    "test" { projectDefault() }
}

val shadows by configurations.creating {
    isTransitive = false
}
configurations.getByName("compileOnly").extendsFrom(shadows)
configurations.getByName("testCompile").extendsFrom(shadows)

dependencies {
    compile(kotlinStdlib())
    shadows(project(":kotlinx-metadata"))
    shadows(project(":core:metadata"))
    shadows(project(":compiler:serialization"))
    compile(project(":kotlin-util-klib-metadata"))
    shadows(protobufLite())
    testCompile(commonDep("junit:junit"))
    testCompile(intellijDep()) { includeJars("asm-all", rootProject = rootProject) }
    testCompileOnly(project(":kotlin-reflect-api"))
    testRuntime(project(":kotlin-reflect"))
}

if (deployVersion != null) {
    publish()
}

noDefaultJar()

tasks.register<ShadowJar>("shadowJar") {
    callGroovy("manifestAttributes", manifest, project)
    manifest.attributes["Implementation-Version"] = version

    from(mainSourceSet.output)
    exclude("**/*.proto")
    configurations = listOf(shadows)
//    relocate("org.jetbrains.kotlin", "kotlinx.metadata.internal")

    val artifactRef = outputs.files.singleFile
    runtimeJarArtifactBy(this, artifactRef)
    addArtifact("runtime", this, artifactRef)
}

sourcesJar {
    for (dependency in shadows.dependencies) {
        if (dependency is ProjectDependency) {
            val javaPlugin = dependency.dependencyProject.convention.findPlugin(JavaPluginConvention::class.java)
            if (javaPlugin != null) {
                from(javaPlugin.sourceSets["main"].allSource)
            }
        }
    }
}

javadocJar()