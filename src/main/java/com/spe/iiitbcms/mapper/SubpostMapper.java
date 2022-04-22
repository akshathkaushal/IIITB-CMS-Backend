package com.spe.iiitbcms.mapper;

import com.spe.iiitbcms.dto.SubpostDto;
import com.spe.iiitbcms.model.Post;
import com.spe.iiitbcms.model.Subpost;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubpostMapper {

    @Mapping(target = "numberOfPosts", expression = "java(mapPosts(subpost.getPosts()))")
    SubpostDto mapSubpostToDto(Subpost subpost);

    default Integer mapPosts(List<Post> numberOfPosts) {
        return numberOfPosts.size();
    }

    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    Subpost mapDtoToSubpost(SubpostDto subpostDto);
}
