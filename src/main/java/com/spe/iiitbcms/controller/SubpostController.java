package com.spe.iiitbcms.controller;

import com.spe.iiitbcms.dto.SubpostDto;
import com.spe.iiitbcms.service.SubpostService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/subpost")
@AllArgsConstructor
@Slf4j
public class SubpostController {

    private static final Logger logger = LogManager.getLogger(SubpostController.class);
    private final SubpostService subpostService;

    @PostMapping
    public ResponseEntity<SubpostDto> createSubpost(@RequestBody SubpostDto subpostDto) {
        SubpostDto res = new SubpostDto();
        HttpStatus stat;
        try {
            res = subpostService.save(subpostDto);
            stat = HttpStatus.CREATED;
            logger.info("Successfully created subpost");
        } catch (Exception e) {
            stat = HttpStatus.EXPECTATION_FAILED;
            logger.error("Could not create subpost");
        }
        return ResponseEntity.status(stat).body(res);
    }

    @GetMapping
    public ResponseEntity<List<SubpostDto>> getAllSubposts() {
        HttpStatus stat;
        List<SubpostDto> subposts = new ArrayList<>();
        try {
            subposts = subpostService.getAll();
            stat = HttpStatus.OK;
            logger.info("Successfully fetched subposts");
        } catch (Exception e) {
            stat = HttpStatus.EXPECTATION_FAILED;
            logger.error("Error in fetching subposts");
        }
        return ResponseEntity.status(stat).body(subposts);
    }

    @GetMapping("{id}")
    public ResponseEntity<SubpostDto> getSubpost(@PathVariable Long id) {
        HttpStatus stat;
        SubpostDto subpost = new SubpostDto();
        try {
            subpost = subpostService.getSubpost(id);
            stat = HttpStatus.OK;
            logger.info("Successfully fetched subpost with id " + id);
        } catch (Exception e) {
            stat = HttpStatus.EXPECTATION_FAILED;
            logger.error("Error in fetching subpost with id " + id);
        }
        return ResponseEntity.status(stat).body(subpost);
    }
}
