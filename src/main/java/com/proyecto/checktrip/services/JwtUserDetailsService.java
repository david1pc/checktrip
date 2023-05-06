package com.proyecto.checktrip.services;

import com.proyecto.checktrip.entities.Person;
import com.proyecto.checktrip.entities.Role;
import lombok.SneakyThrows;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
    protected final Log logger = LogFactory.getLog(this.getClass());
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
            Person person = this.clientService.findClientByUsername(username);
            List<Role> rolesClient = this.roleService.findClientRolesByUsername(username);
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            for(Role role : rolesClient){
                authorities.add(new SimpleGrantedAuthority(role.getNombre()));
            }
            UserDetails userDetails = new User(person.getUsername(), person.getPassword(), authorities);
            return userDetails;
        }catch(Exception e){
            throw new Exception("El username o contraseña es incorrecto");
        }
    }

}
