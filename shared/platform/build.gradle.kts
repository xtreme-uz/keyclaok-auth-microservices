plugins {
    `java-platform`
}

repositories {
    mavenCentral()
}

dependencies {
    constraints {
        api("org.springframework.cloud:spring-cloud-dependencies:2021.0.4")
        api("org.springframework.boot:spring-boot-starter-parent:2.7.5")

        api("io.grpc:grpc-netty-shaded:1.50.0")
        api("io.grpc:grpc-protobuf:1.50.0")
        api("io.grpc:grpc-stub:1.50.0")
        api("io.grpc:grpc-api:1.50.0")
        api("io.grpc:grpc-stub:1.50.0")
        api("io.grpc:grpc-testing:1.50.0")

        api("org.keycloak:keycloak-core:19.0.3")
        api("org.keycloak:keycloak-server-spi:19.0.3")
        api("org.keycloak:keycloak-server-spi-private:19.0.3")
        api("org.keycloak:keycloak-services:19.0.3")
        api("org.keycloak:keycloak-model-legacy:19.0.3")
    }
}