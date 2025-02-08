package spring_boot_security.Spring.Boot.Security.services;

import spring_boot_security.Spring.Boot.Security.dtos.UsersDTO;

import java.util.List;

public interface UserService {
    UsersDTO registerNewUSer(UsersDTO user);
    UsersDTO createUser(UsersDTO user);
    UsersDTO updateUser(UsersDTO user, Integer userId);
    UsersDTO getUsersDetail(Integer userId);
    List<UsersDTO> getAllUsers();
    void DeleteUser(Integer userId);
}
