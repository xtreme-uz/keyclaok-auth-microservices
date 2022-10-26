import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
	repositories {
		gradlePluginPortal()
		mavenCentral()
	}
	dependencies {
		classpath("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
		classpath("org.jetbrains.kotlin:kotlin-reflect")
	}
	extra.apply {
		set("protobufVersion", "3.19.1")
		set("grpcVersion", "1.50.0")
	}
}

plugins {
	kotlin("jvm") version "1.7.20"
	id("org.springframework.boot") version "2.7.5"
	id("io.spring.dependency-management") version "1.1.0"
	id("com.google.protobuf") version "0.9.1" apply false
}

dependencies {
	platform(project(":shared:platform"))
}

subprojects {
	group = "uz.xtreme"
	version = "1.0.0"

	apply {
		plugin("maven-publish")
	}

	repositories {
		mavenCentral()
	}

	tasks {
		withType<JavaCompile> {
			options.compilerArgs.add("-Xlint:all")
		}

		withType<KotlinCompile> {
			kotlinOptions.jvmTarget = "17"
			kotlinOptions.allWarningsAsErrors = true
			kotlinOptions.freeCompilerArgs += "-Xjvm-default=all"
		}

		withType<Jar> {
			archiveFileName.set("demo-${project.name}.jar")
		}

		withType<Test> {
			useJUnitPlatform()

			jvmArgs = listOf("-Xmx2048m")

			testLogging {
				events(TestLogEvent.PASSED, TestLogEvent.FAILED, TestLogEvent.SKIPPED)
				showStandardStreams = true
				exceptionFormat = TestExceptionFormat.FULL
			}
		}

		task("allDeps", DependencyReportTask::class) {}
	}
}
