package com.example.project.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.Repository.UserRepository;
import com.example.project.entity.user;

/**
 * UserService
 */
@Service
// define coponent of user cotroller
public class UserService {

  private final UserRepository userRepository;

  @Autowired // connect with userRepository
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<user> getUsers() {

    return userRepository.findAll();
  }

  public void addNewUser(user user) {
    Optional<user> userOptional = userRepository
        .findUserByEmail(user.getEmail());
    if (userOptional.isPresent()) {
      throw new IllegalStateException("email taken");
    }
    userRepository.save(user);
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