package spring_boot_security.Spring.Boot.Security.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring_boot_security.Spring.Boot.Security.dtos.UsersDTO;
import spring_boot_security.Spring.Boot.Security.services.UserService;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UsersDTO> registerUser(@RequestBody UsersDTO userDto){
        System.out.println("******************************************");
        System.out.println("Data i get is====== ============   "+userDto);
        System.out.println("******************************************");
        UsersDTO registerNewUSer = this.userService.registerNewUSer(userDto);
        return new ResponseEntity<UsersDTO>(registerNewUSer, HttpStatus.CREATED);
    }
}
