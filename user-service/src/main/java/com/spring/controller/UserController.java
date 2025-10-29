package com.spring.controller;

import com.spring.entity.User;
import com.spring.service.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController
{
    private final UserServiceImpl service;

    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody @Valid User user)
    {
        User save=service.create(user);
        return  new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @GetMapping("/findall")
    public ResponseEntity<List<User>> getAll()
    {
        List<User> list=service.getAllUsers();
       return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<User> getUserById(@PathVariable  Integer id) throws Exception
    {
     User get=service.getUserById(id);
return  new ResponseEntity<>(get,HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<User> update(@PathVariable Integer id) throws Exception {
        User update=service.updateUserById(id);
        return  new ResponseEntity<>(update,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Integer id) throws Exception {
       String del=service.deleteUserById(id);
       return  new ResponseEntity<>(del,HttpStatus.OK);
    }

}
