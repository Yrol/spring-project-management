package com.yrol.pma.entities;

import com.yrol.pma.enums.useraccount.Roles;
import com.yrol.pma.validation.useraccount.UniqueEmail;
import com.yrol.pma.validation.useraccount.UniqueUsername;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user_accounts")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_accounts_seq")
    @Column(name = "user_id")
    private long userId;

    //Size.UserAccount.userName is defined in ValidationMessages.properties
    @NotEmpty
    @Size(min = 5, max = 50, message = "{Size.UserAccount.userName}")
    @UniqueUsername
    @Column(name = "username", unique = true)
    private String userName;

    @NotEmpty
    @Email
    @UniqueEmail
    @Column(unique = true)
    private String email;

    //Size.UserAccount.password is defined in ValidationMessages.properties
    @NotEmpty
    @Size(min = 5, max = 50, message = "{Size.UserAccount.password}")
    private String password;

    private boolean enabled = true;

    @Enumerated(EnumType.STRING)
    private Roles role;

    public UserAccount() {}

    public UserAccount(String userName, String email, String password, boolean enabled) {
        super();
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}
