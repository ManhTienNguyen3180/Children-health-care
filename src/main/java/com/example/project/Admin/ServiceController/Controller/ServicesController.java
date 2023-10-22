package com.example.project.Admin.ServiceController.Controller;

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

import com.example.project.Admin.ServiceCategoryController.Service.ServiceCategoryService;
import com.example.project.Admin.ServiceController.Model.Service;
import com.example.project.Admin.ServiceController.Repository.ServiceRepository;
import com.example.project.Admin.ServiceController.Service.ServiceService;

@Controller
public class ServicesController {
    @Autowired
    ServiceService service;

    @Autowired
    ServiceCategoryService serviceCategory;

    @Autowired
    ServiceRepository repo;

    @GetMapping("/servicesManagement/page/{pageNo}")
    public String findPaginated(@PathVariable int pageNo,
            Model model) {

        int pageSize = 5;

        Page<Service> page = service.findPaginated(pageNo, pageSize);
        List<Service> listS = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("servicesList", listS);

        model.addAttribute("listCate", serviceCategory.findAll());

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalService", service.listServices().size());

        return "admin/services";
    }

    @PostMapping("/servicesManagement/addService")
    public String addService(Model model,
            @RequestParam("name") String name,
            @RequestParam("price") int price,
            @RequestParam("description") String description,
            @RequestParam("category") int category,
            @RequestParam("createBy") String createBy,
            @RequestParam("image") MultipartFile image) {
        String imageAddress = "";
        if (image.isEmpty()) {
            imageAddress = "/images/service/default-service.jpg";
        } else {
            try {
                // We can save image in 'images' directory in roo

                String uploadDir = "./src/main/resources/static/images/service";
                java.nio.file.Path copyLocation = Paths
                        .get(uploadDir + java.io.File.separator + image.getOriginalFilename());
                java.nio.file.Files.copy(image.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
                imageAddress = "/images/service" + "/" + image.getOriginalFilename();
            } catch (Exception e) {
                return "redirect:/servicesManagement/addFail";
            }

        }
        if (createBy.isBlank()) {
            createBy = "Admin";
        }
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        service.addService(new Service(name, price, description, imageAddress, category, 1, date, createBy));
        return "redirect:/servicesManagement/addSuccess";
    }

    @PostMapping("/servicesManagement/editService")
    public String editService(Model model,
            @RequestParam("Id") int id,
            @RequestParam("name") String name,
            @RequestParam("price") int price,
            @RequestParam("description") String description,
            @RequestParam("category") int category,
            @RequestParam("createBy") String createBy,
            @RequestParam("image") MultipartFile image) {
        String imageAddress = "";
        if (image.isEmpty()) {
            Service s = service.findById(id);
            imageAddress = s.getImage();
        } else {
            try {
                // We can save image in 'images' directory in roo

                String uploadDir = "./src/main/resources/static/images/service";
                java.nio.file.Path copyLocation = Paths
                        .get(uploadDir + java.io.File.separator + image.getOriginalFilename());
                java.nio.file.Files.copy(image.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
                imageAddress = "/images/service" + "/" + image.getOriginalFilename();
            } catch (Exception e) {
                return "redirect:/servicesManagement/addFail";
            }

        }

        if (createBy.isBlank()) {
            createBy = "Admin";
        }
        Service s = service.findById(id);
        s.setName(name);
        s.setPrice(price);
        s.setDescription(description);
        s.setCategoryId(category);
        s.setCreateBy(createBy);
        s.setImage(imageAddress);
        service.save(s);
        return "redirect:/servicesManagement/editSuccess/" + id;
    }

    @GetMapping("/servicesManagement/filterCategory/{id}")
    public String filterCategory(Model model, @PathVariable("id") int id) {
        return filterCategoryAndPaginated(model, id, 1);
    }

    @GetMapping("/servicesManagement/filterCategory/{id}/{pageNo}")
    public String filterCategoryAndPaginated(Model model, @PathVariable("id") int id,
            @PathVariable(value = "pageNo") int pageNo) {
        Page<Service> page = service.filterCategory(id, pageNo, 5);
        List<Service> listS = page.getContent();

        model.addAttribute("servicesList", listS);
        model.addAttribute("listCate", serviceCategory.findAll());

        model.addAttribute("catId", id);

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalService", page.getTotalElements());
        return "admin/services";
    }

    @GetMapping("/servicesManagement/filterStatus/{id}")
    public String filterStatus(Model model, @PathVariable("id") int id) {
        return filterStatusAndPaginated(model, id, 1);
    }

    @GetMapping("/servicesManagement/filterStatus/{id}/{pageNo}")
    public String filterStatusAndPaginated(Model model, @PathVariable("id") int id,
            @PathVariable(value = "pageNo") int pageNo) {
        Page<Service> page = service.filterStatus(id, pageNo, 5);
        List<Service> listS = page.getContent();

        model.addAttribute("servicesList", listS);
        model.addAttribute("listCate", serviceCategory.findAll());

        model.addAttribute("status", id);

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalService", page.getTotalElements());
        return "admin/services";
    }

    @GetMapping("/servicesManagement/editSuccess/{id}")
    public String editSuccess(Model model, @PathVariable("id") int id) {
        Service s = repo.findById(id).get();

        model.addAttribute("message", "Service " + id + " is up to date!");
        model.addAttribute("listCate", serviceCategory.findAll());
        model.addAttribute("serviceDetail", s);
        return "admin/service-detail";
    }

    @PostMapping("/servicesManagement/changeStatus")
    public String changeStatus(@RequestParam("Id") int id,
            @RequestParam("pageNo") int pageNo, Model model) {
        Service s = service.findById(id);
        if (s.getStatus() == 1)
            s.setStatus(0);
        else
            s.setStatus(1);
        service.save(s);
        model.addAttribute("message", "Change status of service " + id + " successfully!");
        return findPaginated(pageNo, model);
    }

    @PostMapping("/servicesManagement/changeStatusDetails")
    public String changeStatusDetails(@RequestParam("Id") int id, Model model) {
        Service s = service.findById(id);
        if (s.getStatus() == 1)
            s.setStatus(0);
        else
            s.setStatus(1);
        service.save(s);
        return "redirect:/servicesManagement/service-udetail/" + id;
    }

    @GetMapping("/servicesManagement/service-udetail/{id}")
    public String getServiceDetailu(Model model, @PathVariable("id") int id) {
        Service s = repo.findById(id).get();

        model.addAttribute("message", "Change status of service " + id + " successfully!");
        model.addAttribute("listCate", serviceCategory.findAll());
        model.addAttribute("serviceDetail", s);
        return "admin/service-detail";
    }

    @GetMapping("/servicesManagement/service-detail/{id}")
    public String getServiceDetail(Model model, @PathVariable("id") int id) {
        Service s = repo.findById(id).get();

        model.addAttribute("listCate", serviceCategory.findAll());
        model.addAttribute("serviceDetail", s);
        return "admin/service-detail";
    }

    @GetMapping("/servicesManagement/sort/")
    public String sort(
            @RequestParam("sortField") String sortField,
            @RequestParam("sortDir") String sortDir,
            Model model) {
        return sortadnPaginate(1, sortField, sortDir, model);
    }

    @GetMapping("/servicesManagement/sort/{pageNo}")
    public String sortadnPaginate(@PathVariable int pageNo,
            @RequestParam("sortField") String sortField,
            @RequestParam("sortDir") String sortDir,
            Model model) {
        int pageSize = 5;
        Page<Service> page = service.sortandPaginate(pageNo, pageSize, sortField, sortDir);
        List<Service> listS = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("servicesList", listS);
        
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);

        model.addAttribute("listCate", serviceCategory.findAll());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalService", service.listServices().size());
        return "admin/services";
    }

    @GetMapping(value = "/servicesManagement/search")
    public String search(@RequestParam("searchText") String search, Model model) {
        model.addAttribute("searchText", search);
        return searchandPaginated(1, search.trim(), model);
    }

    @GetMapping(value = "/servicesManagement/page/{pageNo}/{searchText}")
    public String searchandPaginated(@PathVariable("pageNo") int pageNo,
            @PathVariable("searchText") String search,
            Model model) {
        Page<Service> page;
        if (search == "")
            return "redirect:/servicesManagement/firstView";
        else
            page = service.searchbyNamePriceDescription(search, pageNo, 5);

        List<Service> listS = page.getContent();

        model.addAttribute("searchText", search);
        model.addAttribute("servicesList", listS);
        model.addAttribute("listCate", serviceCategory.findAll());

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalService", page.getTotalElements());
        return "admin/services";
    }

    @GetMapping("/servicesManagement")
    public String getserviceList() {
        return "redirect:/servicesManagement/firstView";
    }

    @GetMapping("/servicesManagement/addSuccess")
    public String success(Model mode) {
        mode.addAttribute("message", "Add Service Successfully!");
        return findPaginated(1, mode);
    }

    @GetMapping("/servicesManagement/addFail")
    public String fail(Model mode) {
        mode.addAttribute("message", "Add Service Fail!");
        return findPaginated(1, mode);
    }

    @GetMapping("/servicesManagement/firstView")
    public String view(Model mode) {
        return findPaginated(1, mode);

    }
}
