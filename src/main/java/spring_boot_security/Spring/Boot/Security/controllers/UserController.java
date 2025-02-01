package spring_boot_security.Spring.Boot.Security.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/")
public class UserController {

    @GetMapping("/")
    public String greeting(HttpServletRequest request){
        return "Welcome to spring boot " + request.getRequestId();
    }
}
