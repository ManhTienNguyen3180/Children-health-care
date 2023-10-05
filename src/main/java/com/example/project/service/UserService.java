package com.example.project.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.Repository.UserRepository;
import com.example.project.entity.user;
import com.example.project.entity.token.ConfirmationToken;
import com.example.project.entity.token.ConfirmationTokenService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private ConfirmationTokenService tokenService;
  @Autowired
  private EmailServiceImpl emailservice;

  public Optional<user> findByEmail(String email) {
    return userRepository.findUserByEmail(email);
  }

  public Optional<user> findByResetToken(String resetToken) {
    return userRepository.findByResetToken(resetToken);
  }

  public void save(user user) {
    userRepository.save(user);
  }

  public List<user> getUsers() {

    return userRepository.findAll();
  }

  public void addNewUser(user user) {
    Optional<user> userOptional = userRepository
        .findUserByEmail(user.getEmail());
    if (userOptional.isPresent()) {
      throw new IllegalStateException();
    }
    userRepository.save(user);
    // create token
    String token = UUID.randomUUID().toString();
    ConfirmationToken ConfirmToken = new ConfirmationToken(
        token,
        LocalDateTime.now(),
        LocalDateTime.now().plusMonths(3),
        user);
    // sendConfirm token
    tokenService.saveConfirmToken(ConfirmToken);
    String domain = "localhost:8080";
    String link = "http://" + domain + "/signup/confirm?token=" + token;
    emailservice.sendConfirm(user.getEmail(), link);
  }

  public void updateUserByUserid(int userid) {
    Optional<user> useOptional = userRepository
        .findById(userid);
    if (useOptional.isPresent()) {
      user foundUser = useOptional.get();
      foundUser.setStatus(1);
      userRepository.save(foundUser);
    } else {
      // Handle the case where the user is not found
      throw new EntityNotFoundException("User not found with ID: " + userid);
    }
  }
  public user findUserById(int userid) {
    Optional<user> useOptional = userRepository
        .findById(userid);
    if (useOptional.isPresent()) {
      user u = useOptional.get();
      return u;
    }
    return null;
  }
  public user findUserByEmail(String email) {
    Optional<user> useOptional = userRepository
        .findUserByEmail(email);
    if (useOptional.isPresent()) {
      user u = useOptional.get();
      return u;
    }
    return null;
  }

  public user findUserByUserN(String username) {
    Optional<user> useOptional = userRepository
        .findUserByUsername(username);
    if (useOptional.isPresent()) {
      user u = useOptional.get();
      return u;
    }
    return null;
  }

  public void deleteUser(int userid) {
    userRepository.findById(userid);
    boolean exists = userRepository.existsById(userid);
    if (!exists) {
      throw new IllegalStateException("User with id " + userid + " does not exist");

    }
    userRepository.deleteById(userid);
  }
}
