package com.example.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.Repository.RoleRepository;
import com.example.project.entity.role;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RoleService {
  @Autowired
  private RoleRepository roleRepository;

  public List<role> getRole() {
    return roleRepository.GetRole();

  }
  public role findUserByName(String name){
    Optional<role> roleOptional = roleRepository.findByRole_Name(name);
    if(roleOptional.isPresent()){
     role u = roleOptional.get();
      return u;
    }
    return null;
  }

  public role findRoleById(int roleid) {
    Optional<role> useOptional = roleRepository
        .findByRole_Id(roleid);
    if (useOptional.isPresent()) {
      role u = useOptional.get();
      return u;
    }
    return null;
  }

}
