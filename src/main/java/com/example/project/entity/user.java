package com.example.project.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor

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
    // @Column(name = "role")
    // private int role_id;
    private String create_by;
    private LocalDate create_at;

    @Column(name = "reset_token")
    private String resetToken;
    @Column(name = "rolename")
    private String rolename;
    public user(String username, String password, String full_name, int gender, int phone,
            @Email(message = "Please provide a valid e-mail") @NotEmpty(message = "Please provide an e-mail") String email,
            String image, int status, String create_by, LocalDate create_at, String rolename) {
        this.username = username;
        this.password = password;
        this.full_name = full_name;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.image = image;
        this.status = status;
        this.create_by = create_by;
        this.create_at = create_at;
        this.rolename = rolename;
    }
    public user(int user_id, String username, String password, String full_name, int gender, int phone,
            @Email(message = "Please provide a valid e-mail") @NotEmpty(message = "Please provide an e-mail") String email,
            String image, int status, String create_by, LocalDate create_at, String resetToken, String rolename) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.full_name = full_name;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.image = image;
        this.status = status;
        this.create_by = create_by;
        this.create_at = create_at;
        this.resetToken = resetToken;
        this.rolename = rolename;
    }
    @Override
    public String toString() {
        return "user [user_id=" + user_id + ", username=" + username + ", password=" + password + ", full_name="
                + full_name + ", gender=" + gender + ", phone=" + phone + ", email=" + email + ", image=" + image
                + ", status=" + status + ", create_by=" + create_by + ", create_at=" + create_at + ", resetToken="
                + resetToken + ", rolename=" + rolename + "]";
    }
    
    
    
}
