package com.example.project.Admin.ServiceController.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.project.Admin.ServiceController.Repository.ServiceRepository;

@Service
public class ServiceServiceImpl implements ServiceService {
    @Autowired
    ServiceRepository serviceRepository;

    @Override
    public List<com.example.project.Admin.ServiceController.Model.Service> listServices() {
        return serviceRepository.findAll();
    }

    @Override
    public void addService(com.example.project.Admin.ServiceController.Model.Service service) {
        serviceRepository.save(service);
    }

    @Override
    public Page<com.example.project.Admin.ServiceController.Model.Service> findPaginated(int pageNo, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.serviceRepository.findAll(pageable);
    }

    @Override
    public Page<com.example.project.Admin.ServiceController.Model.Service> searchbyNamePriceDescription(String search,
            int pageNo, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNo - 1, pageSize);
        return serviceRepository.search(search, pageable);
    }

    @Override
    public com.example.project.Admin.ServiceController.Model.Service findById(int id) {
        return serviceRepository.findById(id).get();
    }

    @Override
    public void save(com.example.project.Admin.ServiceController.Model.Service service) {
        serviceRepository.save(service);
    }

    @Override
    public Page<com.example.project.Admin.ServiceController.Model.Service> filterCategory(int categoryId,int pageNo, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNo - 1, pageSize);
        return serviceRepository.filterCategory(categoryId, pageable);
    }

    @Override
    public Page<com.example.project.Admin.ServiceController.Model.Service> filterStatus(int status, int pageNo,
            int pageSize) {
        PageRequest pageable = PageRequest.of(pageNo - 1, pageSize);
        return serviceRepository.filterStatus(status, pageable);
    }

    @Override
    public Page<com.example.project.Admin.ServiceController.Model.Service> sortandPaginate(int pageNo, int pageSize,
            String sortField, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
        Sort.by(sortField).descending();
        PageRequest pageable = PageRequest.of(pageNo-1,pageSize,sort);
        return serviceRepository.findAll(pageable);
    }

}
