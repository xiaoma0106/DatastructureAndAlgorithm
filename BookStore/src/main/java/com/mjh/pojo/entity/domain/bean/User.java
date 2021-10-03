package com.mjh.pojo.entity.domain.bean;

/**
 * @author mjh
 * @date 2021-10-02 13:04
 */
public class User {
    private int id;
    private String username;
    private String psd;
    private String email;


    public User() {
    }

    public User( String username, String psd, String email) {
        this.username = username;
        this.psd = psd;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPsd() {
        return psd;
    }

    public void setPsd(String psd) {
        this.psd = psd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", psd='" + psd + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
