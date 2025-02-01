package spring_boot_security.Spring.Boot.Security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_boot_security.Spring.Boot.Security.entities.Users;

import java.util.Optional;

public interface UserRepo extends JpaRepository<Users, Integer> {
    Optional<Users> findByEmail(String email);
}
