package com.example.project.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.project.entity.service;
import com.example.project.service.FeedbackService;
import com.example.project.service.ServiceCategoryService;
import com.example.project.service.ServiceService;

import jakarta.servlet.http.HttpSession;


@Controller
public class ServiceController {   

    // @GetMapping("/service")
    // public String service(){
    //     return "service";
    // }
        @Autowired 
    private FeedbackService FeedbackService;
    @Autowired 
    private ServiceService ServiceService;

    @Autowired
    private ServiceCategoryService serviceCategoryService;

    @GetMapping("/service")
    public String getService(Model model,@Param("key") String key,@RequestParam(value = "page", defaultValue = "1") int page,
                      @RequestParam(value = "size", defaultValue = "8")int size,String category,HttpSession session){
        Page<service> servicePage = null;
        if(key==null && category == null ){
            servicePage = ServiceService.findPaginated(page, size);                

        }else if(key!= null){
            servicePage = ServiceService.getServiceByKey(key, page, size);
            model.addAttribute("key", key);
        }else if(category != null){
            int id = Integer.parseInt(category);
            servicePage = ServiceService.getServiceByCategoryId(id, page, size);
            model.addAttribute("categoryId", id);
        }
        
        
        

        
        model.addAttribute("listCategory", serviceCategoryService.fetchServiceCategoryList());
        model.addAttribute("result", servicePage);
        return "service";
    }

    @GetMapping("/service-detail/{id}")
    public String viewServiceDetail(@PathVariable int id, Model model) {
        service service = ServiceService.findServiceById(id).orElse(null);
        int cate = service.getCategory_id();
        model.addAttribute("listRv", FeedbackService.getFeedbackByS(id));
        model.addAttribute("listCategory", serviceCategoryService.fetchServiceCategoryList());
        model.addAttribute("cateName", ServiceService.getServiceCategoryName(id));
        model.addAttribute("docs", ServiceService.getDocByService(cate));
        model.addAttribute("service", ServiceService.findServiceById(id).orElse(null));
        return "service-detail";
    }

    @GetMapping("/service/add-service")
    public String addServiceToReservation(@RequestParam(value="id") int id,Model model, HttpSession session){
        
        if(session.getAttribute("selectedServices")==null){
            List<service> listS = new ArrayList<>();
            Optional<service> s = ServiceService.findServiceById(id);
            service ss = s.get();
            listS.add(ss);
            session.setAttribute("selectedServices", listS);

        }else{
            List<service> listS = (List<service>)session.getAttribute("selectedServices");
            Optional<service> s = ServiceService.findServiceById(id);
            service ss = s.get();
            listS.add(ss);
            session.setAttribute("selectedServices", listS);
        }
        return "redirect:/service";
    }

}
