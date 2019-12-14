package abiz.ir.demo2.model.entity;


import javax.persistence.*;
import java.util.Date;


@Entity(name = "UserInfo")
@Table(name = "USER_INFO")
public class UserInfo {

    @Id
    @GeneratedValue
    private Long id;

    @Column(columnDefinition = "NVARCHAR2(30)", name = "USERNAME")
    private String userName;

    @Column(columnDefinition = "NVARCHAR2(30)")
    private String password;

    @Column(columnDefinition = "NVARCHAR2(30)", name = "FIRST_NAME")
    private String firstName;

    @Column(columnDefinition = "NVARCHAR2(30)", name = "LAST_NAME")
    private String lastName;

    @Column(columnDefinition = "Date", name = "CREATE_DATE")
    private Date createDate;

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

}
