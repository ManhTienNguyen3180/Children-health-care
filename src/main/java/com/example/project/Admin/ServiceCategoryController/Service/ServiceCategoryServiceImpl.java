package com.example.project.Admin.ServiceCategoryController.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.project.Admin.ServiceCategoryController.Model.ServiceCategory;
import com.example.project.Admin.ServiceCategoryController.Repository.ServiceCategoryRepository;

@Service
public class ServiceCategoryServiceImpl implements ServiceCategoryService {

    @Autowired
    ServiceCategoryRepository serviceCategoryRepository;

    @Override
    public List<ServiceCategory> findAll() {
        return serviceCategoryRepository.findAll();
    }

    @Override
    public Page<ServiceCategory> findPaginated(int pageNo, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNo - 1, pageSize);
        return serviceCategoryRepository.findAll(pageable);
    }

    @Override
    public void addService(ServiceCategory serviceCategory) {
        serviceCategoryRepository.save(serviceCategory);
    }

    @Override
    public Page<ServiceCategory> searchbyNameDescription(String search,
            int pageNo, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNo - 1, pageSize);
        return serviceCategoryRepository.search(search, pageable);
    }

    @Override
    public Page<ServiceCategory> filterStatus(int status, int pageNo, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNo - 1, pageSize);
        return serviceCategoryRepository.filterStatus(status, pageable);
    }

    @Override
    public Page<ServiceCategory> sortandPaginate(int pageNo, int pageSize, String sortField, String sortDir) {
         Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
        Sort.by(sortField).descending();
        PageRequest pageable = PageRequest.of(pageNo-1,pageSize,sort);
        return serviceCategoryRepository.findAll(pageable);
    }

    @Override
    public ServiceCategory findById(int id) {
        return serviceCategoryRepository.findById(id).get();
    }

    @Override
    public void save(ServiceCategory s) {
        serviceCategoryRepository.save(s);
    }

}
