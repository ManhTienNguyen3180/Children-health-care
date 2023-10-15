package com.example.project.entity.token;

import java.time.LocalDateTime;

import com.example.project.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class ConfirmationToken {

  
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 //need to add auto increment in mysql database to use
  private long id;

  @Column(nullable = false)
  private String token;
  @Column(nullable = false)
  private LocalDateTime createAt;
  @Column(nullable = false)
  private LocalDateTime expiresAt;

  private LocalDateTime confirmedAt;

  @ManyToOne
  @JoinColumn(nullable = false, name = "user_id")
  private user user;

  public ConfirmationToken() {

  }

  public ConfirmationToken(String token,
      LocalDateTime createAt,
      LocalDateTime expiredAt,
      user user) {
    this.token = token;
    this.createAt = createAt;
    this.expiresAt = expiredAt;

    this.user = user;
  }

  public long getId() {
    return id;
  }

  public String getToken() {
    return token;
  }

  public LocalDateTime getCreateAt() {
    return createAt;
  }

  public LocalDateTime getExpiredAt() {
    return expiresAt;
  }

  public LocalDateTime getConfirmedAt() {
    return confirmedAt;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public void setCreateAt(LocalDateTime createAt) {
    this.createAt = createAt;
  }

  public void setExpiredAt(LocalDateTime expiredAt) {
    this.expiresAt = expiredAt;
  }

  public void setConfirmedAt(LocalDateTime confirmedAt) {
    this.confirmedAt = confirmedAt;
  }

  public user getUser() {
    return user;
  }

  public void setUser(user user) {
    this.user = user;
  }

  

}
