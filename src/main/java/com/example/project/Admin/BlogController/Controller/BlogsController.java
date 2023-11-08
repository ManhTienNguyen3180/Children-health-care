package com.example.project.Admin.BlogController.Controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    private BlogCategoryService blogCategoryService;

    // When you first access to Blogs Management Page
    @GetMapping(value = "/blogsManagement")
    public String listAll(Model model) {
        return findPaginated(1, model);
    }

    // Requets to saving Blogs
    @PostMapping(value = "/blogsManagement/addBlog")
    public String addBlog(Model model,
            @RequestParam("title") String title,
            @RequestParam("image") MultipartFile image,
            @RequestParam("category") int category,
            @RequestParam("description") String description,
            @RequestParam("content") String content,
            @RequestParam("author") String author) {
        // Add Blog
        // try {
        // Get the current date
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        String toDay = date.toString();

        // Get default author
        String authorB;
        if (author.equalsIgnoreCase("")) {
            authorB = "Admin";
        } else {
            authorB = author;
        }
        // Need to add image here still wrong

        String imageAddress = "";
        if (image.isEmpty()) {
            imageAddress = "/images/blog/default-blog.png";
        } else {
            try {
                // We can save image in 'images' directory in roo

                String uploadDir = "./src/main/resources/static/images/blog";
                java.nio.file.Path copyLocation = Paths
                        .get(uploadDir + java.io.File.separator + image.getOriginalFilename());
                java.nio.file.Files.copy(image.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
                imageAddress = "/images/blog" + "/" + image.getOriginalFilename();
            } catch (Exception e) {
                imageAddress = "/images/blog" + "/" + image.getOriginalFilename();
            }

        }
        // =========
 
        // Save Blog
        blogService.addBlog(category, title, toDay, description, 1, authorB, imageAddress, content, toDay);
        // } catch (Exception e) {
        // // Send error to Manage Dashboard
        // model.addAttribute("message", "System Error Try Again ");
        // model.addAttribute("blogsCategory", blogCategoryService.findAll());
        // model.addAttribute("blogs", blogService.findAll());
        // return findPaginated(1, model);
        // }

        // View Blogs List
        model.addAttribute("blogs", blogService.findAll());
        model.addAttribute("message", "Add Blog Successfully!");
        model.addAttribute("blogsCategory", blogCategoryService.findAll());

        return "redirect:/blogsManagement/blogList";
    }

    // Paginated Page
    @GetMapping("/blogsManagement/page/{pageNo}")
    public String findPaginated(@PathVariable int pageNo,
            Model model) {

        int pageSize = 5;

        Page<Blog> page = blogService.findPaginated(pageNo, pageSize);
        List<Blog> listB = page.getContent();

        model.addAttribute("blogsCategory", blogCategoryService.findAll());

        model.addAttribute("blogs", listB);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());

        return "admin/blogs";
    }

    @GetMapping(value = "/blogsManagement/search")
    public String search(@RequestParam("searchText") String search, Model model) {
        if (search.trim() != "") {
            return searchandPaginated(1, search, model);
        } else {
            return findPaginated(1, model);
        }
    }

    @GetMapping("/blogsManagement/filterCategory/{id}")
    public String filterCategory(Model model, @PathVariable("id") int id) {
        return filterCategoryAndPaginated(model, id, 1);
    }

    @GetMapping("/blogsManagement/filterCategory/{id}/{pageNo}")
    public String filterCategoryAndPaginated(Model model, @PathVariable("id") int id,
            @PathVariable(value = "pageNo") int pageNo) {
        Page<Blog> page = blogService.filterCategory(id, pageNo, 5);
        List<Blog> listB = page.getContent();

        model.addAttribute("catId", id);

        model.addAttribute("blogsCategory", blogCategoryService.findAll());

        model.addAttribute("blogs", listB);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        return "admin/blogs";
    }

    @GetMapping("/blogsManagement/filterStatus/{id}")
    public String filterStatus(Model model, @PathVariable("id") int id) {
        return filterStatusAndPaginated(model, id, 1);
    }

    @GetMapping("/blogsManagement/filterStatus/{id}/{pageNo}")
    public String filterStatusAndPaginated(Model model, @PathVariable("id") int id,
            @PathVariable(value = "pageNo") int pageNo) {
        Page<Blog> page = blogService.filterStatus(id, pageNo, 5);
        List<Blog> listB = page.getContent();

        model.addAttribute("blogsCategory", blogCategoryService.findAll());

        model.addAttribute("status", id);

        model.addAttribute("blogs", listB);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        return "admin/blogs";
    }

    @GetMapping(value = "/blogsManagement/page/{pageNo}/{searchText}")
    public String searchandPaginated(@PathVariable("pageNo") int pageNo,
            @PathVariable("searchText") String search,
            Model model) {
        Page<Blog> blogPage = blogService.searchbyTitleAuthorordescription(search.trim(), pageNo, 5);
        List<Blog> listB = blogPage.getContent();

        model.addAttribute("searchText", search);
        model.addAttribute("blogsCategory", blogCategoryService.findAll());

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", blogPage.getTotalPages());

        model.addAttribute("blogs", listB);
        return "admin/blogs";
    }

    @GetMapping(value = "/blogsManagement/blog-detail/{id}")
    public String blogsDetail(Model model, @PathVariable int id) {
        Blog blog = blogService.findByID(id);

        model.addAttribute("blogsCategory", blogCategoryService.findAll());
        model.addAttribute("blogDetail", blog);
        return "admin/blogs-detail";
    }

    @PostMapping(value = "blogsManagement/changeStatus")
    public String changeStatus(Model model, @RequestParam("blogID") int id) {
        Blog blog = blogService.findByID(id);
        int status = blog.getStatus();
        if (status == 1) {
            status = 0;
        } else
            status = 1;
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        String toDay = date.toString();
        blogService.saveStatus(id, toDay, status);
        return "redirect:/blogsManagement/blogs/" + id;
    }

    // Requets to saving Blogs
    @PostMapping(value = "/blogsManagement/edit")
    public String editBlog(Model model,
            @RequestParam("blogId") int id,
            @RequestParam("title") String title,
            @RequestParam("image") MultipartFile image,
            @RequestParam("category") int category,
            @RequestParam("description") String description,
            @RequestParam("content") String content,
            @RequestParam("author") String author) {
        // Add Blog

        // Get the current date
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        String imageAddress = "";
        if (image.isEmpty()) {
            Blog b = blogService.findByID(id);
            imageAddress = b.getImage();
        } else {
            try {
                // We can save image in 'images' directory in roo

                String uploadDir = "./src/main/resources/static/images/blog";
                java.nio.file.Path copyLocation = Paths
                        .get(uploadDir + java.io.File.separator + image.getOriginalFilename());
                java.nio.file.Files.copy(image.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
                imageAddress = "/images/blog" + "/" + image.getOriginalFilename();
            } catch (Exception e) {
                imageAddress = "/images/blog" + "/" + image.getOriginalFilename();
            }
        }
        Blog blog = blogService.findByID(id);
        blog.setTitle(title);
        blog.setImage(imageAddress);
        blog.setCategoryBlogId(category);
        blog.setDescription(description);
        blog.setContent(content);
        blog.setAuthor(author);
        blog.setUpdateDate(date);
        // Save Blog
        blogService.saveBlogChanges(blog);

        return "redirect:/blogsManagement/blogsu/" + id;
    }

    // VIEW
    @RequestMapping(value = "/blogsManagement/blogList", method = { RequestMethod.GET })
    public String afterAdd(Model model) {
        model.addAttribute("message", "Add Blog Successfully!");
        return findPaginated(1, model);
    }

    @RequestMapping(value = "/blogsManagement/blogs/{id}", method = { RequestMethod.GET })
    public String afterChangeStatus(Model model, @PathVariable("id") int id) {

        Blog blog = blogService.findByID(id);
        model.addAttribute("blogsCategory", blogCategoryService.findAll());

        model.addAttribute("blogsCategory", blogCategoryService.findAll());
        model.addAttribute("blogDetail", blog);
        model.addAttribute("message", "Change Status Successfully!");
        return "admin/blogs-detail";
    }

    @RequestMapping(value = "/blogsManagement/blogsu/{id}", method = { RequestMethod.GET })
    public String afterUpdate(Model model, @PathVariable("id") int id) {

        Blog blog = blogService.findByID(id);

        model.addAttribute("blogsCategory", blogCategoryService.findAll());
        model.addAttribute("blogDetail", blog);
        model.addAttribute("message", "Update Successfully!");
        return "admin/blogs-detail";
    }

}
