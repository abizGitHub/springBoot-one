package abiz.ir.demo2.repository;


import abiz.ir.demo2.model.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    List<UserInfo> findByFirstName(String firstName);

    List<UserInfo> findByLastName(String lastName);

    UserInfo findByUserName(String userName);

    List<UserInfo> findByPassword(String password);

    List<UserInfo> findByFirstNameAndLastName(String firstName, String lastName);

}
