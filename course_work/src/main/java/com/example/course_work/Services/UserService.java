package com.example.course_work.Services;
import com.example.course_work.Models.User;
import com.example.course_work.Models.Role;

import com.example.course_work.Repositories.RoleRepository;
import com.example.course_work.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public void registerUser (String username, String password, String roleName, PasswordEncoder passwordEncoder) {
        String encodedPassword = passwordEncoder.encode(password);

        Role role = roleRepository.findByName(roleName);


        User user = new User(username, encodedPassword, role);
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id).get();
        userRepository.delete(user);
        //userRepository.deleteById(id);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public Role getRole(Long id) {
        return roleRepository.findById(id).get();
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public User getById(Long id) {
        return userRepository.findById(id).get();
    }

    public void updateRoles (Long userId, Role newRoles) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User  not found"));
        user.setRoles(newRoles);
        userRepository.save(user);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }


}



