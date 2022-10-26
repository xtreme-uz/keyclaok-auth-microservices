FROM quay.io/keycloak/keycloak:19.0.2
COPY  ./user-provider/build/libs/demo-user-provider.jar /opt/keycloak/providers/user-provider.jar
ENTRYPOINT ["/opt/keycloak/bin/kc.sh"]