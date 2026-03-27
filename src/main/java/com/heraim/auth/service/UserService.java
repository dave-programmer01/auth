package com.heraim.auth.service;

import com.heraim.auth.payload.UserDTO;
import com.heraim.auth.payload.UserResponse;

public interface UserService {
    UserResponse getAllUsers();
    UserDTO getUserById(Long id);
    UserDTO saveUser(UserDTO userDTO);
    UserDTO updateUser(UserDTO userDTO, Long id);
    UserDTO deleteUser(Long id);
}
