package itsurena.ir.demo2.controller;


import com.fasterxml.jackson.annotation.JsonView;
import itsurena.ir.demo2.exception.UserNotFoundException;
import itsurena.ir.demo2.model.entity.UserInfo;
import itsurena.ir.demo2.model.entity.UserInfoView;
import itsurena.ir.demo2.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    UserInfoService userInfoService;


    @PostMapping("/createUser")
    public UserInfo createUser(@JsonView(UserInfoView.CreateScope.class) @Validated @RequestBody UserInfo userInfo) {
        userInfoService.saveUser(userInfo);
        return userInfo;
    }

    @GetMapping("/deleteUserByUserName/{username}")
    public ResponseEntity deleteUserByUserName(@PathVariable String username) throws UserNotFoundException {
        userInfoService.deleteByUserName(username);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("delete done!");
    }

    @GetMapping("/deleteUserById/{id}")
    public ResponseEntity deleteUserById(@PathVariable Long id) throws UserNotFoundException {
        userInfoService.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("delete done!");
    }


    @PostMapping("/editFirstAndLastName")
    public ResponseEntity editFirstAndLastName(@JsonView(UserInfoView.UpdateScope.class) @RequestBody UserInfo userInfo) throws UserNotFoundException {
        userInfoService.editFirstAndLastName(userInfo);
        return ResponseEntity.status(HttpStatus.OK)
                .body("update done!");
    }


    @PostMapping("/editPassword")
    public ResponseEntity editPassword(@JsonView(UserInfoView.UpdateScope.class) @RequestBody UserInfo userInfo) throws UserNotFoundException {
        userInfoService.editPassword(userInfo);
        return ResponseEntity.status(HttpStatus.OK)
                .body("update done!");
    }

    @GetMapping("/getUserByUserName/{username}")
    public UserInfo getUserByUserName(@PathVariable String username) throws UserNotFoundException {
        return userInfoService.loadUserByUserName(username);
    }


    @GetMapping("/getUserById/{id}")
    public UserInfo getUserById(@PathVariable Long id) throws UserNotFoundException {
        return userInfoService.loadUserById(id);
    }

    @GetMapping("/loadAllUsers")
    public List<UserInfo> loadAllUsers() {
        return userInfoService.loadAllUsers();
    }

}
