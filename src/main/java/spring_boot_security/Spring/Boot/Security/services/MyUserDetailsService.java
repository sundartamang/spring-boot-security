package spring_boot_security.Spring.Boot.Security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spring_boot_security.Spring.Boot.Security.entities.Users;
import spring_boot_security.Spring.Boot.Security.exceptions.ResourceNotFoundException;
import spring_boot_security.Spring.Boot.Security.repositories.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = this.userRepo.findByEmail(username).orElseThrow(
                () -> new ResourceNotFoundException("User", "email", username));

        return user;
    }
}

