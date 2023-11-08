package com.example.project.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.project.Admin.ServiceCategoryController.Service.ServiceCategoryService;
import com.example.project.Admin.ServiceController.Model.Service;
import com.example.project.service.FeedbackService;

@Controller

public class AdminFeedback {
    @Autowired
    ServiceCategoryService serviceCategory;
    @Autowired 
    private FeedbackService FeedbackService;

    @GetMapping("admin/feedback")
    public String listAll(Model model) {
        
        return findPaginated(1,model);
    }

    @GetMapping("admin/feedback/page/{pageNo}")
    public String findPaginated(@PathVariable int pageNo,
            Model model) {

        int pageSize = 5;

        Page<Object[]> page = FeedbackService.findPaginated(pageNo, pageSize);
        List<Object[]> listS = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("listAll", listS);
        model.addAttribute("listRate", FeedbackService.ratings());

        model.addAttribute("listCate", serviceCategory.findAll());
        //model.addAttribute("totalService", service.listServices().size());

        return "admin/feedback";
    }

    @GetMapping("admin/feedback-detail/{id}")
    public String getDetail(Model model, @PathVariable("id") int id) {
        

        //model.addAttribute("message", "Change status of service " + id + " successfully!");
        //model.addAttribute("listCate", serviceCategory.findAll());
        model.addAttribute("d",FeedbackService.getFeedbackDetail(id));
        model.addAttribute("s", FeedbackService.getService(id));
        return "admin/feedback-detail";
    }

    @GetMapping("/admin/feedback/filter/{id}")
    public String filterCategory(Model model, @PathVariable("id") int id) {
        model.addAttribute("listRate", FeedbackService.ratings());
        return filterCategoryAndPaginated(model, id, 1);
    }

    @GetMapping("/admin/feedback/filter/{id}/{pageNo}")
    public String filterCategoryAndPaginated(Model model, @PathVariable("id") int id,
            @PathVariable(value = "pageNo") int pageNo) {
        Page<Object[]> page = FeedbackService.paginatedRating(pageNo, 5, id);
        List<Object[]> listS = page.getContent();

        
        model.addAttribute("listRate", FeedbackService.ratings());
        model.addAttribute("listAll", listS);
        model.addAttribute("catId", id);

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        //model.addAttribute("totalService", page.getTotalElements());
        return "admin/feedback";
    }
}
