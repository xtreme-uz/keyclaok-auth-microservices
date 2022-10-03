package uz.xtreme.users;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDto {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;

    public static UserDto of(String firstName, String lastName) {
        var dto = new UserDto();
        dto.setId(UUID.randomUUID().toString());
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        dto.setEmail(String.format("%s.%s@example.com", firstName.toLowerCase(), lastName.toLowerCase()));
        dto.setUserName(firstName.toLowerCase().replaceAll("\\s+", "_"));
        return dto;
    }
}
