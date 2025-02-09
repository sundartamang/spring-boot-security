package spring_boot_security.Spring.Boot.Security.payloads;

import lombok.Data;

@Data
public class JwtAuthRequest {
    private String username;
    private String password;
}
