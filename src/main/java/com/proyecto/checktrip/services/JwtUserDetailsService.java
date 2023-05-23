package com.proyecto.checktrip.services;

import com.proyecto.checktrip.entities.Person;
import com.proyecto.checktrip.entities.Role;
import com.proyecto.checktrip.exceptions.PersonaNoExiste;
import lombok.SneakyThrows;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    private final ClientServiceImpl clientService;
    private final RoleServiceImpl roleService;

    public JwtUserDetailsService(ClientServiceImpl clientService, RoleServiceImpl roleService){
        this.clientService = clientService;
        this.roleService = roleService;
    }

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            Person person = this.clientService.obtenerPersona(username);
            List<Role> rolesClient = this.roleService.findClientRolesByUsername(username);
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            for(Role role : rolesClient){
                authorities.add(new SimpleGrantedAuthority(role.getNombre()));
            }
            return new User(person.getUsername(), person.getPassword(), authorities);
        }catch(Exception e){
            throw new PersonaNoExiste("El username o contraseña es incorrecto");
        }
    }

}
