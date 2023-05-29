package com.proyecto.checktrip.controllers;

import com.proyecto.checktrip.dto.*;
import com.proyecto.checktrip.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200", "https://checktrip-software.web.app/"},
        methods = {RequestMethod.POST,
                RequestMethod.GET
        })
public class AuthController {
    private final ClientServiceImpl clientService;
    private final ClienteViajeServiceImpl clienteViajeService;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;


    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDTO login) {
        try {
            Boolean esPasswdTemporal = this.clientService.verifyTemporalPasswd(login);
            if (esPasswdTemporal){
                return new ResponseEntity<>(login, HttpStatus.UPGRADE_REQUIRED);
            }
            Authentication authentication = autenticarUsuario(login.username(), login.password());
            String token = tokenService.generateToken(authentication);
            LoginResponseDTO loginResponseDTO = LoginResponseDTO.builder()
                    .username(login.username())
                    .token(token)
                    .expirationTime(30 * 60)
                    .build();
            return new ResponseEntity<>(loginResponseDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/register")
    public ClientResponseDTO createClient(@RequestBody ClientRequestDTO client) {
        return this.clientService.createClient(client);
    }

    @PostMapping("/update-passwd")
    public ClientPasswdResponseDTO updatePasswdClient(@RequestBody ClientPasswdRequestDTO client) {
        return this.clientService.updateAccount(client);
    }

    @PostMapping("/recover-account")
    public AccountRecoveryResponseDTO recoverAccount(@RequestBody AccountRecoveryRequestDTO accountRecoveryRequestDTO){
        return this.clientService.recoverAccount(accountRecoveryRequestDTO);
    }

    @PreAuthorize("hasAuthority('SCOPE_CLIENT')")
    @GetMapping("/index")
    public String index(Principal principal){
        return "Hola " + principal.getName();
    }


    public Authentication autenticarUsuario(String username, String password) {
        try{
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }catch(DisabledException e){
            throw new DisabledException("Usuario deshabilitado");
        }catch(BadCredentialsException e){
            throw new BadCredentialsException("El username o contraseña es incorrecto");
        }
    }
}
