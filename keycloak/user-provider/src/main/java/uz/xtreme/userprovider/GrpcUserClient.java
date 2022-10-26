package uz.xtreme.userprovider;

import java.util.Map;


public class GrpcUserClient {

    Map<String, User> users = Map.of(
            "998998023940", new User("John", "Doe", "john.doe@example.com", "998998023940", "usr00001"),
            "998998023941", new User("Alex", "Doe", "alex.doe@example.com", "998998023941", "usr00002")
    );

    public User getUserDetails(String username) {
        return users.get(username);
    }

    public VerifyPasswordResponse verifyUserPassword(String username, String password) {
        return VerifyPasswordResponse.of(true);
    }
}
