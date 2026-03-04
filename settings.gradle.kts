pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "CoursesApp"

include(":app")
include(":core")
include(":domain")
include(":data")
include(":feature-login")
include(":feature-main")
include(":feature-favorites")
include(":feature-account")
