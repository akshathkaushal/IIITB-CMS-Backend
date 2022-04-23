package com.spe.iiitbcms.mapper;

import com.spe.iiitbcms.dto.PostRequest;
import com.spe.iiitbcms.dto.PostResponse;
import com.spe.iiitbcms.model.Post;
import com.spe.iiitbcms.model.Post.PostBuilder;
import com.spe.iiitbcms.model.Subpost;
import com.spe.iiitbcms.model.User;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-23T18:20:05+0530",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.14.1 (Ubuntu)"
)
@Component
public class PostMapperImpl extends PostMapper {

    @Override
    public Post map(PostRequest postRequest, Subpost subpost, User user) {
        if ( postRequest == null && subpost == null && user == null ) {
            return null;
        }

        PostBuilder post = Post.builder();

        if ( postRequest != null ) {
            post.description( postRequest.getDescription() );
            post.postId( postRequest.getPostId() );
            post.postName( postRequest.getPostName() );
            post.url( postRequest.getUrl() );
        }
        if ( subpost != null ) {
            post.subpost( subpost );
        }
        if ( user != null ) {
            post.user( user );
        }
        post.createdDate( java.time.Instant.now() );
        post.voteCount( 0 );

        return post.build();
    }

    @Override
    public PostResponse mapToDto(Post post) {
        if ( post == null ) {
            return null;
        }

        PostResponse postResponse = new PostResponse();

        postResponse.setSubpostName( postSubpostName( post ) );
        postResponse.setRollNo( postUserRollNo( post ) );
        postResponse.setId( post.getPostId() );
        postResponse.setPostName( post.getPostName() );
        postResponse.setUrl( post.getUrl() );
        postResponse.setDescription( post.getDescription() );
        postResponse.setVoteCount( post.getVoteCount() );

        postResponse.setDuration( getDuration(post) );
        postResponse.setDownVote( isPostDownVoted(post) );
        postResponse.setCommentCount( commentCount(post) );
        postResponse.setUpVote( isPostUpVoted(post) );

        return postResponse;
    }

    private String postSubpostName(Post post) {
        if ( post == null ) {
            return null;
        }
        Subpost subpost = post.getSubpost();
        if ( subpost == null ) {
            return null;
        }
        String name = subpost.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String postUserRollNo(Post post) {
        if ( post == null ) {
            return null;
        }
        User user = post.getUser();
        if ( user == null ) {
            return null;
        }
        String rollNo = user.getRollNo();
        if ( rollNo == null ) {
            return null;
        }
        return rollNo;
    }
}
