package me.hung.auth.service;

import me.hung.auth.dto.AuthRequest;
import me.hung.auth.dto.CustomResponse;
import me.hung.auth.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthenticateService(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    public ResponseEntity<CustomResponse> authenticateUser(AuthRequest authRequest) {
        String jwt;
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
            jwt = jwtUtil.generateToken(authRequest.getUsername());
        } catch (AuthenticationException ex) {
            ex.printStackTrace();
            return ResponseEntity
                    .status(400)
                    .body(new CustomResponse(400, null, ex.getMessage()));
        }
        return ResponseEntity
                .status(200)
                .body(new CustomResponse(200, jwt, null));
    }

}
