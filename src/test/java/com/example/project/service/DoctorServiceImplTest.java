package com.example.project.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.project.Admin.BlogController.Service.BlogsService;
import com.example.project.Admin.BlogController.Service.BlogsServiceImpl;
import com.example.project.Admin.ServiceController.Service.ServiceService;
import com.example.project.Repository.DoctorRepo;

@SpringBootTest
public class DoctorServiceImplTest {
    @Autowired
    private DoctorRepo repo;

    @Autowired
    private ServiceService service;

    @Test
    void testFetchDoctorList() {
        System.out.println(repo.findAll());
    }

    @Test
    void testFetchDoctorList2() {
        int result = service.listServices().size();
        assertEquals(11, result);
    }
}
