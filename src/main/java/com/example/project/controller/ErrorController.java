package com.example.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ErrorController {
    @GetMapping("/error")
    public ModelAndView handleError(HttpServletRequest request,@RequestParam(value = "continue",required = false) String continueUrl) {
        ModelAndView modelAndView = new ModelAndView();
        if (continueUrl != null && !continueUrl.isEmpty()) {
            // Nếu có tham số "continue" trong URL, chuyển hướng đến URL đã chỉ định
            System.out.println("continueUrl: " + continueUrl);
            modelAndView.setViewName("redirect:" + continueUrl);
        } else {
            // Nếu không có tham số "continue", xem xét chuyển hướng đến trang lỗi mặc định
            String referer = request.getHeader("Referer");
            if (referer != null) {
                modelAndView.setViewName("redirect:" + referer);
            } else {
                // Nếu không có trang trang liên kết trước đó, chuyển hướng đến trang lỗi mặc định
                modelAndView.setViewName("denied"); // Thay thế "error-page" bằng tên trang lỗi của bạn
            }
        }
        return modelAndView;
    }
    
}
