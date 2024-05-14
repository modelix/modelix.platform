plugins {
    `java-platform`
    `maven-publish`
    `version-catalog`
}

dependencies {
    constraints {
        //modelix core
        api(libs.modelix.authorization)
        api(libs.modelix.bulk.model.sync.lib)
        api(libs.modelix.bulk.model.sync.lib.js)
        api(libs.modelix.bulk.model.sync.lib.jvm)
        api(libs.modelix.bulk.model.sync.gradle)
        api(libs.modelix.bulk.model.sync.solution)
        api(libs.modelix.bulk.model.sync.plugin)
        api(libs.modelix.kotlin.utils)
        api(libs.modelix.kotlin.utils.js)
        api(libs.modelix.kotlin.utils.jvm)
        api(libs.modelix.light.model.client)
        api(libs.modelix.light.model.client.js)
        api(libs.modelix.light.model.client.jvm)
        api(libs.modelix.model.server.lib)
        api(libs.modelix.metamodel.export)
        api(libs.modelix.model.api.gen)
        api(libs.modelix.model.api.gen.gradle)
        api(libs.modelix.model.api.gen.runtime)
        api(libs.modelix.model.api.gen.runtime.js)
        api(libs.modelix.model.api.gen.runtime.jvm)
        api(libs.modelix.model.api.gen.plugin)
        api(libs.modelix.model.api)
        api(libs.modelix.model.api.js)
        api(libs.modelix.model.api.jvm)
        api(libs.modelix.model.client)
        api(libs.modelix.model.client.js)
        api(libs.modelix.model.client.jvm)
        api(libs.modelix.model.datastructure)
        api(libs.modelix.model.datastructure.js)
        api(libs.modelix.model.datastructure.jvm)
        api(libs.modelix.model.server)
        api(libs.modelix.model.server.api)
        api(libs.modelix.model.server.api.js)
        api(libs.modelix.model.server.api.jvm)
        api(libs.modelix.model.server.lib)
        api(libs.modelix.model.server.with.dependencies)
        api(libs.modelix.modelql.client)
        api(libs.modelix.modelql.client.js)
        api(libs.modelix.modelql.client.jvm)
        api(libs.modelix.modelql.core)
        api(libs.modelix.modelql.core.js)
        api(libs.modelix.modelql.core.jvm)
        api(libs.modelix.modelql.html)
        api(libs.modelix.modelql.html.js)
        api(libs.modelix.modelql.html.jvm)
        api(libs.modelix.modelql.server)
        api(libs.modelix.modelql.typed)
        api(libs.modelix.modelql.typed.js)
        api(libs.modelix.modelql.typed.jvm)
        api(libs.modelix.modelql.untyped)
        api(libs.modelix.modelql.untyped.js)
        api(libs.modelix.modelql.untyped.jvm)
        api(libs.modelix.mps.model.adapters)
        api(libs.modelix.mps.model.server.plugin)

        //incremental
        api(libs.modelix.incremental)
        api(libs.modelix.incremental.js)
        api(libs.modelix.incremental.jvm)

        //mps build tools
        api(libs.modelix.build.tools.lib)
        api(libs.modelix.build.tools.gradle)
        api(libs.modelix.build.tools.plugin)

        //mps plugins
        api(libs.modelix.mps.diff.plugin)
        api(libs.modelix.mps.generator.execution.plugin)
        api(libs.modelix.mps.legacy.sync.plugin)
        api(libs.modelix.mps.sync.plugin.lib)
        api(libs.modelix.mps.sync.plugin)

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
