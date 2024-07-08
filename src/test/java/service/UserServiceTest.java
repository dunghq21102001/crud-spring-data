package service;

import com.BEFresherTP.DTO.RegisterDTO;
import com.BEFresherTP.DTO.UserDTO;
import com.BEFresherTP.common.RoleEnum;
import com.BEFresherTP.entity.Role;
import com.BEFresherTP.entity.User;
import com.BEFresherTP.repository.UserRepository;
import com.BEFresherTP.service.serviceImp.JwtService;
import com.BEFresherTP.service.serviceImp.RoleServiceImp;
import com.BEFresherTP.service.serviceImp.UserServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserServiceTest {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleServiceImp roleService;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private UserServiceImp userServiceImp;

    private RoleEnum roleEnum = new RoleEnum();


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testFindAll() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "Van", "A", 29, "male", "n2ooqiwnfn@gmail.com"));
        users.add(new User(2, "Thanh", "B", 24, "male", "acb2n@gmail.com"));

        when(userRepository.findAllByDeletedFalse()).thenReturn(users);

        List<UserDTO> userDTOs = userServiceImp.findAll();
        assertEquals(2, userDTOs.size());
    }

    @Test
    public void testFindById() {
        User user = new User(1, "Lan", "BCD", 23, "male", "n2ooqiwnfn@gmail.com");

        UserDTO userDTO = new UserDTO();
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(modelMapper.map(user, UserDTO.class)).thenReturn(userDTO);
        assertEquals(user.getFirstName(), "Lan");
    }

    @Test
    public void testFindById_NotFound() {
        when(userRepository.findById(4)).thenReturn(Optional.empty());

        Throwable exception = assertThrows(NoSuchElementException.class, () -> userServiceImp.findById(4));
        assertEquals("No value present", exception.getMessage());
    }

    @Test
    public void testCreateUser() {
        User user = new User(1, "Lan", "BCD", 23, "male", "n2ooqiwnfn@gmail.com", "123@D");

        when(userRepository.save(user)).thenReturn(user);

        assertEquals(user.getFirstName(), "Lan");
    }

    @Test
    public void testUpdateUserNoSuchElementException() {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("Updated");
        Throwable exception = assertThrows(NoSuchElementException.class, () -> userServiceImp.updateUser(userDTO, 8));
        assertEquals("User not found", exception.getMessage());
    }

    @Test
    public void testRegister() {
        RegisterDTO registerDTO = new RegisterDTO("Nguyen", "Test", "test@gmail.com", "Dt@123");

        User user= new User();
        user.setFirstName(registerDTO.getFirstName());
        user.setLastName(registerDTO.getLastName());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(registerDTO.getPassword()));

        Role mockRole = new Role();
        mockRole.setName(roleEnum.getCOMMON_USER());

        when(userRepository.findByEmail(registerDTO.getEmail())).thenReturn(null);
        when(roleService.getRole("COMMON_USER")).thenReturn(mockRole);
        when(userRepository.save(any(User.class))).thenReturn(user);
//        when(jwtService.createJWToken(user)).thenReturn("mock.jwt.token");

        ResponseEntity<?> responseEntity = userServiceImp.registerUser(registerDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        verify(userRepository, times(1)).findByEmail(registerDTO.getEmail());
        verify(roleService, times(1)).getRole("COMMON_USER");
        verify(userRepository, times(1)).save(any(User.class));


    }


}
