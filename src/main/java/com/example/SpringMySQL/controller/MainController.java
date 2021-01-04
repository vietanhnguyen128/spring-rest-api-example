package com.example.SpringMySQL.controller;

import com.example.SpringMySQL.model.User;
import com.example.SpringMySQL.service.MainServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/demo")
public class MainController {

    @Autowired
    private MainServiceImpl mainService;

    @Operation(summary = "Add new user", description = "Add new user to the database, require token validation before proceed.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User added to database",
                    content = { @Content(schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "403", description = "Token not authorised", content = @Content)})
    @PostMapping("/add")
    public ResponseEntity<User> addNewUser (@RequestBody @Valid User user, @RequestParam(name = "token", required = false) String token) {
        mainService.createUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Operation(summary = "Get all users", description = "Get all users, require token validation before proceed.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = { @Content(array = @ArraySchema(schema = @Schema(implementation = User.class))) }),
            @ApiResponse(responseCode = "403", description = "Token not authorised", content = @Content)})
    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(name = "token", required = false) String token) {
        List<User> userData = mainService.findAll();

        return new ResponseEntity<>(userData, HttpStatus.OK);
    }

    @Operation(summary = "Get user by their id", description = "Get user by their id, require token validation before proceed.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the user", content = { @Content(schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "403", description = "Token not authorised", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)})
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Integer id, @RequestParam(name = "token", required = false) String token) {
        Optional<User> userData = mainService.findById(id);

        if (userData.isPresent()) {
            return new ResponseEntity<>(userData.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Update user", description = "Update user by id, require token validation before proceed.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = { @Content(schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "403", description = "Token not authorised", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)})
    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Integer id, @RequestBody @Valid User user, @RequestParam(name = "token", required = false) String token) {
        Optional<User> userData = mainService.findById(id);

        if (userData.isPresent()) {
            User userInfo = userData.get();

            userInfo.setName(user.getName());
            userInfo.setEmail(user.getEmail());
            userInfo.setAge(user.getAge());
            userInfo.setGender(user.getGender());
            userInfo.setDescription(user.getDescription());

            mainService.updateUser(userInfo);

            return new ResponseEntity<>(userInfo, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Delete user", description = "Delete user by id, require token validation before proceed.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content),
            @ApiResponse(responseCode = "403", description = "Token not authorised", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)})
    @DeleteMapping("/user/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Integer id, @RequestParam(name = "token", required = false) String token) {
        if (((Optional<User>) mainService.findById(id)).isPresent()) {
            mainService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
