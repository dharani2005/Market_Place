package org.example.market_place.controller;

import jakarta.validation.Valid;
import org.example.market_place.domain.dto.UserDTOForm;
import org.example.market_place.domain.dto.UserDTOView;
import org.example.market_place.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/users")
@RestController
@Validated
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTOView> doRegister(@RequestBody @Valid UserDTOForm dtoForm){
        System.out.println("DTO Form: " + dtoForm);
        UserDTOView responseBody = userService.register(dtoForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @GetMapping("/email")
    public ResponseEntity<UserDTOView> doGetByEmail(@RequestParam @Valid  String email){
        System.out.println("Email: " + email);
        UserDTOView responseBody = userService.getByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
    @GetMapping("/authenticate")
    public ResponseEntity<UserDTOView>authenticateUser(@RequestBody @Valid UserDTOForm dtoForm){
        System.out.println("Authenticate DTO Form: " + dtoForm);
        UserDTOView responseBody = userService.authenticateUser(dtoForm);
        return ResponseEntity.status(HttpStatus.FOUND).body(responseBody);
    }
    @GetMapping("/user")
    public ResponseEntity<UserDTOView> doFindUserByEmailAndPassword(@RequestParam @Valid String email,@RequestParam @Valid String password){
        System.out.println("Email: " + email);
       UserDTOView responseBody = userService.findUserByEmailAndPassword(email, password);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
