package me.hung.auth.controller;

import me.hung.auth.dto.CustomResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hello")
public class HelloController {

    @GetMapping("/user")
    public ResponseEntity<CustomResponse> helloUser() {
        return ResponseEntity
                .ok(new CustomResponse(200, "Hello, User!", null));
    }

    @GetMapping("/admin")
    public ResponseEntity<CustomResponse> helloAdmin() {
        return ResponseEntity
                .ok(new CustomResponse(200, "Hello, Admin!", null));
    }

}
