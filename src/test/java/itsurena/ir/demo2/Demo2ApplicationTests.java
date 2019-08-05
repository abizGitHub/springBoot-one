package itsurena.ir.demo2;

import com.fasterxml.jackson.annotation.JsonView;
import itsurena.ir.demo2.exception.UserNotFoundException;
import itsurena.ir.demo2.model.entity.UserInfo;
import itsurena.ir.demo2.model.entity.UserInfoView;
import itsurena.ir.demo2.service.UserInfoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.BDDAssertions.then;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo2ApplicationTests {

    @Autowired
    UserInfoService userInfoService;

    @Test
    public void contextLoads() {
        System.out.println("------------------ contextLoads -----------------");
    }

    //@Before
    public void createSomeUsers() {
        for (int i = 0; i < 2; i++) {
            createUser();
        }
    }

    @Test
    public void createUser() {
        UserInfo userInfo = new UserInfo();
        userInfo.setFirstName("FNAME-" + UUID.randomUUID().toString().substring(0, 6));
        userInfo.setLastName("LNAME-" + UUID.randomUUID().toString().substring(0, 6));
        userInfo.setUserName("USERNAME-" + UUID.randomUUID().toString().substring(0, 6));
        userInfo.setPassword("PASS-" + UUID.randomUUID().toString().substring(0, 6));
      userInfoService.saveUser(userInfo);
    }

    @Test
    public void deleteUserByUserName() throws UserNotFoundException {
        String userName = userInfoService.loadAllUsers().get(0).getUserName();
        userInfoService.deleteByUserName(userName);
    }

    @Test
    public void deleteUserById() throws UserNotFoundException {
        Long id = userInfoService.loadAllUsers().get(0).getId();
        userInfoService.deleteById(id);
    }

    @Test
    public void editFirstAndLastName() throws UserNotFoundException {
        UserInfo userInfo = userInfoService.loadAllUsers().get(0);
        userInfo.setFirstName("FirstName-EDITED");
        userInfo.setLastName("LastName-EDITED");
        userInfo.setPassword("MUST NOT EDIT");
        userInfo.setUserName("MUST NOT EDIT");
        userInfoService.editFirstAndLastName(userInfo);
    }

    @Test
    public void editPassword() throws UserNotFoundException {
        UserInfo userInfo = userInfoService.loadAllUsers().get(1);
        userInfo.setFirstName("MUST NOT EDIT");
        userInfo.setLastName("MUST NOT EDIT");
        userInfo.setPassword("PASSWORD-EDITED");
        userInfo.setUserName("MUST NOT EDIT");
        userInfoService.editPassword(userInfo);
    }

    @Test
    public void getUserByUserName() throws UserNotFoundException {
        String userName = userInfoService.loadAllUsers().get(0).getUserName();
        UserInfo userInfo = userInfoService.loadUserByUserName(userName);
        System.out.println("---------getUserByUserName-----------");
        System.out.println(userInfo);
    }

    @Test
    public void getUserById() throws UserNotFoundException {
        Long id = userInfoService.loadAllUsers().get(0).getId();
        UserInfo userInfo = userInfoService.loadUserById(id);
        System.out.println("-----------getUserById-------------");
        System.out.println(userInfo);
    }

    @Test
    public void loadAllUsers() {
        List<UserInfo> userInfos = userInfoService.loadAllUsers();
        System.out.println("---------------loadAllUsers--------------");
        for (UserInfo userInfo : userInfos) {
            System.out.println(userInfo);
        }
    }


}
