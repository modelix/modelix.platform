val modelixPatchVersion: String by rootProject
val modelixVersion = "2020.3.$modelixPatchVersion"
val platformVersion = file("../platform_version.txt").readText().trim()

javaPlatform {
    allowDependencies()
}

dependencies {
    api(platform("org.modelix:platform:$platformVersion"))
    constraints {
        api("org.modelix:model-download-gradle:$modelixVersion")
        api("org.modelix.mps.model-download-gradle:org.modelix.mps.model-download-gradle.gradle.plugin:$modelixVersion")
        api("org.modelix.mps:model-server-sync-plugin:$modelixVersion")
        api("org.modelix.mps:build-solution:$modelixVersion")
        api("org.modelix.mps:headless-runner:$modelixVersion")
        api("org.modelix.mps:web-editors-plugin:$modelixVersion")
    }
}