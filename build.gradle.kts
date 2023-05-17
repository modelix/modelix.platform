/*
 * This file was generated by the Gradle 'init' task.
 *
 * This is a general purpose Gradle build.
 * Learn more about Gradle by exploring our samples at https://docs.gradle.org/8.0.2/samples
 */
plugins {
    `java-platform`
    `maven-publish`
}

dependencies {
    constraints {
        //modelix core
        api(libs.modelix.authorization)
        api(libs.modelix.light.model.client)
        api(libs.modelix.model.server.lib)
        api(libs.modelix.metamodel.export)
        api(libs.modelix.model.api.gen)
        api(libs.modelix.model.api.gen.gradle)
        api(libs.modelix.model.api.gen.plugin)
        api(libs.modelix.model.api.gen.runtime)
        api(libs.modelix.model.api.gen.runtime.js)
        api(libs.modelix.model.api.gen.runtime.jvm)
        api(libs.modelix.model.api)
        api(libs.modelix.model.api.js)
        api(libs.modelix.model.api.jvm)
        api(libs.modelix.model.client)
        api(libs.modelix.model.client.js)
        api(libs.modelix.model.client.jvm)
        api(libs.modelix.model.server)
        api(libs.modelix.model.server.api)
        api(libs.modelix.model.server.api.js)
        api(libs.modelix.model.server.api.jvm)
        api(libs.modelix.model.server.with.dependencies)

        //incremental
        api(libs.modelix.incremental)
        api(libs.modelix.incremental.js)
        api(libs.modelix.incremental.jvm)

        //mps build tools
        api(libs.modelix.build.tools.lib)
        api(libs.modelix.build.tools.gradle)
        api(libs.modelix.build.tools.plugin)
    }
}

val platformVersion = file("platform_version.txt").readText().trim()

publishing {
    publications {
        create<MavenPublication>("modelixPlatform") {
            group = "org.modelix"
            version = platformVersion

            from(components["javaPlatform"])
        }
    }
    repositories {
        maven {
            name = "itemis"
            url = if (version.toString().contains("SNAPSHOT")) {
                uri("https://artifacts.itemis.cloud/repository/maven-mps-snapshots/")
            } else {
                uri("https://artifacts.itemis.cloud/repository/maven-mps-releases/")
            }
            credentials {
                username = project.findProperty("artifacts.itemis.cloud.user").toString()
                password = project.findProperty("artifacts.itemis.cloud.pw").toString()
            }
        }
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/modelix/modelix.platform")
            credentials {
                username = project.findProperty("gpr.user") as? String ?: System.getenv("GITHUB_ACTOR")
                password = project.findProperty("gpr.key") as? String ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }
}
subprojects {
    apply(plugin="java-platform")
    apply(plugin="maven-publish")

    publishing {
        publications {
            create<MavenPublication>("modelixMpsPlatform") {
                group = "org.modelix"
                version = platformVersion

                from(components["javaPlatform"])
            }
        }
        repositories {
            maven {
                name = "itemis"
                url = if (version.toString().contains("SNAPSHOT")) {
                    uri("https://artifacts.itemis.cloud/repository/maven-mps-snapshots/")
                } else {
                    uri("https://artifacts.itemis.cloud/repository/maven-mps-releases/")
                }
                credentials {
                    username = project.findProperty("artifacts.itemis.cloud.user").toString()
                    password = project.findProperty("artifacts.itemis.cloud.pw").toString()
                }
            }
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/modelix/modelix.platform")
                credentials {
                    username = project.findProperty("gpr.user") as? String ?: System.getenv("GITHUB_ACTOR")
                    password = project.findProperty("gpr.key") as? String ?: System.getenv("GITHUB_TOKEN")
                }
            }
        }
    }
}

