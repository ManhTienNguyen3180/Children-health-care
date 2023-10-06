package com.example.project.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * AdminInvoice
 */

@Controller
@RequestMapping("admin/invoice-list")
public class AdminInvoiceList {

  @GetMapping
  public String page() {
    return "admin/invoice-list";
  }
}
