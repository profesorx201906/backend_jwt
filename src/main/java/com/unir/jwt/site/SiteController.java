package com.unir.jwt.site;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/site")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class SiteController {

  @GetMapping
  @PreAuthorize("hasRole('ADMIN')")
  public String saludar()
  {
    return "hello world";
  }

}
