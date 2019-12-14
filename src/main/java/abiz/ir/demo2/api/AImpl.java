package abiz.ir.demo2.api;

import abiz.ir.demo2.model.entity.UserInfo;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@Api(tags = {"aaa"})
public class AImpl implements A {

    @Override
    public ResponseEntity<UserInfo> getAAA(String bbb) {
        UserInfo userInfo = new UserInfo();
        userInfo.setFirstName(bbb);
        return new ResponseEntity<UserInfo>(userInfo, HttpStatus.OK);
    }


}
