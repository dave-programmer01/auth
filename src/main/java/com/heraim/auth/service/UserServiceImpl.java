package com.heraim.auth.service;

import com.heraim.auth.exception.EmailAlreadyExists;
import com.heraim.auth.exception.ResourceNotFound;
import com.heraim.auth.mapper.UserMapper;
import com.heraim.auth.model.User;
import com.heraim.auth.payload.UserDTO;
import com.heraim.auth.payload.UserResponse;
import com.heraim.auth.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }



    @Override
    public UserResponse getAllUsers() {
        List<User> users = userRepository.findAll();

        if (users.isEmpty()) {
            throw new ResourceNotFound("No users found");
        }

        List<UserDTO> userDTOS = userMapper.usersToUserDTOs(users);

        return new UserResponse(userDTOS);
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFound("User not found"));
        return userMapper.userToUserDTO(user);
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        User user = userMapper.userDTOToUser(userDTO);
        if(userRepository.existsByEmailIgnoreCase(user.getEmail())){
            throw new EmailAlreadyExists("Email already exists");
        }


        return userMapper.userToUserDTO(userRepository.save(user));
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFound("User not found"));
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        return userMapper.userToUserDTO(userRepository.save(user));
    }

    @Override
    public UserDTO deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFound("User not found"));
        userRepository.delete(user);
        return userMapper.userToUserDTO(user);
    }
}
