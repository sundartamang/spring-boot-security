package spring_boot_security.Spring.Boot.Security.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import spring_boot_security.Spring.Boot.Security.dtos.UsersDTO;
import spring_boot_security.Spring.Boot.Security.entities.Users;
import spring_boot_security.Spring.Boot.Security.exceptions.ApiException;
import spring_boot_security.Spring.Boot.Security.payloads.JwtAuthRequest;
import spring_boot_security.Spring.Boot.Security.payloads.JwtAuthResponse;
import spring_boot_security.Spring.Boot.Security.repositories.UserRepo;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    public JwtAuthResponse authenticateUser(JwtAuthRequest jwtAuthRequest) throws Exception {

        this.authenticate(jwtAuthRequest.getUsername(), jwtAuthRequest.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtAuthRequest.getUsername());
        String token = jwtTokenService.generateToken(userDetails);

        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(token);

        Optional<Users> user = userRepo.findByEmail(jwtAuthRequest.getUsername());
        UsersDTO userDto = modelMapper.map(user, UsersDTO.class);
        response.setUsersDTO(userDto);

        return response;
    }

    private void authenticate(String username, String password) throws Exception {
        System.out.println("username is " + username);
        System.out.println("Password is "+ password);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            System.out.println(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password)));
        } catch (BadCredentialsException e) {
            throw new ApiException("Invalid username or password");
        }
    }
}
