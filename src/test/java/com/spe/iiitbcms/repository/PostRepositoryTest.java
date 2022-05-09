package com.spe.iiitbcms.repository;

import com.spe.iiitbcms.BaseTest;
import com.spe.iiitbcms.model.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PostRepositoryTest extends BaseTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    public void shouldSavePost() {
        Post expectedPostObject = new Post(null, "First Post", "http://url.site", "Test",
                0, null, Instant.now(), null);
        Post actualPostObject = postRepository.save(expectedPostObject);
        assertThat(actualPostObject).usingRecursiveComparison()
                .ignoringFields("postId").isEqualTo(expectedPostObject);
    }

}
