package com.example.project.Admin.ServiceController.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.project.Admin.ServiceController.Model.Service;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer> {
    @Query(value = "SELECT * FROM service  WHERE `service_name` LIKE %?1% OR `description` LIKE %?1% OR `price` LIKE  %?1%", nativeQuery = true)
    Page<Service> search(@Param("searchText") String keyword, PageRequest pageable);

    @Query(value = "SELECT * FROM service  WHERE `category_id`= ?1", nativeQuery = true)
    Page<Service> filterCategory(@Param("categoryId") int categoryId, PageRequest pageable);

    @Query(value = "SELECT * FROM service  WHERE `status`= ?1", nativeQuery = true)
    Page<Service> filterStatus(@Param("status") int status, PageRequest pageable);
}
