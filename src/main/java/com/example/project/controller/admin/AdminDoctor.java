package com.example.project.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.example.project.entity.doctor;
import com.example.project.service.DoctorService;
import com.example.project.service.ServiceCategoryService;

@Controller
public class AdminDoctor {

    @Autowired
    private DoctorService DoctorService;

    @Autowired
    private ServiceCategoryService ServiceCategoryService;

    @GetMapping("admin/doctors")
    public String listAll(Model model) {
        return findPaginatedDoctor(1, model);
    }

    @GetMapping("/admin/doctors/page/{pageNo}")
    public String findPaginatedDoctor(@PathVariable int pageNo, Model model) {

        int pageSize = 3;
        Page<doctor> page = DoctorService.findPaginated(pageNo, pageSize);
        List<doctor> listB = page.getContent();
        model.addAttribute("serviceCategory", ServiceCategoryService.fetchServiceCategoryList());
        model.addAttribute("doctor", listB);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        return "admin/doctors";
    }

    @GetMapping(value = "/admin/doctors/page/{pageNo}/{keyword}")
    public String searchandPaginated(@PathVariable("pageNo") int pageNo,
            @PathVariable("keyword") String search,
            Model model) {
        Page<doctor> doctorPage = DoctorService.search(search, pageNo, 3);
        List<doctor> listB = doctorPage.getContent();

        model.addAttribute("keyword", search);
        model.addAttribute("blogsCategory", ServiceCategoryService.fetchServiceCategoryList());

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", doctorPage.getTotalPages());

        model.addAttribute("doctor", listB);
        return "admin/doctors";
    }

    @GetMapping(value = "/admin/doctors/search")
    public String search(@RequestParam("keyword") String search, Model model) {
        if (search != "") {
            return searchandPaginated(1, search, model);
        } else {
            return findPaginatedDoctor(1, model);
        }
    }

    @GetMapping("/admin/doctors/filterCategory/{id}")
    public String filterCategory(Model model, @PathVariable("id") int id) {
        return filterCategoryAndPaginated(model, id, 1);
    }

    @GetMapping("/admin/doctors/filterCategory/{id}/{pageNo}")
    public String filterCategoryAndPaginated(Model model, @PathVariable("id") int id,
            @PathVariable(value = "pageNo") int pageNo) {
        Page<doctor> page = DoctorService.filterCategory(id, pageNo, 3);
        List<doctor> listB = page.getContent();

        model.addAttribute("catId", id);

        model.addAttribute("serviceCategory", ServiceCategoryService.fetchServiceCategoryList());

        model.addAttribute("doctor", listB);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        return "admin/doctors";
    }

    @GetMapping("/admin/doctors/filterStatus/{id}")
    public String filterStatus(Model model, @PathVariable("id") int id) {
        return filterStatusAndPaginated(model, id, 1);
    }

    @GetMapping("/admin/doctors/filterStatus/{id}/{pageNo}")
    public String filterStatusAndPaginated(Model model, @PathVariable("id") int id,
            @PathVariable(value = "pageNo") int pageNo) {
        Page<doctor> page = DoctorService.filterStatus(id, pageNo, 3);
        List<doctor> listB = page.getContent();

        model.addAttribute("status", id);

        model.addAttribute("serviceCategory", ServiceCategoryService.fetchServiceCategoryList());

        model.addAttribute("doctor", listB);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        return "admin/doctors";
    }

}
