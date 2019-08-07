package itsurena.ir.demo2.utils;


import itsurena.ir.demo2.controller.UserInfoDto;

import java.util.UUID;

public class UserInfoDtoMockBuilder {

    UserInfoDto userInfo;

    public UserInfoDtoMockBuilder newFilledInstance() {
        userInfo = new UserInfoDto();
        userInfo.setFirstName("FNAME-" + UUID.randomUUID().toString().substring(0, 6));
        userInfo.setLastName("LNAME-" + UUID.randomUUID().toString().substring(0, 6));
        userInfo.setUserName("USERNAME-" + UUID.randomUUID().toString().substring(0, 6));
        userInfo.setPassword("PASS-12");
        return this;
    }

    public UserInfoDtoMockBuilder exceedMinLengthUserName() {
        userInfo.setUserName(UUID.randomUUID().toString().substring(0, 2));
        return this;
    }

    public UserInfoDtoMockBuilder exceedMaxLengthUserName() {
        userInfo.setUserName(UUID.randomUUID().toString());
        return this;
    }

    public UserInfoDtoMockBuilder exceedMinLengthPassword() {
        userInfo.setPassword(UUID.randomUUID().toString().substring(0, 2));
        return this;
    }

    public UserInfoDtoMockBuilder exceedMaxLengthPassword() {
        userInfo.setPassword(UUID.randomUUID().toString());
        return this;
    }

    public UserInfoDtoMockBuilder exceedMinLengthFirstName() {
        userInfo.setFirstName(UUID.randomUUID().toString().substring(0, 2));
        return this;
    }

    public UserInfoDtoMockBuilder exceedMaxLengthFirstName() {
        userInfo.setFirstName(UUID.randomUUID().toString());
        return this;
    }

    public UserInfoDtoMockBuilder exceedMinLengthLastName() {
        userInfo.setLastName(UUID.randomUUID().toString().substring(0, 2));
        return this;
    }

    public UserInfoDtoMockBuilder exceedMaxLengthLastName() {
        userInfo.setLastName(UUID.randomUUID().toString());
        return this;
    }

    public UserInfoDtoMockBuilder nullUserName() {
        userInfo.setUserName(null);
        return this;
    }

    public UserInfoDtoMockBuilder nullFirstName() {
        userInfo.setFirstName(null);
        return this;
    }

    public UserInfoDtoMockBuilder nullLastName() {
        userInfo.setLastName(null);
        return this;
    }

    public UserInfoDtoMockBuilder nullPassword() {
        userInfo.setPassword(null);
        return this;
    }

    public UserInfoDto build() {
        return userInfo;
    }


}
