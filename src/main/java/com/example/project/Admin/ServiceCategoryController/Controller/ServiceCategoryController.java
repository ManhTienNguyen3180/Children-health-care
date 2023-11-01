package com.example.project.Admin.ServiceCategoryController.Controller;

import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.project.Admin.ServiceCategoryController.Model.ServiceCategory;
import com.example.project.Admin.ServiceCategoryController.Service.ServiceCategoryService;
import com.example.project.Admin.ServiceController.Model.Service;

@Controller
public class ServiceCategoryController {

    @Autowired
    ServiceCategoryService service;

    @GetMapping("/departmentsManagement")
    public String findAll(Model model) {
        return findPaginated(1, model);
    }

    @GetMapping("/departmentsManagement/page/{pageNo}")
    public String findPaginated(@PathVariable int pageNo,
            Model model) {

        int pageSize = 5;

        Page<ServiceCategory> page = service.findPaginated(pageNo, pageSize);
        List<ServiceCategory> listD = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("departments", listD);

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalService", page.getTotalElements());

        return "admin/departments";
    }

    @PostMapping("/departmentsManagement/addDepartment")
    public String addDepartment(Model model, @RequestParam("name") String name,
            @RequestParam("description") String note) {
        List<ServiceCategory> listS = service.findAll();
        for (ServiceCategory serviceCategory : listS) {

            if (serviceCategory.getName().equalsIgnoreCase(name)) {

                return "redirect:/departmentsManagement/addFail";
            }
        }
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        service.addService(new ServiceCategory(name, note, 1, date.toString(), "admin"));
        return "redirect:/departmentsManagement/addSuccess";
    }

    @GetMapping("/departmentsManagement/addSuccess")
    public String addSuccess(Model model) {
        model.addAttribute("message", "Add Department Successfully!");
        return findPaginated(1, model);
    }

    @GetMapping("/departmentsManagement/addFail")
    public String addFail(Model model) {
        model.addAttribute("addFail", "Department is exist!");
        return findPaginated(1, model);
    }

    @GetMapping(value = "/departmentsManagement/search")
    public String search(@RequestParam("searchText") String search, Model model) {
        model.addAttribute("searchText", search);
        return searchandPaginated(1, search.trim(), model);
    }

    @GetMapping(value = "/departmentsManagement/page/{pageNo}/{searchText}")
    public String searchandPaginated(@PathVariable("pageNo") int pageNo,
            @PathVariable("searchText") String search,
            Model model) {
        Page<ServiceCategory> page;
        if (search == "")
            return findPaginated(1, model);
        else
            page = service.searchbyNameDescription(search, pageNo, 5);

        List<ServiceCategory> listS = page.getContent();

        model.addAttribute("searchText", search);
        model.addAttribute("departments", listS);

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalService", page.getTotalElements());
        return "admin/departments";
    }

    @GetMapping("/departmentsManagement/filterStatus/{id}")
    public String filterStatus(Model model, @PathVariable("id") int id) {
        return filterStatusAndPaginated(model, id, 1);
    }

    @GetMapping("/departmentsManagement/filterStatus/{id}/{pageNo}")
    public String filterStatusAndPaginated(Model model, @PathVariable("id") int id,
            @PathVariable(value = "pageNo") int pageNo) {
        Page<ServiceCategory> page = service.filterStatus(id, pageNo, 5);
        List<ServiceCategory> listS = page.getContent();

        model.addAttribute("departments", listS);

        model.addAttribute("status", id);

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalService", page.getTotalElements());
        return "admin/departments";
    }

    @GetMapping("/departmentsManagement/sort/")
    public String sort(
            @RequestParam("sortField") String sortField,
            @RequestParam("sortDir") String sortDir,
            Model model) {
        return sortadnPaginate(1, sortField, sortDir, model);
    }

    @GetMapping("/departmentsManagement/sort/{pageNo}")
    public String sortadnPaginate(@PathVariable int pageNo,
            @RequestParam("sortField") String sortField,
            @RequestParam("sortDir") String sortDir,
            Model model) {
        int pageSize = 5;
        Page<ServiceCategory> page = service.sortandPaginate(pageNo, pageSize, sortField, sortDir);
        List<ServiceCategory> listS = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("departments", listS);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalService", page.getTotalElements());
        return "admin/departments";
    }

    @PostMapping("/departmentsManagement/changeStatus")
    public String changeStatus(@RequestParam("Id") int id,
            @RequestParam("pageNo") int pageNo, Model model) {
        ServiceCategory s = service.findById(id);
        if (s.getStatus() == 1)
            s.setStatus(0);
        else
            s.setStatus(1);
        service.save(s);
        model.addAttribute("message", "Change status of department " + id + " successfully!");
        return findPaginated(pageNo, model);
    }

    @GetMapping("/departmentsManagement/department-detail/{currentPage}/{id}")
    public String getServiceDetail(Model model, @PathVariable("currentPage") int pageNo, @PathVariable("id") int id) {
        ServiceCategory s = service.findById(id);

        model.addAttribute("departmentDetail", s);
        return findPaginated(pageNo, model);
    }

    @PostMapping("/departmentsManagement/editDepartment")
    public String editService(Model model,
            @RequestParam("Id") int id,
            @RequestParam("name") String name,
            @RequestParam("note") String description) {

        ServiceCategory s = service.findById(id);
        s.setName(name);
        s.setNote(description);
        service.save(s);
        return "redirect:/departmentsManagement/editSuccess/" + id;
    }

    @GetMapping("/departmentsManagement/editSuccess/{id}")
    public String editSuccess(Model model, @PathVariable("id") int id) {

        model.addAttribute("message", "Department " + id + " is up to date!");
        return findPaginated(1, model);
    }

}