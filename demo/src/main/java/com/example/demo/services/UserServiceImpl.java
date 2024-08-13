package com.example.demo.services;

import org.springframework.stereotype.Service;

import com.example.demo.model.Users;
import com.example.demo.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Users saveUser(Users user) {
        // TODO Auto-generated method stub
        return userRepository.save(user);
    }

    @Override
    public Users getUserById(Long id) {
        // TODO Auto-generated method stub
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteUser(Long id) {
        // TODO Auto-generated method stub
        userRepository.deleteById(id);
    }

    @Override
    public Users updateUser(Users user) {
        // TODO Auto-generated method stub
        return userRepository.save(user);
    }

    @Override
    public Iterable<Users> getUsers() {
        // TODO Auto-generated method stub
        return userRepository.findAll();
    }

    
}
