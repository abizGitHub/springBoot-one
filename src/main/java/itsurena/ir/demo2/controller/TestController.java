package itsurena.ir.demo2.controller;

import itsurena.ir.demo2.exception.ApiException;
import itsurena.ir.demo2.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/userInfos")
public class TestController {

    @Autowired
    UserInfoService userInfoService;

    @PutMapping
    public ResponseEntity createUser(@RequestBody UserInfoDto userInfoDto) throws ApiException {
        UserInfoDto createdUser = userInfoService.createUser(userInfoDto);
        return new ResponseEntity(createdUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteByUserName/{username}")
    public ResponseEntity deleteByUserName(@PathVariable String username) throws ApiException {
        userInfoService.deleteByUserName(username);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("delete done!");
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) throws ApiException {
        userInfoService.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("delete done!");
    }

    @PatchMapping("/modifyFirstAndLastName")
    public ResponseEntity modifyFirstAndLastName(@RequestBody UserInfoDto userInfoDto) throws ApiException {
        userInfoService.modifyFirstAndLastName(userInfoDto.getId(), userInfoDto.getFirstName(), userInfoDto.getLastName());
        return ResponseEntity.status(HttpStatus.OK)
                .body("update done!");
    }


    @PatchMapping("/modifyPassword")
    public ResponseEntity modifyPassword(@RequestBody UserInfoDto userInfoDto) throws ApiException {
        userInfoService.modifyPassword(userInfoDto.getId(), userInfoDto.getPassword(), userInfoDto.getNewPassword());
        return ResponseEntity.status(HttpStatus.OK)
                .body("update done!");
    }

    @GetMapping("/findByUserName/{username}")
    public ResponseEntity findByUserName(@PathVariable String username) throws ApiException {
        UserInfoDto userInfoDto = userInfoService.findByUserName(username);
        return ResponseEntity.status(HttpStatus.OK)
                .body(userInfoDto);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity findById(@PathVariable Long id) throws ApiException {
        UserInfoDto userInfoDto = userInfoService.findById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(userInfoDto);
    }

    @GetMapping
    public ResponseEntity findAll() throws ApiException {
        List<UserInfoDto> list = userInfoService.findAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(list);
    }

}
