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

val modelixCoreVersion: String by project
val modelixVersion: String by project
val projectionalEditorVersion: String by project
val incrementalVersion: String by project
val mpsBuildVersion: String by project

dependencies {
    constraints {
        //modelix core
        api("org.modelix:authorization:${modelixCoreVersion}")
        api("org.modelix:light-model-client:${modelixCoreVersion}")
        api("org.modelix:model-server-lib:${modelixCoreVersion}")
        api("org.modelix.mps:metamodel-export:${modelixCoreVersion}")
        api("org.modelix:model-api-gen:${modelixCoreVersion}")
        api("org.modelix:model-api-gen-gradle:${modelixCoreVersion}")
        api("org.modelix:model-api-gen-runtime:${modelixCoreVersion}")
        api("org.modelix:model-api-gen-runtime-js:${modelixCoreVersion}")
        api("org.modelix:model-api-gen-runtime-jvm:${modelixCoreVersion}")
        api("org.modelix.model-api-gen:org.modelix.model-api-gen.gradle.plugin:${modelixCoreVersion}")
        api("org.modelix:model-api:${modelixCoreVersion}")
        api("org.modelix:model-api-js:${modelixCoreVersion}")
        api("org.modelix:model-api-jvm:${modelixCoreVersion}")
        api("org.modelix:model-client:${modelixCoreVersion}")
        api("org.modelix:model-client-js:${modelixCoreVersion}")
        api("org.modelix:model-client-jvm:${modelixCoreVersion}")
        api("org.modelix:model-server:${modelixCoreVersion}")
        api("org.modelix:model-server-api:${modelixCoreVersion}")
        api("org.modelix:model-server-api-js:${modelixCoreVersion}")
        api("org.modelix:model-server-api-jvm:${modelixCoreVersion}")
        api("org.modelix:model-server-with-dependencies:${modelixCoreVersion}")

        //projectional editor
        api("org.modelix:projectional-editor:${projectionalEditorVersion}")
        api("org.modelix:projectional-editor-js:${projectionalEditorVersion}")
        api("org.modelix:projectional-editor-jvm:${projectionalEditorVersion}")

        //incremental
        api("org.modelix:incremental:${incrementalVersion}")
        api("org.modelix:incremental-js:${incrementalVersion}")
        api("org.modelix:incremental-jvm:${incrementalVersion}")

        //mps build tools
        api("org.modelix.mps:build-tools-lib:${mpsBuildVersion}")
        api("org.modelix.mps:build-tools-gradle:${mpsBuildVersion}")
        api("org.modelix.mps.build-tools:org.modelix.mps.build-tools.gradle.plugin:${mpsBuildVersion}")
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
        //maven {
        //    name = "itemis"
        //    url = if (version.toString().contains("SNAPSHOT")) {
        //        uri("https://artifacts.itemis.cloud/repository/maven-mps-snapshots/")
        //    } else {
        //        uri("https://artifacts.itemis.cloud/repository/maven-mps-releases/")
        //    }
        //    credentials {
        //        username = project.findProperty("artifacts.itemis.cloud.user").toString()
        //        password = project.findProperty("artifacts.itemis.cloud.pw").toString()
        //    }
        //}
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
            //maven {
            //    name = "itemis"
            //    url = if (version.toString().contains("SNAPSHOT")) {
            //        uri("https://artifacts.itemis.cloud/repository/maven-mps-snapshots/")
            //    } else {
            //        uri("https://artifacts.itemis.cloud/repository/maven-mps-releases/")
            //    }
            //    credentials {
            //        username = project.findProperty("artifacts.itemis.cloud.user").toString()
            //        password = project.findProperty("artifacts.itemis.cloud.pw").toString()
            //    }
            //}
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