package com.example.project.Admin.SliderController.Controller;

import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
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
import com.example.project.Admin.SliderController.Model.Slider;
import com.example.project.Admin.SliderController.Service.SliderService;

@Controller
public class SliderController {

    @Autowired
    SliderService serviceS;
    @Autowired
    ServiceCategoryService serviceCategory;

    @GetMapping("/slidersManagement")
    public String getserviceList(Model model) {
        return findPaginated(1, model);
    }

    @PostMapping("/slidersManagement/saveSlider")
    public String saveSlider(Model model,
            @RequestParam("id") int id,
            @RequestParam("title") String title,
            @RequestParam("backlink") String backlink,
            @RequestParam("image") MultipartFile image) {
        String imageAddress = "";
        if (image.isEmpty()) {
            Slider slider = serviceS.findById(id);
            imageAddress = slider.getImage();
        } else {
            try {
                // We can save image in 'images' directory in roo

                String uploadDir = "./src/main/resources/static/images/slider";
                java.nio.file.Path copyLocation = Paths
                        .get(uploadDir + java.io.File.separator + image.getOriginalFilename());
                java.nio.file.Files.copy(image.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
                imageAddress = "/images/slider" + "/" + image.getOriginalFilename();
            } catch (Exception e) {
                return "redirect:/slidersManagement/addFail";
            }
        } 
        Slider slider = serviceS.findById(id);
        slider.setTitle(title);
        slider.setBacklink(backlink);
        slider.setImage(imageAddress);
        serviceS.save(slider);
        return "redirect:/slidersManagement/editSuccess/" + id;
    }

    @GetMapping("/slidersManagement/editSuccess/{id}")
    public String editSuccess(Model model, @PathVariable("id") int id) {
        Slider slider = serviceS.findById(id);
        model.addAttribute("message", "Update slider " + id + " successfully!");
        model.addAttribute("sliderDetail", slider);
        return "admin/slider-detail";
    }

    @PostMapping("/slidersManagement/addSlider")
    public String addSlider(Model model,
            @RequestParam("title") String title,
            @RequestParam("backlink") String backlink,
            @RequestParam("image") MultipartFile image) {
        String imageAddress = "";
        if (image.isEmpty()) {
            imageAddress = "/images/service/default-service.jpg";
        } else {
            try {
                // We can save image in 'images' directory in roo

                String uploadDir = "./src/main/resources/static/images/slider";
                java.nio.file.Path copyLocation = Paths
                        .get(uploadDir + java.io.File.separator + image.getOriginalFilename());
                java.nio.file.Files.copy(image.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
                imageAddress = "/images/slider" + "/" + image.getOriginalFilename();
            } catch (Exception e) {
                imageAddress = "/images/slider" + "/" + image.getOriginalFilename();
            }
        }
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        serviceS.addService(new Slider(title, imageAddress, backlink, 0, date, 0));
        return "redirect:/slidersManagement/addSuccess";
    }

    @GetMapping("/slidersManagement/addSuccess")
    public String success(Model mode) {
        mode.addAttribute("message", "Add Slider Successfully!");
        return findPaginated(1, mode);
    }

    @GetMapping("/slidersManagement/addFail")
    public String fail(Model mode) {
        mode.addAttribute("message", "Add Service Fail!");
        return findPaginated(1, mode);
    }

    @GetMapping("/slidersManagement/slider-detail/{id}")
    public String sliderDetail(Model model, @PathVariable("id") int id) {
        Slider slider = serviceS.findById(id);
        model.addAttribute("sliderDetail", slider);
        return "admin/slider-detail";
    }

    @PostMapping("/slidersManagement/position")
    public String position(Model model, @RequestParam("list") Integer[] sliderIds) {
        List<Slider> listS = new ArrayList<>();
        List<Slider> total = serviceS.findAll();
        for (Slider slider : total) {
            if (!listS.contains(slider)) {
                slider.setPosition(0);
                slider.setStatus(0);
                serviceS.save(slider);
            }
        }
        int i = 1;
        for (Integer sliderid : sliderIds) {
            Slider slider = serviceS.findById(sliderid);
            slider.setPosition(i);
            slider.setStatus(1);
            serviceS.save(slider);
            i++;
        }

        return "redirect:/sliders/Management/successPos";
    }

    @GetMapping("/sliders/Management/successPos")
    public String succesPos(Model model) {
        model.addAttribute("message", "Change position successfully!");
        return findPaginated(1, model);
    }

    @GetMapping("/slidersManagement/page/{pageNo}")
    private String findPaginated(@PathVariable int pageNo, Model model) {
        int pageSize = 5;
        Page<Slider> page = serviceS.findPaginated(pageNo, pageSize);
        List<Slider> listS = page.getContent();
        model.addAttribute("slidersList", listS);

        List<Slider> previewList = serviceS.findPreviewSlider();
        model.addAttribute("previewSlider", previewList);
        List<Slider> hiddenList = serviceS.findHiddenSlider();
        model.addAttribute("hiddenList", hiddenList);

        model.addAttribute("allSliders", serviceS.findAll());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalService", page.getTotalElements());
        return "admin/sliders";
    }

}
