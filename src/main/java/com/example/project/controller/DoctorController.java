package com.example.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.project.entity.doctor;
import com.example.project.service.DoctorService;
import com.example.project.service.FeedbackService;
import com.example.project.service.ServiceCategoryService;

@Controller
public class DoctorController {
    
    @Autowired 
    private FeedbackService FeedbackService;

    @Autowired 
    private DoctorService DoctorService;

    @Autowired
    private ServiceCategoryService serviceCategoryService;

    @GetMapping("/doctor")
    public String getDoctor(Model model,@RequestParam(value = "page", defaultValue = "1") int page,
        @RequestParam(value = "size", defaultValue = "8")int size,String category){
        
        Page<doctor> doctorPage = null;
        if(category==null){
            doctorPage = DoctorService.getAllDoc(page, size);
        }else{
            int id = Integer.parseInt(category);
            doctorPage = DoctorService.getDocByService(id, page, size);
            model.addAttribute("categoryId", id);
        }
        
        model.addAttribute("listCategory", serviceCategoryService.fetchServiceCategoryList());
        model.addAttribute("result", doctorPage);
        return "doctor";
    }

    
    @GetMapping("/doctor-detail/{id}")
    public String viewDoctorDetail(@PathVariable int id, Model model) {
        
        model.addAttribute("listRv", FeedbackService.getFeedbackByDoc(id));
        model.addAttribute("listSlot", DoctorService.getSlotByDoc(id));
        model.addAttribute("listSer", DoctorService.getSerByDoc(id));
        model.addAttribute("doc", DoctorService.findDoctorById(id).orElse(null));
        model.addAttribute("listCategory", DoctorService.getCategoryNameByDoctorID(id));
        return "doctor-detail";
    }

    
}
