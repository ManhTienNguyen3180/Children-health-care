package com.example.project.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;


@Entity
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
    private String username;
    private String password;
    private String full_name;
    private int gender;
    private int phone;
    @Email(message = "Please provide a valid e-mail")
    @NotEmpty(message = "Please provide an e-mail")
    private String email;
    private String image;
    private int status;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private role role_id;
    private String create_by;
    private LocalDate create_at;
    @Column(name = "reset_token")
    private String resetToken;
    @Column(name = "rolename")
    private String rolename;

    public user() {
    }

    public user(int user_id, String username, String password, String full_name, int gender, int phone,
            @Email(message = "Please provide a valid e-mail") @NotEmpty(message = "Please provide an e-mail") String email,
            String image, int status, role role_id, String create_by, LocalDate create_at, String resetToken) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.full_name = full_name;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.image = image;
        this.status = status;
        this.role_id = role_id;
        this.create_by = create_by;
        this.create_at = create_at;
        this.resetToken = resetToken;
    }

    public user(int user_id, String username, String password, String full_name, int gender, int phone,
            @Email(message = "Please provide a valid e-mail") @NotEmpty(message = "Please provide an e-mail") String email,
            String image, int status, role role_id, String create_by, LocalDate create_at, String resetToken,
            String rolename) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.full_name = full_name;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.image = image;
        this.status = status;
        this.role_id = role_id;
        this.create_by = create_by;
        this.create_at = create_at;
        this.resetToken = resetToken;
        this.rolename = rolename;
    }
    
    public user(String username, String password, String full_name, int gender, int phone,
            @Email(message = "Please provide a valid e-mail") @NotEmpty(message = "Please provide an e-mail") String email,
            String image, int status, role role_id, String create_by, LocalDate create_at) {
        this.username = username;
        this.password = password;
        this.full_name = full_name;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.image = image;
        this.status = status;
        this.role_id = role_id;
        this.create_by = create_by;
        this.create_at = create_at;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public role getRole_id() {
        return role_id;
    }

    public void setRole_id(role role_id) {
        this.role_id = role_id;
    }

    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    public LocalDate getCreate_at() {
        return create_at;
    }

    public void setCreate_at(LocalDate create_at) {
        this.create_at = create_at;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
    

}
