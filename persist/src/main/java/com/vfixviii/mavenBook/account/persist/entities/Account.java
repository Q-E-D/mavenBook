package com.vfixviii.mavenBook.account.persist.entities;

/**
 * 用户实体类.
 */
public class Account {

    private Integer id;

    private String name;

    private String email;

    private String password;

    private Boolean activited;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Boolean getActivited() {
        return activited;
    }

    public void setActivited(Boolean activited) {
        this.activited = activited;
    }
}