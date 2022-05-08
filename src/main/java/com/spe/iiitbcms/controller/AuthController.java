package com.spe.iiitbcms.controller;

import com.spe.iiitbcms.dto.AuthenticationResponse;
import com.spe.iiitbcms.dto.LoginRequest;
import com.spe.iiitbcms.dto.RefreshTokenRequest;
import com.spe.iiitbcms.dto.RegisterRequest;
import com.spe.iiitbcms.service.AuthService;
import com.spe.iiitbcms.service.RefreshTokenService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private static final Logger logger = LogManager.getLogger(AuthController.class);
    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/signup")
    public void signup(@RequestBody RegisterRequest registerRequest) {
        try{
            authService.signup(registerRequest);
            logger.info("Successfully logged in");
//            return new ResponseEntity<>("User Registration Successful", OK);
        }catch (Exception e){
            logger.error("Error logging in");
        }
    }

    @GetMapping("accountVerification/{token}")
    public void verifyAccount(@PathVariable String token) {
        try{
            authService.verifyAccount(token);
            logger.info("Account activated successfully");
        } catch (Exception e){
            logger.error("Account could not be activated due to some error");
        }
//        return new ResponseEntity<>("Account Activated Successfully", OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        AuthenticationResponse aT = new AuthenticationResponse();
        try {
            aT = authService.login(loginRequest);
            logger.info("Logging in");
        } catch (Exception e){
            logger.error("Error logging in");
        }
        return aT;
    }

    @PostMapping("/refresh/token")
    public AuthenticationResponse refreshTokens(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        AuthenticationResponse aT = new AuthenticationResponse();
        try {
            aT = authService.refreshToken(refreshTokenRequest);
            logger.info("Token refreshed succesfully");
        } catch (Exception e) {
            logger.error("Could not refresh the token");
        }
        return aT;
    }

    @PostMapping("/logout")
    public void logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        try {
            refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
            logger.info("Refresh Token Deleted Successfully, logging out");
        } catch (Exception e){
            logger.error("Could not delete the refresh token, logout not permitted");
        }
//        return ResponseEntity.status(OK).body("Refresh Token Deleted Successfully!!");
    }
}
