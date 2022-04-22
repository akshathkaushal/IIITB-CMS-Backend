package com.spe.iiitbcms.service;

import com.spe.iiitbcms.dto.SubpostDto;
import com.spe.iiitbcms.exceptions.CMSException;
import com.spe.iiitbcms.mapper.SubpostMapper;
import com.spe.iiitbcms.model.Subpost;
import com.spe.iiitbcms.repository.SubpostRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class SubpostService {

    private final SubpostRepository subpostRepository;
    private final SubpostMapper subpostMapper;

    @Transactional
    public SubpostDto save(SubpostDto subpostDto) {
        Subpost save = subpostRepository.save(subpostMapper.mapDtoToSubpost(subpostDto));
        subpostDto.setId(save.getId());
        return subpostDto;
    }

    @Transactional(readOnly = true)
    public List<SubpostDto> getAll() {
        return subpostRepository.findAll()
                .stream()
                .map(subpostMapper::mapSubpostToDto)
                .collect(toList());
    }

    public SubpostDto getSubpost(Long id) {
        Subpost subpost = subpostRepository.findById(id)
                .orElseThrow(() -> new CMSException("No subpost found with ID - " + id));
        return subpostMapper.mapSubpostToDto(subpost);
    }
}
