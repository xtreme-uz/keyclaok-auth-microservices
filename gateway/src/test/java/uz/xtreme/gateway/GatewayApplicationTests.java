package uz.xtreme.gateway;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class GatewayApplicationTests {

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void contextLoads() {
        assertThat(objectMapper).isNotNull();
    }

}
