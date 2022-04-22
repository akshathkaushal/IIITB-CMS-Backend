package com.spe.iiitbcms.mapper;

import com.spe.iiitbcms.dto.SubpostDto;
import com.spe.iiitbcms.dto.SubpostDto.SubpostDtoBuilder;
import com.spe.iiitbcms.model.Subpost;
import com.spe.iiitbcms.model.Subpost.SubpostBuilder;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-22T17:47:03+0530",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.14.1 (Ubuntu)"
)
@Component
public class SubpostMapperImpl implements SubpostMapper {

    @Override
    public SubpostDto mapSubpostToDto(Subpost subpost) {
        if ( subpost == null ) {
            return null;
        }

        SubpostDtoBuilder subpostDto = SubpostDto.builder();

        subpostDto.id( subpost.getId() );
        subpostDto.name( subpost.getName() );
        subpostDto.description( subpost.getDescription() );

        subpostDto.numberOfPosts( mapPosts(subpost.getPosts()) );

        return subpostDto.build();
    }

    @Override
    public Subpost mapDtoToSubpost(SubpostDto subpostDto) {
        if ( subpostDto == null ) {
            return null;
        }

        SubpostBuilder subpost = Subpost.builder();

        subpost.id( subpostDto.getId() );
        subpost.name( subpostDto.getName() );
        subpost.description( subpostDto.getDescription() );

        return subpost.build();
    }
}
