package com.spe.iiitbcms.service;

import com.spe.iiitbcms.dto.PostRequest;
import com.spe.iiitbcms.dto.PostResponse;
import com.spe.iiitbcms.exceptions.PostNotFoundException;
import com.spe.iiitbcms.exceptions.SubpostNotFoundException;
import com.spe.iiitbcms.mapper.PostMapper;
import com.spe.iiitbcms.model.Post;
import com.spe.iiitbcms.model.Subpost;
import com.spe.iiitbcms.model.User;
import com.spe.iiitbcms.repository.PostRepository;
import com.spe.iiitbcms.repository.SubpostRepository;
import com.spe.iiitbcms.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final SubpostRepository subpostRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final PostMapper postMapper;

    public void save(PostRequest postRequest) {
        Subpost subpost = subpostRepository.findByName(postRequest.getSubpostName())
                .orElseThrow(() -> new SubpostNotFoundException(postRequest.getSubpostName()));
        postRepository.save(postMapper.map(postRequest, subpost, authService.getCurrentUser()));
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id.toString()));
        return postMapper.mapToDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsBySubpost(Long subpostId) {
        Subpost subpost = subpostRepository.findById(subpostId)
                .orElseThrow(() -> new SubpostNotFoundException(subpostId.toString()));
        List<Post> posts = postRepository.findAllBySubpost(subpost);
        return posts.stream().map(postMapper::mapToDto).collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return postRepository.findByUser(user)
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }
}
