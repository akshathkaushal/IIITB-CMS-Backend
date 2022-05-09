package com.spe.iiitbcms.controller;

import com.spe.iiitbcms.dto.PostResponse;
import com.spe.iiitbcms.security.JwtProvider;
import com.spe.iiitbcms.service.PostService;
import com.spe.iiitbcms.service.UserDetailsServiceImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static java.util.Arrays.asList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@WebMvcTest(controllers = PostController.class)
class PostControllerTest {

    @MockBean
    private PostService postService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Should List All Posts When making GET request to endpoint - /api/posts/")
    void shouldCreatePost() throws Exception {
        PostResponse postRequest1 = new PostResponse(1L, "Post Name 1", "http://url.site1", "Description1", "rollNo1",
                "Subpost Name 1", 0, 0, "1 day ago", false, false);
        PostResponse postRequest2 = new PostResponse(2L, "Post Name 2", "http://url2.site2", "Description2", "rollNo2",
                "Subpost Name 2", 0, 0, "2 days ago", false, false);

        Mockito.when(postService.getAllPosts()).thenReturn(asList(postRequest1, postRequest2));

        mockMvc.perform(get("/api/posts/"))
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.size()", Matchers.is(2)))
                .andExpect(jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$[0].postName", Matchers.is("Post Name 1")))
                .andExpect(jsonPath("$[0].url", Matchers.is("http://url.site1")))
                .andExpect(jsonPath("$[1].id", Matchers.is(2)))
                .andExpect(jsonPath("$[1].postName", Matchers.is("Post Name 2")))
                .andExpect(jsonPath("$[1].url", Matchers.is("http://url2.site2")));
    }
}