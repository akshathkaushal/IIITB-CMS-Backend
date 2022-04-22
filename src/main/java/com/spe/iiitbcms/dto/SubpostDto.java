package com.spe.iiitbcms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubpostDto {
    private Long id;
    private String name;
    private String description;
    private Integer numberOfPosts;
}
