package com.spe.iiitbcms.controller;

import com.spe.iiitbcms.dto.VoteDto;
import com.spe.iiitbcms.service.VoteService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/votes/")
@AllArgsConstructor
public class VoteController {

    private static final Logger logger = LogManager.getLogger(VoteController.class);
    private final VoteService voteService;

    @PostMapping
    public ResponseEntity<Void> vote(@RequestBody VoteDto voteDto) {
        HttpStatus stat;
        try {
            voteService.vote(voteDto);
            stat = HttpStatus.OK;
            logger.info("Successfully voted");
        } catch (Exception e) {
            stat = HttpStatus.EXPECTATION_FAILED;
            logger.error("Voting failed");
        }
        return new ResponseEntity<>(stat);
    }
}