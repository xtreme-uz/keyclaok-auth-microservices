package uz.xtreme.users;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    private static final List<UserDto> users = List.of(
            UserDto.of("John", "Doe"),
            UserDto.of("Alex", "Doe"),
            UserDto.of("Martin", "Joe")
    );

    @GetMapping
    public List<UserDto> getAllUsers() {
        return users;
    }

    @GetMapping("/{username}")
    public UserDto getByUsername(@PathVariable String username) {
        return users.stream()
                .filter(dto -> dto.getUserName().equals(username))
                .findAny()
                .orElseThrow(() -> new RuntimeException("user not found by username: " + username));
    }

}
