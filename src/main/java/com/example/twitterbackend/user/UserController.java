package com.example.twitterbackend.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // zwraca wszystkich uzytkownikow
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return userService.getAllUsers();
    }

    // zwraca pojedynczego uzytkownika po id
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    // usuwa uzytkownika po id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Integer id) {
        return userService.deleteUserById(id);
    }

   /*
   @GetMapping("/test")
    public ResponseEntity<User> testowy() {
        User user = userService.testowy();
        return ResponseEntity.ok(user);
    }
    */

}
