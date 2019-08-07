package itsurena.ir.demo2.service;


import itsurena.ir.demo2.controller.UserInfoDto;
import itsurena.ir.demo2.exception.ApiException;
import itsurena.ir.demo2.exception.ApiExceptionType;
import itsurena.ir.demo2.model.entity.UserInfo;
import itsurena.ir.demo2.repository.UserInfoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserInfoService {

    @Autowired
    UserInfoRepository repository;

    public UserInfoDto createUser(UserInfoDto userInfoDto) throws ApiException {
        ValidateFirstAndLastName(userInfoDto);

        if (userInfoDto.getUserName() == null)
            throw new ApiException(ApiExceptionType.USER_NAME_MUST_NOT_NULL);
        if (userInfoDto.getPassword() == null)
            throw new ApiException(ApiExceptionType.PASSWORD_MUST_NOT_NULL);
        if (userInfoDto.getUserName().trim().length() < 4)
            throw new ApiException(ApiExceptionType.USER_NAME_MIN_LENGTH);
        if (userInfoDto.getUserName().length() > 30)
            throw new ApiException(ApiExceptionType.USER_NAME_MAX_LENGTH);

        if (userInfoDto.getPassword().trim().length() < 4)
            throw new ApiException(ApiExceptionType.PASSWORD_MIN_LENGTH);
        if (userInfoDto.getPassword().length() > 8)
            throw new ApiException(ApiExceptionType.PASSWORD_MAX_LENGTH);

        UserInfo loaded = repository.findByUserName(userInfoDto.getUserName());
        if (loaded != null)
            throw new ApiException(ApiExceptionType.USER_NAME_ALREADY_EXIST);

        userInfoDto.setCreateDate(new Date());
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userInfoDto, userInfo);
        UserInfo savedUserInfo = repository.save(userInfo);
        UserInfoDto responseUserInfoDto = new UserInfoDto();
        BeanUtils.copyProperties(savedUserInfo, responseUserInfoDto);
        return responseUserInfoDto;
    }

    public void deleteByUserName(String userName) throws ApiException {
        UserInfo loaded = repository.findByUserName(userName);
        if (loaded == null)
            throw new ApiException(ApiExceptionType.USER_NOT_FOUND);
        repository.delete(loaded);
    }

    public void deleteById(Long id) throws ApiException {
        Optional<UserInfo> loaded = repository.findById(id);
        if (!loaded.isPresent())
            throw new ApiException(ApiExceptionType.USER_NOT_FOUND);
        repository.deleteById(id);
    }

    public UserInfoDto findById(Long id) throws ApiException {
        Optional<UserInfo> loaded = repository.findById(id);
        if (!loaded.isPresent())
            throw new ApiException(ApiExceptionType.USER_NOT_FOUND);
        UserInfoDto responseUserInfoDto = new UserInfoDto();
        BeanUtils.copyProperties(loaded.get(), responseUserInfoDto);
        return responseUserInfoDto;
    }

    public UserInfoDto findByUserName(String userName) throws ApiException {
        UserInfo loaded = repository.findByUserName(userName);
        if (loaded == null)
            throw new ApiException(ApiExceptionType.USER_NOT_FOUND);
        UserInfoDto responseUserInfoDto = new UserInfoDto();
        BeanUtils.copyProperties(loaded, responseUserInfoDto);
        return responseUserInfoDto;
    }

    public List<UserInfoDto> findAll() throws ApiException {
        List<UserInfo> list = repository.findAll();
        if (list == null || list.size() == 0)
            throw new ApiException(ApiExceptionType.USERS_LIST_IS_EMPTY);
        List<UserInfoDto> dtos = new ArrayList<>();
        for (UserInfo userInfo : list) {
            UserInfoDto dto = new UserInfoDto();
            BeanUtils.copyProperties(userInfo, dto);
            dtos.add(dto);
        }
        return dtos;
    }

    public void modifyFirstAndLastName(Long id, String firstName, String lastName) throws ApiException {
        Optional<UserInfo> loaded = repository.findById(id);
        if (!loaded.isPresent())
            throw new ApiException(ApiExceptionType.USER_NOT_FOUND);

        ValidateFirstAndLastName(firstName, lastName);

        loaded.get().setModifiedDate(new Date());
        loaded.get().setFirstName(firstName);
        loaded.get().setLastName(lastName);
        repository.save(loaded.get());
    }

    public void modifyPassword(Long id, String oldPassword, String newPassword) throws ApiException {
        Optional<UserInfo> loaded = repository.findById(id);
        if (!loaded.isPresent())
            throw new ApiException(ApiExceptionType.USER_NOT_FOUND);
        if (newPassword == null)
            throw new ApiException(ApiExceptionType.PASSWORD_MUST_NOT_NULL);
        if (!loaded.get().getPassword().equals(oldPassword))
            throw new ApiException(ApiExceptionType.PASSWORD_INCORRECT_ERROR);
        if (newPassword.trim().length() < 4)
            throw new ApiException(ApiExceptionType.PASSWORD_MIN_LENGTH);
        if (newPassword.length() > 8)
            throw new ApiException(ApiExceptionType.PASSWORD_MAX_LENGTH);
        loaded.get().setModifiedDate(new Date());
        loaded.get().setPassword(newPassword);
        repository.save(loaded.get());
    }

    private void ValidateFirstAndLastName(UserInfoDto userInfoDto) throws ApiException {

        if (userInfoDto.getFirstName() == null || userInfoDto.getFirstName().trim().isEmpty())
            throw new ApiException(ApiExceptionType.FIRST_NAME_MUST_NOT_NULL);
        if (userInfoDto.getLastName() == null || userInfoDto.getLastName().trim().isEmpty())
            throw new ApiException(ApiExceptionType.LAST_NAME_MUST_NOT_NULL);

        if (userInfoDto.getFirstName().trim().length() < 4)
            throw new ApiException(ApiExceptionType.FIRST_NAME_MIN_LENGTH);
        if (userInfoDto.getFirstName().length() > 20)
            throw new ApiException(ApiExceptionType.FIRST_NAME_MAX_LENGTH);

        if (userInfoDto.getLastName().trim().length() < 4)
            throw new ApiException(ApiExceptionType.LAST_NAME_MIN_LENGTH);
        if (userInfoDto.getLastName().length() > 20)
            throw new ApiException(ApiExceptionType.LAST_NAME_MAX_LENGTH);

    }

    private void ValidateFirstAndLastName(String firstName, String lastName) throws ApiException {
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setFirstName(firstName);
        userInfoDto.setLastName(lastName);
        ValidateFirstAndLastName(userInfoDto);
    }


    public void deleteAll() {
        repository.deleteAll();
    }

}

