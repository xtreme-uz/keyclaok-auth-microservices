package uz.xtreme.userprovider;

import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.storage.UserStorageProviderFactory;

public class GrpcUserProviderFactory implements UserStorageProviderFactory<GrpcUserProvider> {

    public static final String PROVIDER_NAME = "grpc-user-provider";

    @Override
    public GrpcUserProvider create(KeycloakSession session, ComponentModel model) {
        GrpcUserClient grpcUserClient = new GrpcUserClient();
        return new GrpcUserProvider(session, model, grpcUserClient);
    }

    @Override
    public String getId() {
        return PROVIDER_NAME;
    }

    @Override
    public String getHelpText() {
        return "gRPC User Provider";
    }
}
