package com.example.project.controller;

import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;

import com.example.project.Admin.SliderController.Model.Slider;
import com.example.project.Admin.SliderController.Service.SliderService;
import com.example.project.entity.user;
import com.example.project.service.BlogService;
import com.example.project.service.DoctorService;
import com.example.project.service.FeedbackService;
import com.example.project.service.ServiceService;

@Controller
public class HomeController {
    @Autowired 
    private FeedbackService FeedbackService;
    @Autowired
    private DoctorService DoctorService;
    @Autowired
    private ServiceService ServiceService;
    @Autowired
    private BlogService BlogService;

    @Autowired
    SliderService serviceS;
    @Autowired
    private com.example.project.service.UserService userService;

    // @GetMapping("/")
    // public String home1() {
    // return "home";
    // }

    @GetMapping("/user-profile")
    public String userinfor() {
        return "user-profile";
    }

    @ModelAttribute
    public void addAttributes(Model model, Principal principal, HttpSession session) {
        if (principal != null) {
            String email = principal.getName();
            user user = userService.findUserByEmail(email);
            model.addAttribute("user", user);
            session.setAttribute("user", user);
        }

    }

    @GetMapping("/home")
    public String home(Authentication authentication, Model model, HttpSession session) {
        user u = (user) session.getAttribute("user");
        try {
            if (u.getStatus() == 0) {
               
                if (authentication != null) {
                    authentication.setAuthenticated(false);
                    session.removeAttribute("user");
                     model.addAttribute("mess", "User not Active");
                    return "/login";
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        model.addAttribute("rv", FeedbackService.getFeedbackHome(5));
        model.addAttribute("u", u);
        model.addAttribute("doctor", DoctorService.fetchDoctorList());
        model.addAttribute("service", ServiceService.fechServicesList());
        model.addAttribute("blogNew", BlogService.getBlogsNew());
        
        List<Slider> previewList = serviceS.findPreviewSlider();
        model.addAttribute("previewSlider", previewList);
        return "home";
    }
    

    @GetMapping("/logout")
    public String logout(Authentication authentication, HttpSession session) {
        if (authentication != null) {
            authentication.setAuthenticated(false);
            session.removeAttribute("user");
        }

        return "redirect:/home";
    }

    @GetMapping("/denied")
    public String denied() {
        return "denied";
    }

    // generate postmapping to save image into folder and save image path into
    // database
    @PostMapping("/upload")
    public String saveFile(@RequestParam("file") MultipartFile file, HttpSession session) {
        // We can save image in 'images' directory in roo
        String uploadDir = "./src/main/resources/static/images";

        try {
            java.nio.file.Path copyLocation = Paths
                    .get(uploadDir + java.io.File.separator + file.getOriginalFilename());
            java.nio.file.Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);

            // if (user.getImage() != null) {
            // java.io.File oldFile = new java.io.File(
            // uploadDir + java.io.File.separator + getImageName(user.getImage()));
            // oldFile.delete();
            // }
            user user = (user) session.getAttribute("user");
            user.setImage("images" + "/" + file.getOriginalFilename());
            userService.saveImage(user);

        } catch (Exception e) {
            e.printStackTrace();

        }

        return "redirect:/user-profile";
    }

    public String getImageName(String name) {
        String[] arr = name.split("/");
        return arr[arr.length - 1];
    }

}
