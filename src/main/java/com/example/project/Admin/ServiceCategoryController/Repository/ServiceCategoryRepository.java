package com.example.project.Admin.ServiceCategoryController.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.project.Admin.ServiceCategoryController.Model.ServiceCategory;
@Repository
public interface ServiceCategoryRepository extends JpaRepository<ServiceCategory,Integer> {

    @Query(value = "SELECT * FROM category_service  WHERE `name` LIKE %?1% OR `note` LIKE %?1% ", nativeQuery = true)
    Page<ServiceCategory> search(String search, PageRequest pageable);

    @Query(value = "SELECT * FROM category_service  WHERE `status`= ?1", nativeQuery = true)
    Page<ServiceCategory> filterStatus(int status, PageRequest pageable);
    
}
