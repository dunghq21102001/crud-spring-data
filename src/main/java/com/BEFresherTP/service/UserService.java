package com.BEFresherTP.service;

import com.BEFresherTP.DTO.LoginDTO;
import com.BEFresherTP.DTO.RegisterDTO;
import com.BEFresherTP.DTO.TokenResponse;
import com.BEFresherTP.DTO.UserDTO;
import com.BEFresherTP.common.RoleEnum;
import com.BEFresherTP.entity.Role;
import com.BEFresherTP.entity.User;
import com.BEFresherTP.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public interface UserService {

    public List<UserDTO> findAll();

    public UserDTO findById(int id);

    public UserDTO createUser(UserDTO userDTO);

    public UserDTO updateUser(UserDTO user, int id);

    public void deleteUser(int id);

    public UserDTO findByEmail(String email);

    public ResponseEntity<?> registerUser(RegisterDTO registerDTO);

    public ResponseEntity<?> login(LoginDTO loginDTO);


}
