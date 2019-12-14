package abiz.ir.demo2.webLayerTest;

import abiz.ir.demo2.controller.UserInfoDto;
import abiz.ir.demo2.exception.ApiException;
import abiz.ir.demo2.service.UserInfoService;
import abiz.ir.demo2.utils.JsonUtils;
import abiz.ir.demo2.utils.UserInfoDtoMockBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static abiz.ir.demo2.exception.ApiExceptionType.PASSWORD_INCORRECT_ERROR;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ModificationTest {

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    private MockMvc mockMvc;

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
        beforeModification.setFirstName("firstNameTest");
        beforeModification.setLastName("lastNameTest");
        try {
            mockMvc.perform(
                    patch("/api/userInfos/modifyFirstAndLastName")
                            .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(JsonUtils.asJsonString(beforeModification)))
                    .andExpect(status().isOk());

        } catch (Exception e) {
            e.getMessage();
            fail();
        }
        UserInfoDto afterModification = userInfoService.findById(createdId);
        assertEquals(afterModification.getFirstName(), "firstNameTest");
        assertEquals(afterModification.getLastName(), "lastNameTest");
    }

    @Test
    public void modifyPassword() throws ApiException {
        UserInfoDto beforeModification = userInfoService.findById(createdId);
        beforeModification.setNewPassword("NEW_PASS");
        try {
            mockMvc.perform(
                    patch("/api/userInfos/modifyPassword")
                            .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(JsonUtils.asJsonString(beforeModification)))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.getMessage();
            fail();
        }
        UserInfoDto afterModification = userInfoService.findById(createdId);
        assertEquals(afterModification.getPassword(), "NEW_PASS");
    }

    @Test
    public void modifyPasswordWrongException() throws ApiException {
        UserInfoDto beforeModification = userInfoService.findById(createdId);
        beforeModification.setPassword("WRONG_PASS");
        beforeModification.setNewPassword("NEW_PASS");
        try {
            mockMvc.perform(
                    patch("/api/userInfos/modifyPassword")
                            .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(JsonUtils.asJsonString(beforeModification)))
                    .andExpect(status().isUnauthorized())
                    .andExpect(content().string(PASSWORD_INCORRECT_ERROR.name()));
        } catch (Exception e) {
            e.getMessage();
            fail();
        }

    }


}