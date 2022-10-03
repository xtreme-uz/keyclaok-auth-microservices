package uz.xtreme.userprovider;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Data
public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String userId;
}
