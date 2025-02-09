package spring_boot_security.Spring.Boot.Security.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring_boot_security.Spring.Boot.Security.dtos.UsersDTO;
import spring_boot_security.Spring.Boot.Security.entities.Users;
import spring_boot_security.Spring.Boot.Security.exceptions.ResourceNotFoundException;
import spring_boot_security.Spring.Boot.Security.repositories.UserRepo;
import spring_boot_security.Spring.Boot.Security.services.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    @Override
    public UsersDTO registerNewUSer(UsersDTO userDto) {
        Users user = this.modelMapper.map(userDto, Users.class);
        user.setPassword(encoder.encode(user.getPassword()));
        Users newUser = this.userRepo.save(user);
        return this.modelMapper.map(newUser, UsersDTO.class);
    }

    @Override
    public UsersDTO createUser(UsersDTO user) {
        Users users = this.modelMapper.map(user, Users.class);
        Users newUser = this.userRepo.save(users);
        return this.modelMapper.map(newUser, UsersDTO.class);
    }

    @Override
    public UsersDTO updateUser(UsersDTO user, Integer userId) {
        Users users = this.userRepo.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", " id", userId));
        users.setName(user.getName());
        users.setEmail(user.getEmail());
        users.setPassword(encoder.encode(user.getPassword()));
        Users updatedUser = this.userRepo.save(users);
        return this.modelMapper.map(updatedUser, UsersDTO.class);
    }

    @Override
    public UsersDTO getUsersDetail(Integer userId) {
        Users users = this.userRepo.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", " id", userId));
        return this.modelMapper.map(users, UsersDTO.class);
    }

    @Override
    public List<UsersDTO> getAllUsers() {
        List<Users> users = this.userRepo.findAll();
        List<UsersDTO> usersDTOS = users.stream()
                .map(user -> this.modelMapper.map(user, UsersDTO.class)).collect(Collectors.toList());
        return usersDTOS;
    }

    @Override
    public void DeleteUser(Integer userId) {
        Users users = this.userRepo.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", " id", userId));
        this.userRepo.delete(users);
    }
}