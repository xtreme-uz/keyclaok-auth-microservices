FROM quay.io/keycloak/keycloak:19.0.2

# Install custom providers
COPY  build/libs/*.jar /opt/keycloak/providers/user-storage-provider.jar
RUN /opt/keycloak/bin/kc.sh build

WORKDIR /opt/keycloak

ENV KC_HOSTNAME=localhost

ENTRYPOINT ["/opt/keycloak/bin/kc.sh"]