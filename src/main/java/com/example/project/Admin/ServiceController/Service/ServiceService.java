package com.example.project.Admin.ServiceController.Service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.project.Admin.ServiceController.Model.Service;

public interface ServiceService {

    List<Service> listServices();

    void addService(Service service);

    Page<Service> findPaginated(int pageNo, int pageSize);

    Page<Service> searchbyNamePriceDescription(String search, int pageNo, int pageSize);

    Service findById(int id);

    void save(Service service);

    Page<Service> filterCategory(int categoryId, int pageNo, int pageSize);

    Page<Service> filterStatus(int status, int pageNo, int pageSize);

    Page<Service> sortandPaginate(int pageNo, int pageSize, String sortField, String sortDir);
}
