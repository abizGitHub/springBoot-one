package itsurena.ir.demo2.serviceLayerTest;


import itsurena.ir.demo2.utils.UserInfoDtoMockBuilder;
import itsurena.ir.demo2.controller.UserInfoDto;
import itsurena.ir.demo2.exception.ApiException;
import itsurena.ir.demo2.exception.ApiExceptionType;
import itsurena.ir.demo2.service.UserInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CreateTest {

    @Autowired
    UserInfoService userInfoService;

    @Test
    public void createUser() throws ApiException {
        UserInfoDto userInfoDto = new UserInfoDtoMockBuilder().
                newFilledInstance().
                build();

        UserInfoDto loaded = userInfoService.createUser(userInfoDto);
        assertNotNull(loaded);

        assertNotNull(loaded.getUserName());
        assertNotNull(loaded.getPassword());
        assertNotNull(loaded.getFirstName());
        assertNotNull(loaded.getLastName());
        assertNotNull(loaded.getCreateDate());

        assertEquals(userInfoDto.getUserName(), loaded.getUserName());
        assertEquals(userInfoDto.getPassword(), loaded.getPassword());
        assertEquals(userInfoDto.getFirstName(), loaded.getFirstName());
        assertEquals(userInfoDto.getLastName(), loaded.getLastName());
        assertEquals(userInfoDto.getCreateDate(), loaded.getCreateDate());
        assertEquals(userInfoDto.getModifiedDate(), loaded.getModifiedDate());
    }

    @Test(expected = ApiException.class)
    public void duplicate() throws ApiException {
        UserInfoDto userInfo = new UserInfoDtoMockBuilder().
                newFilledInstance().
                build();
        userInfoService.createUser(userInfo);
        try {
            userInfoService.createUser(userInfo);
        } catch (ApiException e) {
            assertEquals(e.getType(), ApiExceptionType.USER_NAME_ALREADY_EXIST);
            throw e;
        }
    }

    @Test
    public void nullProperties() {
        UserInfoDto userInfo = new UserInfoDtoMockBuilder().
                newFilledInstance().
                nullUserName().
                build();
        try {
            userInfoService.createUser(userInfo);
        } catch (ApiException e) {
            assertEquals(e.getType(), ApiExceptionType.USER_NAME_MUST_NOT_NULL);
        }

        userInfo = new UserInfoDtoMockBuilder().
                newFilledInstance().
                nullPassword().
                build();
        try {
            userInfoService.createUser(userInfo);
        } catch (ApiException e) {
            assertEquals(e.getType(), ApiExceptionType.PASSWORD_MUST_NOT_NULL);
        }

        userInfo = new UserInfoDtoMockBuilder().
                newFilledInstance().
                nullFirstName().
                build();
        try {
            userInfoService.createUser(userInfo);
        } catch (ApiException e) {
            assertEquals(e.getType(), ApiExceptionType.FIRST_NAME_MUST_NOT_NULL);
        }

        userInfo = new UserInfoDtoMockBuilder().
                newFilledInstance().
                nullLastName().
                build();
        try {
            userInfoService.createUser(userInfo);
        } catch (ApiException e) {
            assertEquals(e.getType(), ApiExceptionType.LAST_NAME_MUST_NOT_NULL);
        }

    }

    @Test
    public void maxLength() {
        UserInfoDto userInfo = new UserInfoDtoMockBuilder().
                newFilledInstance().
                exceedMaxLengthUserName().
                build();
        try {
            userInfoService.createUser(userInfo);
        } catch (ApiException e) {
            assertEquals(e.getType(), ApiExceptionType.USER_NAME_MAX_LENGTH);
        }

        userInfo = new UserInfoDtoMockBuilder().
                newFilledInstance().
                exceedMaxLengthPassword().
                build();
        try {
            userInfoService.createUser(userInfo);
        } catch (ApiException e) {
            assertEquals(e.getType(), ApiExceptionType.PASSWORD_MAX_LENGTH);
        }

        userInfo = new UserInfoDtoMockBuilder().
                newFilledInstance().
                exceedMaxLengthFirstName().
                build();
        try {
            userInfoService.createUser(userInfo);
        } catch (ApiException e) {
            assertEquals(e.getType(), ApiExceptionType.FIRST_NAME_MAX_LENGTH);
        }

        userInfo = new UserInfoDtoMockBuilder().
                newFilledInstance().
                exceedMaxLengthLastName().
                build();
        try {
            userInfoService.createUser(userInfo);
        } catch (ApiException e) {
            assertEquals(e.getType(), ApiExceptionType.LAST_NAME_MAX_LENGTH);
        }

    }

    @Test
    public void minLength() {
        UserInfoDto userInfo = new UserInfoDtoMockBuilder().
                newFilledInstance().
                exceedMinLengthUserName().
                build();
        try {
            userInfoService.createUser(userInfo);
        } catch (ApiException e) {
            assertEquals(e.getType(), ApiExceptionType.USER_NAME_MIN_LENGTH);
        }

        userInfo = new UserInfoDtoMockBuilder().
                newFilledInstance().
                exceedMinLengthPassword().
                build();
        try {
            userInfoService.createUser(userInfo);
        } catch (ApiException e) {
            assertEquals(e.getType(), ApiExceptionType.PASSWORD_MIN_LENGTH);
        }

        userInfo = new UserInfoDtoMockBuilder().
                newFilledInstance().
                exceedMinLengthFirstName().
                build();
        try {
            userInfoService.createUser(userInfo);
        } catch (ApiException e) {
            assertEquals(e.getType(), ApiExceptionType.FIRST_NAME_MIN_LENGTH);
        }

        userInfo = new UserInfoDtoMockBuilder().
                newFilledInstance().
                exceedMinLengthLastName().
                build();
        try {
            userInfoService.createUser(userInfo);
        } catch (ApiException e) {
            assertEquals(e.getType(), ApiExceptionType.LAST_NAME_MIN_LENGTH);
        }
    }


}
