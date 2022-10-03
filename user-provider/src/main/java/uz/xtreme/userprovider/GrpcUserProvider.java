package uz.xtreme.userprovider;

import lombok.RequiredArgsConstructor;
import org.keycloak.component.ComponentModel;
import org.keycloak.credential.CredentialInput;
import org.keycloak.credential.CredentialInputValidator;
import org.keycloak.credential.LegacyUserCredentialManager;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.SubjectCredentialManager;
import org.keycloak.models.UserModel;
import org.keycloak.models.credential.PasswordCredentialModel;
import org.keycloak.storage.StorageId;
import org.keycloak.storage.UserStorageProvider;
import org.keycloak.storage.adapter.AbstractUserAdapter;
import org.keycloak.storage.user.UserLookupProvider;

@RequiredArgsConstructor
public class GrpcUserProvider implements UserStorageProvider, UserLookupProvider, CredentialInputValidator {

    private final KeycloakSession session;
    private final ComponentModel model;
    private final GrpcUserClient usersService;

    @Override
    public void close() {
        //no-op
    }

    @Override
    public UserModel getUserById(String id, RealmModel realm) {
        StorageId storageId = new StorageId(id);
        String username = storageId.getExternalId();
        return getUserByUsername(username, realm);
    }

    @Override
    public UserModel getUserByUsername(String username, RealmModel realm) {
        UserModel returnValue = null;
        User user = usersService.getUserDetails(username);
        if (user != null) {
            returnValue = createUserModel(username, realm);
        }
        return returnValue;
    }

    @Override
    public UserModel getUserByEmail(String email, RealmModel realm) {
        return null;
    }

    @Override
    public boolean supportsCredentialType(String credentialType) {
        return PasswordCredentialModel.TYPE.equals(credentialType);
    }

    @Override
    public boolean isConfiguredFor(RealmModel realm, UserModel user, String credentialType) {
        if (!supportsCredentialType(credentialType)) {
            return false;
        }

        return user.credentialManager()
                .getStoredCredentialsByTypeStream(credentialType)
                .findAny().isPresent();
    }

    @Override
    public boolean isValid(RealmModel realm, UserModel user, CredentialInput credentialInput) {
        VerifyPasswordResponse verifyPasswordResponse = usersService.verifyUserPassword(user.getUsername(), credentialInput.getChallengeResponse());

        if (verifyPasswordResponse == null)
            return false;

        return verifyPasswordResponse.isVerified();
    }

    private UserModel createUserModel(String username, RealmModel realm) {
        return new AbstractUserAdapter.Streams(session, realm, model) {
            @Override
            public String getUsername() {
                return username;
            }

            @Override
            public SubjectCredentialManager credentialManager() {
                return new LegacyUserCredentialManager(session, realm, this);
            }
        };
    }

}
