package spring_boot_security.Spring.Boot.Security.payloads;

import lombok.Data;
import spring_boot_security.Spring.Boot.Security.dtos.UsersDTO;

@Data
public class JwtAuthResponse {
    private String token;
    private UsersDTO usersDTO;
}
