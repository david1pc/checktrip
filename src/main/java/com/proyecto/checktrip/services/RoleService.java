package com.proyecto.checktrip.services;

import com.proyecto.checktrip.entities.Role;
import com.proyecto.checktrip.entities.RoleClient;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Optional<Role> findById(Integer codigo);
    List<RoleClient> findAllClientRolesByUsername(String username);
    List<Role> findClientRolesByUsername(String username);
    Role createRole(Role role);
    RoleClient createRoleClient(RoleClient roleClient);
}
