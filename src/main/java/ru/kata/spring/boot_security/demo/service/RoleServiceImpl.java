package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository rs;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.rs = roleRepository;
    }

    @Override
    @Transactional
    public List<Role> getAllRoles() {
        return rs.findAll();
    }

    @Override
    @Transactional
    public void addRole(Role role) {
        rs.save(role);
    }

    @Override
    @Transactional
    public Role getRoleById(Long id) {
        return rs.getById(id);
    }

    @Override
    @Transactional
    public Set<Role> findAllRoleId(List<Long> userId) {
        return rs.findAllId(userId);
    }
}
