import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm") version "1.6.21"
	id("org.springframework.boot") version "2.7.4"
	id("io.spring.dependency-management") version "1.0.14.RELEASE"
	id("com.google.protobuf") version "0.8.19" apply false
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

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
		set("grpcVersion", "1.46.0")
	}
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
			options.compilerArgs.add("-Xlint:all,-serial,-processing")
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
