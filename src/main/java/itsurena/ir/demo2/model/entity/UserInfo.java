package itsurena.ir.demo2.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity(name = "UserInfo")
@Table(name = "USER_INFO")
public class UserInfo {

    @Id
    @GeneratedValue
    @JsonView(UserInfoView.UpdateScope.class)
    private Long id;


    @NotBlank(message = "userName is mandatory")
    @JsonView(UserInfoView.CreateScope.class)
    @Column(columnDefinition = "NVARCHAR2(30)", name = "USERNAME")
    private String userName;

    @JsonView(UserInfoView.CreateScope.class)
    @Column(columnDefinition = "NVARCHAR2(30)")
    private String password;

    @NotBlank(message = "firstName is mandatory")
    @JsonView(UserInfoView.CreateScope.class)
    @Column(columnDefinition = "NVARCHAR2(30)", name = "FIRST_NAME")
    private String firstName;

    @JsonView(UserInfoView.CreateScope.class)
    @Column(columnDefinition = "NVARCHAR2(30)", name = "LAST_NAME")
    private String lastName;

    @JsonIgnore
    @Column(columnDefinition = "Date", name = "CREATE_DATE")
    private Date createDate;

    @JsonIgnore
    @Column(columnDefinition = "Date", name = "MODIFIED_DATE")
    private Date modifiedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Override
    public String toString() {
        return "id:" + id
                + " |userName:" + userName
                + " |password:" + password
                + " |firstName:" + firstName
                + " |lastName:" + lastName
                + " |createDate:" + createDate
                + " |modifiedDate:" + modifiedDate;
    }
}
