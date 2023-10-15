package com.example.project.Repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.project.entity.role;
import com.example.project.entity.user;
import java.util.List;

//Create a JPA repository
//reponsible of data access and provides CRUD
@Repository
// note
// You're using the Optional type as the return type of the
// method to handle the possibility that no user matches the provided email.
// You can use the Optional methods like isPresent()
// to check if a user was found and then retrieve the user with get() if it
// exists.
public interface UserRepository
            extends JpaRepository<user, Integer>/* type want to work with and type of key */ {
      // list user
      @Query("SELECT u FROM user u WHERE role_id= ?1 ")
      List<user> GetAllByRole(role roleid);

      @Query("SELECT u FROM user u WHERE gender= ?1 ")
      List<user> GetAllByGender(int gender);

      @Query("SELECT u FROM user u WHERE status= ?1 ")
      List<user> GetAllByStatus(int status);

      @Query("SELECT u FROM user u WHERE u.full_name LIKE %:name%")
      List<user> findUsersContain(@Param("name") String name);

      @Query("SELECT u FROM user u WHERE u.full_name LIKE %?1% AND status= ?2 ")
      List<user> findUsersContainAndFilterStatus(String name, int status);

      @Query("SELECT u FROM user u WHERE u.full_name LIKE %?1% AND u.role_id = ?2")
      List<user> findUsersContainAndFilter(String name, role roleId);

      @Query("SELECT u FROM user u WHERE u.full_name LIKE %?1% AND gender= ?2 ")
      List<user> findUsersContainAndFilterGender(String name, int gender);

      // pageable
      Page<user> findAll(Pageable pageable);

      @Query("SELECT u FROM user u WHERE role_id= ?1 ")
      Page<user> findUsersAndFilterRole(role roleId, Pageable pageable);

      @Query("SELECT u FROM user u WHERE gender= ?1 ")
      Page<user> findUsersAndFilterGender(int roleId, Pageable pageable);

      @Query("SELECT u FROM user u WHERE status= ?1 ")
      Page<user> findUsersAndFilterStatus(int status, Pageable pageable);

      @Query("SELECT u FROM user u WHERE u.full_name LIKE %:name%")
      Page<user> findUsersContainWithPaging(@Param("name") String name, Pageable pageable);

      @Query("SELECT u FROM user u WHERE u.full_name LIKE %?1% AND u.role_id = ?2")
      Page<user> findUsersContainAndFilterRoleWithPaging(String name, role roleId, Pageable pageable);

      @Query("SELECT u FROM user u WHERE u.full_name LIKE %?1% AND status= ?2 ")
      Page<user> findUsersContainsAndFilterStatusWithPaging(String name, int status, Pageable pageable);

      @Query("SELECT u FROM user u WHERE u.full_name LIKE %?1% AND gender= ?2 ")
      Page<user> findUsersContainsAndFilterGenderWithPaging(String name, int gender, Pageable pageable);

      // truy van
      @Query("SELECT u FROM user u WHERE email=?1 ")
      // "?1"first parameter passed to the method
      Optional<user> findUserByEmail(String email);

      @Query("SELECT u FROM user u WHERE username=?1")
      Optional<user> findUserByUsername(String username);

      Optional<user> findByEmail(String email);

      Optional<user> findByResetToken(String resetToken);

}
