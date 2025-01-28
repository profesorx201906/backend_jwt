package com.unir.jwt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unir.jwt.services.TestService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
  @Autowired
  TestService testService;

  @GetMapping("/all")
  public String allAccess() {
    return testService.allAccess();
  }

  @GetMapping("/user")
  public String userAccess() {
    return testService.userAccess();
  }

  @GetMapping("/mod")
  public String moderatorAccess() {
    return testService.moderatorAccess();
  }

  @GetMapping("/admin")
  public String adminAccess() {
    return testService.adminAccess();
  }

  @GetMapping("/delete")
  public String deleteAccess() {
    return testService.adminAccess();
  }
}
