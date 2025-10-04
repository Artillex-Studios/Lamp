buildscript {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:2.0.10")
    }
}

plugins {
    id("java")
    id("maven-publish")
}

group = "io.github.revxrsal"
version = "4.0.0-rc.19"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}

subprojects {

    group = rootProject.group
    version = rootProject.version

    apply(plugin = "java")

    val isExample = project.path.startsWith(":example")


    if (!isExample)
        apply(plugin = "maven-publish")

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(8))
        }
    }

    if (!isExample)
        publishing {
            publications {
                create<MavenPublication>("maven") {
                    groupId = group.toString()
                    artifactId = "lamp.${project.name}"
                    version = version.toString()

                    from(components["java"])

                    pom {
                        name.set("Lamp")
                        description.set("A modern annotation-driven commands framework for Java")
                        inceptionYear.set("2024")
                        url.set("https://github.com/Revxrsal/Lamp/")
                        licenses {
                            license {
                                name.set("MIT")
                                url.set("https://mit-license.org/")
                                distribution.set("https://mit-license.org/")
                            }
                        }
                        developers {
                            developer {
                                id.set("revxrsal")
                                name.set("Revxrsal")
                                url.set("https://github.com/Revxrsal/")
                            }
                        }
                        scm {
                            url.set("https://github.com/Revxrsal/Lamp/")
                            connection.set("scm:git:git://github.com/Revxrsal/Lamp.git")
                            developerConnection.set("scm:git:ssh://git@github.com/Revxrsal/Lamp.git")
                        }
                    }
                }
            }

            repositories {
                maven {
                    name = "ArtillexStudios"
                    url = uri("https://repo.artillex-studios.com/releases/")
                    credentials(PasswordCredentials::class) {
                        username = project.properties["maven_username"].toString()
                        password = project.properties["maven_password"].toString()
                    }
                }
            }
        }


    repositories {
        mavenCentral()
    }

    dependencies {
        compileOnly("org.projectlombok:lombok:1.18.30")
        annotationProcessor("org.projectlombok:lombok:1.18.30")

        compileOnly("org.jetbrains:annotations:24.0.1")
    }
}
