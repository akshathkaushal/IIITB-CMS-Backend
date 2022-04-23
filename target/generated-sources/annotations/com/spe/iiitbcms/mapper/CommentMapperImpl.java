package com.spe.iiitbcms.mapper;

import com.spe.iiitbcms.dto.CommentsDto;
import com.spe.iiitbcms.model.Comment;
import com.spe.iiitbcms.model.Post;
import com.spe.iiitbcms.model.User;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-23T18:20:05+0530",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.14.1 (Ubuntu)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public Comment map(CommentsDto commentsDto, Post post, User user) {
        if ( commentsDto == null && post == null && user == null ) {
            return null;
        }

        Comment comment = new Comment();

        if ( commentsDto != null ) {
            comment.setText( commentsDto.getText() );
        }
        if ( post != null ) {
            comment.setPost( post );
        }
        if ( user != null ) {
            comment.setUser( user );
        }
        comment.setCreatedDate( java.time.Instant.now() );

        return comment;
    }

    @Override
    public CommentsDto mapToDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentsDto commentsDto = new CommentsDto();

        commentsDto.setId( comment.getId() );
        commentsDto.setCreatedDate( comment.getCreatedDate() );
        commentsDto.setText( comment.getText() );

        commentsDto.setRollNo( comment.getUser().getRollNo() );
        commentsDto.setPostId( comment.getPost().getPostId() );

        return commentsDto;
    }
}
