package com.example.BankDetails.Controller;

import com.example.BankDetails.Model.user;
import com.example.BankDetails.Repository.UserRepo;
import com.example.BankDetails.Service.UserService;
import com.example.BankDetails.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepo repo;
    private final UserService ser;
    private final JwtUtils jwtUtil;
    private final org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Map<String, String> body) {

        String email = body.get("email");
        String password = passwordEncoder.encode(body.get("password"));

        if (repo.findByEmail(email).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Email already exists");
        }

        user newUser = new user();
        newUser.setEmail(email);
        newUser.setPassword(password);

        ser.createUser(newUser);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Successfully Registered");
    }

    @PostMapping("/loginUser")
    public ResponseEntity<String> loginUser(@RequestBody Map<String, String> body) {

        String email = body.get("email");
        String password = body.get("password");
        user user = repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not registered"));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid password");
        }
        String token = jwtUtil.generateToken(email);
        return ResponseEntity.ok(token);
    }
}