package com.example.project.service;

<<<<<<< src/main/java/com/example/project/service/UserService.java


=======
import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.Repository.UserRepo;
import com.example.project.entity.user;

@Service
public class UserService {
    
    @Autowired
    private UserRepo userRepository;

    public Optional<user> findByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public Optional<user> findByResetToken(String resetToken){
        return userRepository.findByResetToken(resetToken);
    }
    public void save(user user){
        repo.save(user);
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
>>>>>>> src/main/java/com/example/project/service/UserService.java
