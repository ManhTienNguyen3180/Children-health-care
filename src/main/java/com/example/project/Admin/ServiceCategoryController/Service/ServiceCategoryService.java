package com.example.project.Admin.ServiceCategoryController.Service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.project.Admin.ServiceCategoryController.Model.ServiceCategory;

public interface ServiceCategoryService {
    List<ServiceCategory> findAll();

    Page<ServiceCategory> findPaginated(int pageNo, int pageSize);

    void addService(ServiceCategory serviceCategory);

    Page<ServiceCategory> searchbyNameDescription(String search, int pageNo, int i);

    Page<ServiceCategory> filterStatus(int id, int pageNo, int i);

    Page<ServiceCategory> sortandPaginate(int pageNo, int pageSize, String sortField, String sortDir);

    ServiceCategory findById(int id);

    void save(ServiceCategory s);
}
