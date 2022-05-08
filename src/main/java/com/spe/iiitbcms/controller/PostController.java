package com.spe.iiitbcms.controller;

import com.spe.iiitbcms.dto.PostRequest;
import com.spe.iiitbcms.dto.PostResponse;
import com.spe.iiitbcms.model.Post;
import com.spe.iiitbcms.service.PostService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/posts/")
@AllArgsConstructor
public class PostController {

    private static final Logger logger = LogManager.getLogger(PostController.class);
    private final PostService postService;

    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody PostRequest postRequest) {
        HttpStatus stat;
        try {
            postService.save(postRequest);
            stat = HttpStatus.CREATED;
            logger.info("Successfully created post");
        } catch (Exception e){
            logger.error("Error in creating post");
            stat = HttpStatus.EXPECTATION_FAILED;
        }
        return new ResponseEntity<>(stat);
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        HttpStatus stat;
        List<PostResponse> psts = new ArrayList<>();
        try {
            stat = HttpStatus.OK;
            psts = postService.getAllPosts();
            logger.info("Successfully fetched posts");
        } catch (Exception e){
            stat = HttpStatus.EXPECTATION_FAILED;
            logger.error("Could not fetch post(s)");
        }
        return status(stat).body(psts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long id) {
        HttpStatus stat;
        PostResponse pst = new PostResponse();
        try {
            pst = postService.getPost(id);
            stat = HttpStatus.OK;
            logger.info("Successfully fetched post with id " + id);
        } catch (Exception e) {
            stat = HttpStatus.EXPECTATION_FAILED;
            logger.error("Could not fetch post with id " + id);
        }

        return status(stat).body(pst);
    }

    @GetMapping("by-subpost/{id}")
    public ResponseEntity<List<PostResponse>> getPostsBySubpost(Long id) {
        HttpStatus stat;
        List<PostResponse> pst = new ArrayList<>();
        try {
            pst = postService.getPostsBySubpost(id);
            stat = HttpStatus.OK;
            logger.info("Successfully fetched posts for subpost with id " + id);
        } catch (Exception e) {
            stat = HttpStatus.EXPECTATION_FAILED;
            logger.error("Could not fetch posts for subpost with id " + id);
        }
        return status(stat).body(pst);
    }

    @GetMapping("by-user/{rollNo}")
    public ResponseEntity<List<PostResponse>> getPostsByRollNo(@PathVariable String rollNo) {
        HttpStatus stat;
        List<PostResponse> psts = new ArrayList<>();
        try {
            psts = postService.getPostsByRollNo(rollNo);
            stat = HttpStatus.OK;
            logger.info("Successfully fetched posts for roll number " + rollNo);
        } catch (Exception e) {
            stat = HttpStatus.EXPECTATION_FAILED;
            logger.error("Could not fetch posts for roll number " + rollNo);
        }
        return status(stat).body(psts);
    }
}
