package com.example.project.controller;

import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;

import com.example.project.entity.user;
import com.example.project.service.BlogService;
import com.example.project.service.DoctorService;
import com.example.project.service.ServiceService;

@Controller
public class HomeController {
    
    
    @Autowired 
    private DoctorService DoctorService;
    @Autowired 
    private ServiceService ServiceService;
    @Autowired 
    private BlogService BlogService;

    @Autowired
    private com.example.project.service.UserService userService;

    @GetMapping("/")
    public String home1(){
        return "home";
    }
    @GetMapping("/user-profile")
    public String userinfor(){
        return "user-profile";
    }
    
    @GetMapping("/home")
    public String home(Model model){
        model.addAttribute("doctor", DoctorService.fetchDoctorList());
        model.addAttribute("service", ServiceService.fechServicesList());
        model.addAttribute("blogNew", BlogService.getBlogsNew());
        return "home";
    }
    
    
    
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/";
    }

    //generate postmapping to save image into folder and save image path into database
    @PostMapping("/upload")
    public String saveFile(@RequestParam("file") MultipartFile file, HttpSession session) {
        // We can save image in 'images' directory in roo
        String uploadDir = "./src/main/resources/static/images";

        try {
            java.nio.file.Path copyLocation = Paths
                .get(uploadDir + java.io.File.separator + file.getOriginalFilename());
            java.nio.file.Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
            user  user = (user) session.getAttribute("user");
            user.setImage("images" + "/" + file.getOriginalFilename());
            userService.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("message", "Fail to upload " + file.getOriginalFilename() + "!");
        }
         
        return "redirect:/user-profile";
    }    
    



    
    


    
    

    


            

                





    
        
    
}
