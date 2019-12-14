package abiz.ir.demo2.serviceLayerTest;

import abiz.ir.demo2.utils.UserInfoDtoMockBuilder;
import abiz.ir.demo2.controller.UserInfoDto;
import abiz.ir.demo2.exception.ApiException;
import abiz.ir.demo2.service.UserInfoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FindTest {

    @Autowired
    UserInfoService userInfoService;

    @Before
    public void createSomeUsers() throws ApiException {
        userInfoService.deleteAll();
        for (int i = 0; i < 10; i++) {
            UserInfoDto userInfoDto = new UserInfoDtoMockBuilder().
                    newFilledInstance().
                    build();
            userInfoService.createUser(userInfoDto);
        }
    }

    @Test
    public void findAll() throws ApiException {
        List<UserInfoDto> all = userInfoService.findAll();
        assertEquals(all.size(), 10);
    }

    @Test
    public void findById() throws ApiException {
        UserInfoDto userInfoDto = new UserInfoDtoMockBuilder().
                newFilledInstance().
                build();
        Long id = userInfoService.createUser(userInfoDto).getId();
        UserInfoDto loaded = userInfoService.findById(id);
        assertNotNull(loaded);
        assertEqualsProperties(loaded, userInfoDto);
    }

    @Test
    public void findByUserName() throws ApiException {
        UserInfoDto userInfoDto = new UserInfoDtoMockBuilder().
                newFilledInstance().
                build();
        userInfoDto.setUserName("Test");
        userInfoService.createUser(userInfoDto);

        UserInfoDto loaded = userInfoService.findByUserName(userInfoDto.getUserName());
        assertEqualsProperties(loaded, userInfoDto);
    }

    private void assertEqualsProperties(UserInfoDto first, UserInfoDto second) {
        assertEquals(first.getUserName(), second.getUserName());
        assertEquals(first.getPassword(), second.getPassword());
        assertEquals(first.getFirstName(), second.getFirstName());
        assertEquals(first.getLastName(), second.getLastName());
    }


}
