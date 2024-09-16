package com.springProject.FirstProject.service.Impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springProject.FirstProject.Entity.User;
import com.springProject.FirstProject.exceptions.ResourceNotFoundException;
import com.springProject.FirstProject.payloads.UserDto;
import com.springProject.FirstProject.repository.UserRepo;
import com.springProject.FirstProject.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = dtoToUser(userDto);
        // we have to generate dynamic userId yourself
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        // password encode
        // user.setPassword(userId);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepo.save(user);
        return userToDto(savedUser);
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        userRepo.delete(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepo.findAll();
        List<UserDto> userDtos = users.stream().map(user -> userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public UserDto getSingleUser(int userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "ID", userId));
        return userToDto(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "ID", userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User savedUser = userRepo.save(user);
        return userToDto(savedUser);
    }

    public User dtoToUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        // user.setUserId(userDto.getUserId());
        // user.setName(userDto.getName());
        // user.setEmail(userDto.getEmail());
        // user.setPassword(userDto.getPassword());
        // user.setAbout(userDto.getAbout());
        return user;
    }

    public UserDto userToDto(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        // userDto.setUserId(user.getUserId());
        // userDto.setName(user.getName());
        // userDto.setEmail(user.getEmail());
        // userDto.setPassword(user.getPassword());
        // userDto.setAbout(user.getAbout());
        return userDto;
    }

}
