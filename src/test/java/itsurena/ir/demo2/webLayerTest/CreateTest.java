package itsurena.ir.demo2.webLayerTest;

import itsurena.ir.demo2.utils.JsonUtils;
import itsurena.ir.demo2.utils.UserInfoDtoMockBuilder;
import itsurena.ir.demo2.controller.UserInfoDto;
import itsurena.ir.demo2.exception.ApiException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import static itsurena.ir.demo2.exception.ApiExceptionType.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CreateTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createUser() throws ApiException {
        UserInfoDto userInfoDto = new UserInfoDtoMockBuilder().
                newFilledInstance().
                build();
        assertPerformPut(userInfoDto, status().isCreated(), null);
    }

    @Test
    public void duplicate() {
        UserInfoDto userInfoDto = new UserInfoDtoMockBuilder().
                newFilledInstance().
                build();
        assertPerformPut(userInfoDto, status().isCreated(), null);
        assertPerformPut(userInfoDto, status().isConflict(), USER_NAME_ALREADY_EXIST.name());
    }


    @Test
    public void nullProperties() {
        UserInfoDto userInfoDto = new UserInfoDtoMockBuilder().
                newFilledInstance().
                nullUserName().
                build();

        assertPerformPut(userInfoDto, status().isNotModified(), USER_NAME_MUST_NOT_NULL.name());

        userInfoDto = new UserInfoDtoMockBuilder().
                newFilledInstance().
                nullPassword().
                build();

        assertPerformPut(userInfoDto, status().isNotModified(), PASSWORD_MUST_NOT_NULL.name());

        userInfoDto = new UserInfoDtoMockBuilder().
                newFilledInstance().
                nullFirstName().
                build();

        assertPerformPut(userInfoDto, status().isNotModified(), FIRST_NAME_MUST_NOT_NULL.name());

        userInfoDto = new UserInfoDtoMockBuilder().
                newFilledInstance().
                nullLastName().
                build();

        assertPerformPut(userInfoDto, status().isNotModified(), LAST_NAME_MUST_NOT_NULL.name());
    }

    @Test
    public void minLength() {
        UserInfoDto userInfoDto = new UserInfoDtoMockBuilder().
                newFilledInstance().
                exceedMinLengthUserName().
                build();

        assertPerformPut(userInfoDto, status().isNotModified(), USER_NAME_MIN_LENGTH.name());

        userInfoDto = new UserInfoDtoMockBuilder().
                newFilledInstance().
                exceedMinLengthPassword().
                build();

        assertPerformPut(userInfoDto, status().isNotModified(), PASSWORD_MIN_LENGTH.name());

        userInfoDto = new UserInfoDtoMockBuilder().
                newFilledInstance().
                exceedMinLengthFirstName().
                build();

        assertPerformPut(userInfoDto, status().isNotModified(), FIRST_NAME_MIN_LENGTH.name());

        userInfoDto = new UserInfoDtoMockBuilder().
                newFilledInstance().
                exceedMinLengthLastName().
                build();

        assertPerformPut(userInfoDto, status().isNotModified(), LAST_NAME_MIN_LENGTH.name());
    }

    @Test
    public void maxLength() {
        UserInfoDto userInfoDto = new UserInfoDtoMockBuilder().
                newFilledInstance().
                exceedMaxLengthUserName().
                build();

        assertPerformPut(userInfoDto, status().isNotModified(), USER_NAME_MAX_LENGTH.name());

        userInfoDto = new UserInfoDtoMockBuilder().
                newFilledInstance().
                exceedMaxLengthPassword().
                build();

        assertPerformPut(userInfoDto, status().isNotModified(), PASSWORD_MAX_LENGTH.name());

        userInfoDto = new UserInfoDtoMockBuilder().
                newFilledInstance().
                exceedMaxLengthFirstName().
                build();

        assertPerformPut(userInfoDto, status().isNotModified(), FIRST_NAME_MAX_LENGTH.name());

        userInfoDto = new UserInfoDtoMockBuilder().
                newFilledInstance().
                exceedMaxLengthLastName().
                build();

        assertPerformPut(userInfoDto, status().isNotModified(), LAST_NAME_MAX_LENGTH.name());
    }

    private void assertPerformPut(UserInfoDto userInfoDto, ResultMatcher expectedStatus, String expectedMessage) {
        try {
            MvcResult result = mockMvc.perform(put("/api/userInfos")
                    .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JsonUtils.asJsonString(userInfoDto)))
                    .andExpect(expectedStatus)
                    .andReturn();
            String content = result.getResponse().getContentAsString();
            if (expectedMessage != null)
                assertEquals(content, expectedMessage);
        } catch (Exception e) {
            e.getMessage();
            fail();
        }
    }

}
