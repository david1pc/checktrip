package com.proyecto.checktrip.repo;

import com.proyecto.checktrip.entities.Role;
import com.proyecto.checktrip.entities.RoleClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleClientRepo extends JpaRepository<RoleClient,Integer>  {
    @Query("SELECT rc from Client c join RoleClient rc on c.codigo = rc.client.codigo join Role r on r.codigo = rc.role.codigo join Person p on c.persona.codigo = p.codigo WHERE p.username = :username")
    List<RoleClient> findAllByUsername(String username);

    @Query("SELECT r from Client c join RoleClient rc on c.codigo = rc.client.codigo join Role r on r.codigo = rc.role.codigo join Person p on c.persona.codigo = p.codigo WHERE p.username = :username")
    List<Role> findAllRolesByUsername(String username);
}
