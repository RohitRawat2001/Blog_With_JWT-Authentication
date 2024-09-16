package com.springProject.FirstProject.service;

import java.util.List;

import com.springProject.FirstProject.payloads.UserDto;


public interface UserService {

   UserDto createUser(UserDto userDto);

   UserDto updateUser(UserDto userDto,Integer userId);

   void deleteUser(Integer userId);

   List<UserDto> getAllUsers();

   UserDto getSingleUser(int userId);

}
