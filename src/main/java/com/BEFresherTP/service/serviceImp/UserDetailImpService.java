package com.BEFresherTP.service.serviceImp;

import com.BEFresherTP.entity.Role;
import com.BEFresherTP.entity.User;
import com.BEFresherTP.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailImpService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user != null) {
            List<Role> rolesList = user.getRoles();
            List<String> roleNames = rolesList.stream()
                    .map(Role::getName)
                    .collect(Collectors.toList());

            String[] rolesArray = roleNames.toArray(new String[roleNames.size()]);

            var appUser = org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
                    .password(user.getPassword())
                    .roles(rolesArray)
                    .build();
            return appUser;
        } else throw new UsernameNotFoundException("User not found");
    }
}
