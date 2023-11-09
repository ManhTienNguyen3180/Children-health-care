package com.example.project.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.project.Repository.UserRepository;
import com.example.project.entity.role;
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
  @Autowired
  private RoleService roleService;

  @Bean
  public static PasswordEncoder Encoder() {
    return new BCryptPasswordEncoder();
  }

  public Optional<user> findByEmail(String email) {
    return userRepository.findUserByEmail(email);
  }

  public Optional<user> findByResetToken(String resetToken) {
    return userRepository.findByResetToken(resetToken);
  }

  public void save(user user) {
    userRepository.save(user);
  }
  public void saveImage(user user) {
    userRepository.save(user);
  }
  public List<user> getUsers() {

    return userRepository.findAll();
  }

  public Page<user> FindPaginated(int pageNo, int pageSize) {
    Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
    return userRepository.findAll(pageable);
  }

  public Page<user> findUsersAndFilterRole(role i, int pageNo, int pageSize) {
    Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
    return userRepository.findUsersAndFilterRole(i, pageable);
  }

  public Page<user> findUsersAndFilterGender(int i, int pageNo, int pageSize) {
    Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
    return userRepository.findUsersAndFilterGender(i, pageable);
  }

  public Page<user> findUsersAndFilterStatus(int i, int pageNo, int pageSize) {
    Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
    return userRepository.findUsersAndFilterStatus(i, pageable);
  }

  public Page<user> findUsersContainWithPaging(String i, int pageNo, int pageSize) {
    Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
    return userRepository.findUsersContainWithPaging(i, pageable);
  }

  public Page<user> findUsersContainAndFilterRoleWithPaging(String i, role roleid, int pageNo, int pageSize) {
    Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
    return userRepository.findUsersContainAndFilterRoleWithPaging(i, roleid, pageable);
  }

  public Page<user> findUsersContainsAndFilterStatusWithPaging(String i, int status, int pageNo, int pageSize) {
    Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
    return userRepository.findUsersContainsAndFilterStatusWithPaging(i, status, pageable);
  }

  public Page<user> findUsersContainsAndFilterGenderWithPaging(String i, int gender, int pageNo, int pageSize) {
    Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
    return userRepository.findUsersContainsAndFilterGenderWithPaging(i, gender, pageable);
  }

  public List<user> getUsersByGender(int gender) {

    return userRepository.GetAllByGender(gender);
  }

  public List<user> getUsersByStatus(int i) {
    return userRepository.GetAllByStatus(i);
  }

  public List<user> findUsersContainAndFilter(String name, role i) {
    return userRepository.findUsersContainAndFilter(name, i);
  }

  public List<user> findUsersContainAndFilterStatus(String name, int status) {
    return userRepository.findUsersContainAndFilterStatus(name, status);
  }

  public List<user> findUsersContainAndFilterGender(String name, int gender) {
    return userRepository.findUsersContainAndFilterGender(name, gender);
  }

  public List<user> getUsersListRole(role r) {
    return userRepository.GetAllByRole(r);
  }

  public List<user> getUsersSearch(String r) {
    return userRepository.findUsersContain(r);
  }

  public void addNewUser(user u) {
    if (userRepository.findUserByEmail(u.getEmail()).isPresent()) {

    } else {
      userRepository.save(u);
      // create token
      String token = UUID.randomUUID().toString();
      ConfirmationToken ConfirmToken = new ConfirmationToken(
          token,
          LocalDateTime.now(),
          LocalDateTime.now().plusMonths(3),
          u);
      // sendConfirm token
      tokenService.saveConfirmToken(ConfirmToken);
      String domain = "localhost:8080";
      String link = "http://" + domain + "/signup/confirm?token=" + token;
      emailservice.sendConfirm(u.getEmail(), link);
    }

  }

  public void addNewUser2(user u) {
    if (userRepository.findUserByEmail(u.getEmail()).isPresent()) {

    } else {
      userRepository.save(u);

      String domain = "localhost:8080";
      String link = "http://" + domain + "/login";
      emailservice.sendNotification(u.getEmail(), link, u.getPassword());
    }
  }

  // update status to active
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

  public void updateStatus(int userid){
    Optional<user> useOptional = userRepository
        .findById(userid);
    if (useOptional.isPresent()) {
      user foundUser = useOptional.get();
      foundUser.setStatus(0);
      userRepository.save(foundUser);
    } 
  }
  // update user status and role
  public void updateUserByUserid2(int userid, String statusUpdate, String roleUpdate) {
    Optional<user> useOptional = userRepository
        .findById(userid);
    if (useOptional.isPresent()) {
      user foundUser = useOptional.get();
      foundUser.setRolename(roleService.findRoleById(Integer.parseInt(roleUpdate)).getRole_name());
      foundUser.setStatus(Integer.parseInt(statusUpdate));
      foundUser.setRole_id(roleService.findRoleById(Integer.parseInt(roleUpdate)));
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
