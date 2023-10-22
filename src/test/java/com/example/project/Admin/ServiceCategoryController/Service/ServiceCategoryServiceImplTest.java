package com.example.project.Admin.ServiceCategoryController.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.example.project.Admin.ServiceCategoryController.Model.ServiceCategory;

public class ServiceCategoryServiceImplTest {
    ServiceCategoryServiceImpl service;
    @Test
    void testFindAll() {
        List<ServiceCategory> listS = new ArrayList<>();
        listS.add(new ServiceCategory(6, "Primary Care", "dsfasdf", 1, "2023-10-22", "admin"));
        listS.add(new ServiceCategory(7, "Specialty Care Services", "abf", 1, "2023-10-22", "abf"));
        assertEquals(listS,service.findAll(),"nice");
    }
}
