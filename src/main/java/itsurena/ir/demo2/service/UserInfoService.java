package itsurena.ir.demo2.service;


import itsurena.ir.demo2.exception.UserNotFoundException;
import itsurena.ir.demo2.model.entity.UserInfo;
import itsurena.ir.demo2.model.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UserInfoService {

    @Autowired
    UserInfoRepository repository;

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveUser(UserInfo userInfo) throws javax.validation.ConstraintViolationException {
        userInfo.setCreateDate(new Date());
        repository.save(userInfo);
    }


    public UserInfo loadUserById(Long id) throws UserNotFoundException {
        return repository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public UserInfo loadUserByUserName(String userName) throws UserNotFoundException {
        List<UserInfo> byUserName = repository.findByUserName(userName);
        if (byUserName != null && byUserName.size() > 0) {
            repository.delete(byUserName.get(0));
        } else throw new UserNotFoundException();
        return byUserName.get(0);
    }

    public List<UserInfo> loadAllUsers() {
        return repository.findAll();
    }

    public void deleteById(Long id) throws UserNotFoundException {
        repository.findById(id).orElseThrow(UserNotFoundException::new);
        repository.deleteById(id);
    }

    public void deleteByUserName(String userName) throws UserNotFoundException {
        List<UserInfo> byUserName = repository.findByUserName(userName);
        if (byUserName != null && byUserName.size() > 0) {
            repository.delete(byUserName.get(0));
        } else throw new UserNotFoundException();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void editFirstAndLastName(UserInfo userInfo) throws UserNotFoundException {
        UserInfo loaded = repository.findById(userInfo.getId()).orElseThrow(UserNotFoundException::new);
        loaded.setModifiedDate(new Date());
        loaded.setFirstName(userInfo.getFirstName());
        loaded.setLastName(userInfo.getLastName());
        repository.save(loaded);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void editPassword(UserInfo userInfo) throws UserNotFoundException {
        UserInfo loaded = repository.findById(userInfo.getId()).orElseThrow(UserNotFoundException::new);
        loaded.setModifiedDate(new Date());
        loaded.setPassword(userInfo.getPassword());
        repository.save(loaded);
    }

}

