package itsurena.ir.demo2.model.repository;


import itsurena.ir.demo2.model.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    //@Persistenceusrtext
    //@Autowired
    //EntityManager entityManager;

    @Query("SELECT usr FROM UserInfo usr  WHERE usr.firstName = (:firstName)")
    List<UserInfo> findByfirstName(@Param("firstName") String firstName);

    @Query("SELECT usr FROM UserInfo usr  WHERE usr.lastName = (:lastName)")
    List<UserInfo> findByLastName(@Param("lastName") String lastName);

    @Query("SELECT usr FROM UserInfo usr  WHERE usr.userName = (:userName)")
    List<UserInfo> findByUserName(@Param("userName") String userName);

    @Query("SELECT usr FROM UserInfo usr  WHERE usr.password = (:password)")
    List<UserInfo> findByPassword(@Param("password") String password);

    @Query("SELECT usr FROM UserInfo usr  WHERE usr.firstName = (:firstName) and usr.lastName = (:lastName)")
    List<UserInfo> findByfirstAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);


}
