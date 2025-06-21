package checklisthero.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import checklisthero.example.demo.dto.LoginDTO;
import checklisthero.example.demo.dto.UserDTO;
import checklisthero.example.demo.entity.User;
import checklisthero.example.demo.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO dto) {
        User user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();

        User saved = userService.registerUser(user);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO dto) {
        return userService.findByEmail(dto.getEmail())
                .map(user -> {
                    // simulação de login, sem token
                    boolean matches = dto.getPassword().equals(user.getPassword());
                    if (matches) {
                        return ResponseEntity.ok("Login bem-sucedido!");
                    }
                    return ResponseEntity.status(401).body("Senha inválida.");
                })
                .orElse(ResponseEntity.status(404).body("Usuário não encontrado."));
    }
}
