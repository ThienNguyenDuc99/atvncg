package com.example.authentication.service;

import com.example.authentication.entity.User;
import com.example.authentication.repository.UserRepository;
import com.example.authentication.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private JwtUtil jwtService;

    private static final SecureRandom RANDOM = new SecureRandom();


    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private String generateSalt() {
        byte[] saltBytes = new byte[16]; // 128-bit salt
        RANDOM.nextBytes(saltBytes);
        return Base64.getEncoder().encodeToString(saltBytes);
    }

    private String hashPassword(String rawPassword, String salt) {
        // Gộp password + salt rồi hash, ví dụ bằng SHA-256 sfsdfsdfsd
        try {
            var digest = java.security.MessageDigest.getInstance("SHA-256");
            digest.update(salt.getBytes());
            byte[] hash = digest.digest(rawPassword.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            throw new RuntimeException("Failed to hash password", e);
        }
    }

    @Transactional
    public boolean register(User user) {
        // 🔹 Sinh salt ngẫu nhiên
        String salt = generateSalt();

        // 🔹 Hash password kèm salt
        String hashedPassword = hashPassword(user.getPassword(), salt);

        user.setSalt(salt);
        user.setPassword(hashedPassword);

        userRepo.save(user);

        // 🔹 Sinh token
        String token = jwtService.generateToken(user.getUsername());
        return token != null;
    }

    public Map<String, Object> login(User user) {
        // 🔹 Lấy user trong DB
        User found = userRepo.findByUsername(user.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 🔹 Hash lại mật khẩu nhập vào với salt của user trong DB
        String hashedInput = hashPassword(user.getPassword(), found.getSalt());

        if (!hashedInput.equals(found.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // 🔹 Sinh token
        String token = jwtService.generateToken(found.getUsername());
        return Map.of("token", token);
    }
}
