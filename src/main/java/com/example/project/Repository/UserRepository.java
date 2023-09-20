package com.example.project.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.project.entity.user;
import java.util.List;

//Create a JPA repository
//reponsible of data access and provides CRUD
@Repository
//note 
//You're using the Optional type as the return type of the 
//method to handle the possibility that no user matches the provided email.
//You can use the Optional methods like isPresent() 
//to check if a user was found and then retrieve the user with get() if it exists.
public interface UserRepository
            extends JpaRepository<user, Integer>/* type want to work with and type of key */ {

      @Query("SELECT u FROM user u WHERE email=?1 ")
      //"?1"first parameter passed to the method      
      Optional<user> findUserByEmail(String email);
      @Query("SELECT u FROM user u WHERE username=?1")   
      Optional<user> findUserByUsername(String username);

}
