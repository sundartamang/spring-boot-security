package spring_boot_security.Spring.Boot.Security.controllers;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring_boot_security.Spring.Boot.Security.dtos.UsersDTO;
import spring_boot_security.Spring.Boot.Security.entities.Users;
import spring_boot_security.Spring.Boot.Security.exceptions.ApiException;
import spring_boot_security.Spring.Boot.Security.payloads.JwtAuthRequest;
import spring_boot_security.Spring.Boot.Security.payloads.JwtAuthResponse;
import spring_boot_security.Spring.Boot.Security.repositories.UserRepo;
import spring_boot_security.Spring.Boot.Security.services.AuthService;
import spring_boot_security.Spring.Boot.Security.services.JwtTokenService;
import spring_boot_security.Spring.Boot.Security.services.UserService;
import org.springframework.security.authentication.BadCredentialsException;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UsersDTO> registerUser(@RequestBody UsersDTO userDto){
        UsersDTO registerNewUSer = this.userService.registerNewUSer(userDto);
        return new ResponseEntity<UsersDTO>(registerNewUSer, HttpStatus.CREATED);

    }

    @PostMapping("/user-login")
    public ResponseEntity<JwtAuthResponse> createToken(
            @RequestBody JwtAuthRequest jwtAuthRequest) throws Exception{
        JwtAuthResponse jwtAuthResponse = this.authService.authenticateUser(jwtAuthRequest);
        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }

}
