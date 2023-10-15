package com.example.project.Admin.BlogController.Controller;

import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.project.Admin.BlogController.Model.Blog;
import com.example.project.Admin.BlogController.Service.BlogsService;
import com.example.project.Admin.CategoryController.Service.BlogCategoryService;

@Controller
public class BlogsController {

    @Autowired
    private BlogsService blogService;

    @Autowired
    BlogCategoryService blogCategoryService;

    // When you first access to Blogs Management Page
    @GetMapping(value = "/blogsManagement")
    public String listAll(Model model) {
        List<Blog> listB = blogService.findAll();
        model.addAttribute("blogsCategory", blogCategoryService.findAll());
        model.addAttribute("blogs", listB);
        return "admin/blogs";
    }

    // Requets to saving Blogs
    @RequestMapping(value = "/blogsManagerment/addBlog")
    public String addBlog(Model model,
            @RequestParam("title") String title,
            @RequestParam("image") MultipartFile image,
            @RequestParam("category") int category,
            @RequestParam("description") String description,
            @RequestParam("content") String content,
            @RequestParam("author") String author) {
        // Add Blog
        try {
            // Get the current date
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            String toDay = date.toString();

            // Get default author
            String authorB;
            if (author == "") {
                authorB = "Admin";
            } else {
                authorB = author;
            }
            // Need to add image here

            String imageAddress;
            if (image.isEmpty()) {
                imageAddress = "/images/blog/default-blog.png";
            } else {
                // We can save image in 'images' directory in roo
                String uploadDir = "./src/main/resources/static/images/blog";
                try {
                    java.nio.file.Path copyLocation = Paths
                            .get(uploadDir + java.io.File.separator + image.getOriginalFilename());
                    java.nio.file.Files.copy(image.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);

                    imageAddress = "/images/blog" + "/" + image.getOriginalFilename();

                } catch (Exception e) {
                    e.printStackTrace();
                    imageAddress = "/images/blog/default-blog.png";
                }

            }
            // =========

            // Save Blog
            blogService.addBlog(category, title, toDay, description, 1, authorB, imageAddress, content, toDay);
        } catch (Exception e) {
            // Send error to Manage Dashboard
            model.addAttribute("message", "System Error Try Again ");
            model.addAttribute("blogsCategory", blogCategoryService.findAll());
            model.addAttribute("blogs", blogService.findAll());
            return "blogs";
        }

        // View Blogs List
        model.addAttribute("blogs", blogService.findAll());
        model.addAttribute("message", "Add Blog Successfully!");
        model.addAttribute("blogsCategory", blogCategoryService.findAll());

        return "admin/blogs";
    }

    @GetMapping(value = "/blogsManagement/edit/{id}")
    public String blogsDetail(Model model) {
        return "admin/blogs-detail";
    }

}
