package com.example.implementingspringsecurity.Services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.implementingspringsecurity.DTO.UserRequestDto;
import com.example.implementingspringsecurity.Entity.User;
import com.example.implementingspringsecurity.Repository.UserRepository;
import com.example.implementingspringsecurity.Security.SecurityConfiguration;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public String registerUser(UserRequestDto userRequestDto)
    {
        User user = User.builder()
                    .username(userRequestDto.getUsername())
                    .password(passwordEncoder.encode(userRequestDto.getPassword()))
                    .build();

        userRepository.save(user);

        return "User Registered successfully.";
    }

    public String loginUser(UserRequestDto userRequestDto)
    {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequestDto.getUsername(), userRequestDto.getPassword()));

        User user = (User)authentication.getPrincipal();

        String token = jwtService.generateJwtToken(user.getUsername());
        
        return token;

    }

}
