package com.example.secureendpointsjwt.controllers;

import com.example.secureendpointsjwt.dtos.AuthRequest;
import com.example.secureendpointsjwt.dtos.AuthResponse;
import com.example.secureendpointsjwt.models.UserInfo;
import com.example.secureendpointsjwt.repos.UserInfoRepository;
import com.example.secureendpointsjwt.service.JwtService;
import com.example.secureendpointsjwt.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserInfoService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody UserInfo userInfo) {
        System.out.println(userInfo.getPassword());
        return service.addUser(userInfo);
    }

    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userProfile() {
        return "Welcome to User Profile";
    }

    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }

    @PostMapping("/generateToken")
    public AuthResponse authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Optional<UserInfo> userInfo =  userInfoRepository.findByName(authRequest.getUsername());

        if (userInfo.isEmpty()){
            throw new UsernameNotFoundException("an error ocuucred");
        }
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {

            AuthResponse authResponse = new AuthResponse();
            authResponse.setToken(jwtService.generateToken(userInfo.get().getName()));
            authResponse.setEmail(userInfo.get().getEmail());
            authResponse.setUsername(userInfo.get().getName());

            return authResponse;
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

}