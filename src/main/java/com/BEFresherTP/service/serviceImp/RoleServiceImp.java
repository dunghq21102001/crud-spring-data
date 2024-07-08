package com.BEFresherTP.service.serviceImp;

import com.BEFresherTP.entity.Role;
import com.BEFresherTP.repository.RoleRepository;
import com.BEFresherTP.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImp implements RoleService {

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public Role getRole(String roleName) {
        return roleRepository.findByName(roleName);
    }
}
