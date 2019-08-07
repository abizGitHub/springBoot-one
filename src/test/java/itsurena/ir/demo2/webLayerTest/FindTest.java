package itsurena.ir.demo2.webLayerTest;

import itsurena.ir.demo2.controller.UserInfoDto;
import itsurena.ir.demo2.exception.ApiException;
import itsurena.ir.demo2.service.UserInfoService;
import itsurena.ir.demo2.utils.JsonUtils;
import itsurena.ir.demo2.utils.UserInfoDtoMockBuilder;
import jdk.nashorn.internal.runtime.JSONListAdapter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FindTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    UserInfoService userInfoService;

    static Long createdId;

    @Before
    public void createSomeUsers() throws ApiException {
        userInfoService.deleteAll();
        UserInfoDto userInfoDto = null;
        for (int i = 0; i < 10; i++) {
            userInfoDto = new UserInfoDtoMockBuilder().
                    newFilledInstance().
                    build();
            createdId = userInfoService.createUser(userInfoDto).getId();
        }
    }

    @Test
    public void findAll() {
        try {
            MvcResult result = mockMvc.perform(get("/api/userInfos")
                    .accept(MediaType.APPLICATION_JSON_UTF8))
                    .andExpect(status().isOk()).andReturn();

            String content = result.getResponse().getContentAsString();
            JSONArray jsonArray = new JSONArray(content);
            assertEquals(jsonArray.length(), 10);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void findById() throws ApiException {
        UserInfoDto loaded = userInfoService.findById(createdId);
        try {
            MvcResult result = mockMvc.perform(get("/api/userInfos/findById/" + createdId)
                    .accept(MediaType.APPLICATION_JSON_UTF8))
                    .andExpect(status().isOk()).andReturn();

            String content = result.getResponse().getContentAsString();
          //  assertEquals(content, JsonUtils.asJsonString(loaded));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void findByUserName() throws ApiException {
        UserInfoDto loaded = userInfoService.findById(createdId);
        try {
            MvcResult result = mockMvc.perform(get("/api/userInfos/findByUserName/" + loaded.getUserName())
                    .accept(MediaType.APPLICATION_JSON_UTF8))
                    .andExpect(status().isOk()).andReturn();

            String content = result.getResponse().getContentAsString();
            //assertEquals(content, JsonUtils.asJsonString(loaded));
        } catch (Exception e) {
            fail();
        }
    }

}
