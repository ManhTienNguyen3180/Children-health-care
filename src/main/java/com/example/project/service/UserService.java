package com.example.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.project.Repository.UserRepository;
import com.example.project.entity.user;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Bean
    public static PasswordEncoder Encoder() {
        return new BCryptPasswordEncoder();
    }
    

    public Optional<user> findByEmail(String email){
        return userRepository.findUserByEmail(email);
    }
    public Optional<user> findByResetToken(String resetToken){
        return userRepository.findByResetToken(resetToken);
    }
    public void save(user user){
        
        String pass = Encoder().encode(user.getPassword());
        user.setPassword(pass);
        userRepository.save(user);
    }
    public void saveImage(user user){
        userRepository.save(user);
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
