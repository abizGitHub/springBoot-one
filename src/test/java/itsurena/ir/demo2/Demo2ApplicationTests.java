package itsurena.ir.demo2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import itsurena.ir.demo2.controller.UserInfoDto;
import itsurena.ir.demo2.utils.JsonUtils;
import itsurena.ir.demo2.utils.UserInfoDtoMockBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class Demo2ApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createCommonValueByCommonType() {
        UserInfoDto userInfoDto = new UserInfoDtoMockBuilder().
                newFilledInstance().
                build();
        try {
            mockMvc.perform(post("/api/")
                    .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JsonUtils.asJsonString(userInfoDto)))
                    .andExpect(status().isOk());
        } catch (Exception e) {
              e.getMessage();
        }

    }



}
