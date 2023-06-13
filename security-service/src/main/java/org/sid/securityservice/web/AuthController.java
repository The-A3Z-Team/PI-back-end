package org.sid.securityservice.web;

import org.sid.securityservice.dtos.UserResponseDTO;
import org.sid.securityservice.exceptions.UserNotFoundException;
import org.sid.securityservice.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class AuthController {
    private JwtEncoder jwtEncoder;
    private JwtDecoder jwtDecoder;
    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private UserService userService;

    public AuthController(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder, AuthenticationManager authenticationManager, UserDetailsService userDetailsService, UserService userService) {
        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
    }

    public static class AuthRequest {
        private String grantType;
        private String username;
        private String password;
        private boolean withRefreshToken;
        private String refreshToken;

        public String getGrantType() {
            return grantType;
        }

        public void setGrantType(String grantType) {
            this.grantType = grantType;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public boolean isWithRefreshToken() {
            return withRefreshToken;
        }

        public void setWithRefreshToken(boolean withRefreshToken) {
            this.withRefreshToken = withRefreshToken;
        }

        public String getRefreshToken() {
            return refreshToken;
        }

        public void setRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
        }
    }

    public static class AuthResponse {
        private String accessToken;
        private UserResponseDTO userDTO;

        public AuthResponse(String accessToken, UserResponseDTO userDTO) {
            this.accessToken = accessToken;
            this.userDTO = userDTO;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public UserResponseDTO getUserDTO() {
            return userDTO;
        }

        public void setUserDTO(UserResponseDTO userDTO) {
            this.userDTO = userDTO;
        }
    }

    @PostMapping("/token")
    public ResponseEntity<AuthResponse> jwtToken(@RequestBody AuthRequest authRequest) throws UserNotFoundException {
        String grantType = authRequest.getGrantType();
        String username = authRequest.getUsername();
        String password = authRequest.getPassword();
        boolean withRefreshToken = authRequest.isWithRefreshToken();
        String refreshToken = authRequest.getRefreshToken();

        String subject = null;

        if (grantType.equals("password")) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            subject = authentication.getName();

        } else if (grantType.equals("refreshToken")) {
            if (refreshToken == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            Jwt decodeJWT = null;
            try {
                decodeJWT = jwtDecoder.decode(refreshToken);
            } catch (JwtException e) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            subject = decodeJWT.getSubject();
        }

        Instant instant = Instant.now();
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .subject(subject)
                .issuedAt(instant)
                .expiresAt(instant.plus(withRefreshToken ? 1 : 5, ChronoUnit.HOURS))
                .issuer("security-service")
                .build();
        String jwtAccessToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();

        UserResponseDTO userDTO = userService.getUserByUsername(username);

        AuthResponse authResponse = new AuthResponse(jwtAccessToken, userDTO);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }
}
