package com.gftfabiancastillo.demoFabianCastillo.controller;

import com.gftfabiancastillo.demoFabianCastillo.security.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String usuario, @RequestParam String clave) {
        System.out.println(usuario + " + " + clave);
        if ("admin".equals(usuario) && "123".equals(clave)) {
            String token = jwtUtil.generarToken(usuario);
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
    }
}

