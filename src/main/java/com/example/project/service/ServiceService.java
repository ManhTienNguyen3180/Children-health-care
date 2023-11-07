package com.example.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.project.Repository.ServiceRepo;
import com.example.project.entity.service;

@Service
public class ServiceService {
    @Autowired
    private ServiceRepo repo;

    public List<service> fechServicesList(){
        return repo.findAll();
    }
    public Optional<service> findServiceById(int id){
        return repo.findById(id);
    }

    //phan trang
    public Page<service> findPaginated(int page, int size){
        PageRequest pageable = PageRequest.of(page-1,size);
        return repo.getAllPagi(pageable);
    }

    public Page<service> getServiceByKey(String key,int page,int size){
        PageRequest pageable = PageRequest.of(page-1,size);
        return repo.getServiceByKey(key, pageable);
    }

    public Page<service> getServiceByCategoryId(int id,int page,int size){
        PageRequest pageable = PageRequest.of(page-1,size);
        return repo.getServiceByCategoryId(id, pageable);
    }

    public Object getServiceCategoryName(int id){
        return repo.getCategoryName(id);
    }

    public List<Object> getDocByService(int id){
        return repo.getDocByService(id);
    }

    public List<service> findServiceByDocID(int doctorID) {
        return repo.findServiceByDoctorID(doctorID);
    }
    
    public List<service> findListByServiceId(List<Integer> serviceIds){
        return repo.findByServiceId(serviceIds);
    }
    public List<service> findListNotByServiceId(List<Integer> serviceIds){
        return repo.findByServiceId(serviceIds);
    }
    //find service by category id
    public List<service> findServiceByCategoryId(int categoryId){
        return repo.findServiceByCategoryId(categoryId);
    }
}
