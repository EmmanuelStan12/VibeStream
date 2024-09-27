plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "VibeStream"
include("features:auth")
include("app")
include("security")
include("core:api")
include("core:testing")
findProject(":core:testing")?.name = "testing"
