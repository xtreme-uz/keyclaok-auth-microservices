package uz.xtreme.users;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class UsersApplicationTests {

    @Autowired
    UsersController usersController;

    @Test
    void contextLoads() {
        assertThat(usersController).isNotNull();
    }

}
