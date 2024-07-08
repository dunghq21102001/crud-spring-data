package com.BEFresherTP.service.serviceImp;


import com.BEFresherTP.DTO.LoginDTO;
import com.BEFresherTP.DTO.RegisterDTO;
import com.BEFresherTP.DTO.TokenResponse;
import com.BEFresherTP.DTO.UserDTO;
import com.BEFresherTP.common.RoleEnum;
import com.BEFresherTP.entity.Role;
import com.BEFresherTP.entity.User;
import com.BEFresherTP.repository.UserRepository;
import com.BEFresherTP.service.UserService;
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
public class UserServiceImp implements UserService {

    RoleEnum roleEnum = new RoleEnum();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RoleServiceImp roleService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @Override
    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAllByDeletedFalse();
        List<UserDTO> userDTOS = users
                .stream()
                .map(user -> this.modelMapper
                        .map(user, UserDTO.class))
                .collect(Collectors.toList());

        return userDTOS;
    }

    @Override
    public UserDTO findById(int id) {
        User user = userRepository.findById(id).get();
        return this.modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = this.modelMapper.map(userDTO, User.class);
        User savedUser = this.userRepository.save(user);
        return this.modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    @Transactional
    public UserDTO updateUser(UserDTO user, int id) {
        User userFound = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
        ;
        userFound.setFirstName(user.getFirstName());
        userFound.setLastName(user.getLastName());
        userFound.setEmail(user.getEmail());
        userFound.setGender(user.getGender());

        int updatedRows = userRepository.updateUserData(id, user.getFirstName(), user.getLastName(), user.getEmail(), user.getGender(), userFound.getVersion());

        if (updatedRows == 0) {
            throw new OptimisticLockingFailureException("Update failed");
        }

        return modelMapper.map(userFound, UserDTO.class);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return this.modelMapper.map(user, UserDTO.class);
    }

    @Override
    @Transactional
    public ResponseEntity<?> registerUser(RegisterDTO registerDTO) {
        User existingUser = userRepository.findByEmail(registerDTO.getEmail());
        if (existingUser != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Existed email!");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = new User();

        user.setEmail(registerDTO.getEmail());
        user.setPassword(encoder.encode(registerDTO.getPassword()));
        user.setFirstName(registerDTO.getFirstName());
        user.setLastName(registerDTO.getLastName());

        Role role = roleService.getRole(roleEnum.getCOMMON_USER());

        List<Role> roles = new ArrayList<>();
        roles.add(role);

        user.setRoles(roles);

        userRepository.save(user);

        String jwtToken = jwtService.createJWToken(user);

        Map<String, String> response = new HashMap<>();
        response.put("user", user.toString());
        response.put("token", jwtToken);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<?> login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
        );
        org.springframework.security.core.userdetails.User springUser =
                (org.springframework.security.core.userdetails.User) authentication.getPrincipal();

        String username = springUser.getUsername();

//        list này chưa danh sách các role của user đó
        List<GrantedAuthority> authorities = new ArrayList<>(springUser.getAuthorities());
        User user = userRepository.findByEmail(username);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }


        String accessToken = jwtService.createJWToken(user);
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken(accessToken);
        return ResponseEntity.ok(tokenResponse.toString());
    }
}
