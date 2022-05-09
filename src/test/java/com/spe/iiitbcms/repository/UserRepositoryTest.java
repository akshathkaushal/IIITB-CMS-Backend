package com.spe.iiitbcms.repository;

import com.spe.iiitbcms.BaseTest;
import com.spe.iiitbcms.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest extends BaseTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldSaveUser() {
        User expectedUserObject = new User(null, "rollnumber", "testuser", "secretpassword", "user@email.com", Instant.now(), true);
        User actualUserObject = userRepository.save(expectedUserObject);
        assertThat(actualUserObject).usingRecursiveComparison()
                .ignoringFields("userId").isEqualTo(expectedUserObject);
    }

}
