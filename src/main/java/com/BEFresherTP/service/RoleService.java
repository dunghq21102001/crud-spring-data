package com.BEFresherTP.service;

import com.BEFresherTP.entity.Role;
import com.BEFresherTP.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {



    public Role getRole(String roleName);
}
