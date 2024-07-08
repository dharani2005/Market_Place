package org.example.market_place.controller;

import org.example.market_place.domain.dto.UserDTOForm;
import org.example.market_place.domain.dto.UserDTOView;
import org.example.market_place.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/users")
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    public ResponseEntity<UserDTOView> doRegister(@RequestBody UserDTOForm dtoForm){
        System.out.println("DTO Form: " + dtoForm);
        UserDTOView responseBody = userService.register(dtoForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @GetMapping("/email")
    public ResponseEntity<UserDTOView> doGetByEmail(@RequestParam  String email){
        System.out.println("Email: " + email);
        UserDTOView responseBody = userService.getByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
    @GetMapping("/products")
    public ResponseEntity<UserDTOView> doFindProductsByUserEmailAndPassword(@RequestParam String email,@RequestParam  String password){
        System.out.println("Email: " + email);
       UserDTOView responseBody = userService.findProductsByUserEmailAndPassword(email, password);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
