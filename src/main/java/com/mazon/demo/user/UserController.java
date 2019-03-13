package com.mazon.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId) throws EntityNotFoundException {
        User user =
                userRepository
                        .findById(userId)
                        .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado: " + userId));
        // new ResourceNotFoundException("Usuário não encontrado: " + userId));
        return ResponseEntity.ok().body(user);

    }

    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User user) {
        user.setDhCriacao(LocalDateTime.now());
        return userRepository.save(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable(value = "id") Long userId, @Valid @RequestBody User userDetails)
            throws EntityNotFoundException {

        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado para atualizar: " + userId));

        user.setNome(userDetails.getNome());
        user.setEmail(userDetails.getEmail());
        user.setNomeUsuarioAtualizacao(userDetails.getNomeUsuarioAtualizacao());
        user.setDhAtualizacao(LocalDateTime.now());
        final User updateUser = userRepository.save(user);
        return ResponseEntity.ok(updateUser);
    }

    @DeleteMapping("users/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws RuntimeException {
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado para excluir: " + userId));
        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @RequestMapping("users/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable(value = "email") String email) throws EntityNotFoundException {
        User user = userRepository
                .findByEmailLike(email)
                .orElseThrow(() -> new RuntimeException("E-mail não encontrado"));
        return ResponseEntity.ok().body(user);
    }

    @RequestMapping("users/busca/{busca}")
    public List<User> getUsersByTermoBusca(@PathVariable("busca") String termoBusca) throws EntityNotFoundException {
        return userRepository.findByTermoBusca(termoBusca);
    }

    @RequestMapping("users/nome/{nome}")
    public List<Object[]> getUsersByNome(@PathVariable(value = "nome") String nome) throws EntityNotFoundException {
        return userRepository.findByAsArray(nome);

    }
}