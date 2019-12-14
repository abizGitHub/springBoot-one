package abiz.ir.demo2.serviceLayerTest;

import abiz.ir.demo2.utils.UserInfoDtoMockBuilder;
import abiz.ir.demo2.controller.UserInfoDto;
import abiz.ir.demo2.exception.ApiException;
import abiz.ir.demo2.exception.ApiExceptionType;
import abiz.ir.demo2.service.UserInfoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ModificationTest {

    @Autowired
    UserInfoService userInfoService;

    static Long createdId;

    @Before
    public void createTestUser() throws ApiException {
        userInfoService.deleteAll();
        UserInfoDto userInfoDto = new UserInfoDtoMockBuilder().
                newFilledInstance().
                build();
        createdId = userInfoService.createUser(userInfoDto).getId();
    }

    @Test
    public void modifyFirstAndLastName() throws ApiException {
        UserInfoDto beforeModification = userInfoService.findById(createdId);
        userInfoService.modifyFirstAndLastName(createdId, "firstNameTest", "lastNameTest");
        UserInfoDto afterModification = userInfoService.findById(createdId);
        assertNotEquals(beforeModification.getFirstName(), afterModification.getFirstName());
        assertNotEquals(beforeModification.getLastName(), afterModification.getLastName());
        assertEquals(afterModification.getFirstName(), "firstNameTest");
        assertEquals(afterModification.getLastName(), "lastNameTest");
    }

    @Test
    public void modifyPassword() throws ApiException {
        UserInfoDto beforeModification = userInfoService.findById(createdId);
        userInfoService.modifyPassword(createdId, beforeModification.getPassword(), "NEW_PASS");
        UserInfoDto afterModification = userInfoService.findById(createdId);
        assertNotEquals(beforeModification.getPassword(), afterModification.getPassword());
        assertEquals(afterModification.getPassword(), "NEW_PASS");
    }

    @Test
    public void modifyPasswordWrongException(){
        try {
            userInfoService.modifyPassword(createdId, "WRONG_PASS", "NEW_PASS");
        } catch (ApiException e) {
            assertEquals(e.getType(), ApiExceptionType.PASSWORD_INCORRECT_ERROR);
        }
    }


}