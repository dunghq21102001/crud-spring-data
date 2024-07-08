package com.BEFresherTP.controller;

import com.BEFresherTP.DTO.UserDTO;
import com.BEFresherTP.service.ProductService;
import com.BEFresherTP.service.UserService;
import com.BEFresherTP.service.serviceImp.ProductServiceImp;
import com.BEFresherTP.service.serviceImp.UserServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServiceImp userService;
    @Autowired
    private ProductServiceImp productService;



    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users =  userService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(users);

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int id) {
        UserDTO userDTO = userService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);

    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO user) {
        UserDTO userSaved = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(userSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable int id, @RequestBody UserDTO user) {
        UserDTO userUpdated = userService.updateUser(user, id);
        return ResponseEntity.status(HttpStatus.OK).body(userUpdated);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted User");
    }


}
