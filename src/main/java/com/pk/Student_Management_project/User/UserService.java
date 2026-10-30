package com.pk.Student_Management_project.User;

import com.pk.Student_Management_project.SpringSec.JwtService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

   // private final SecurityFilterConfg defaultSecurityFilterChain;
    private final PasswordEncoder passwordEncoder;
    private final UsersRepo usersRepo;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    //private BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder(12);
   @Transactional
    public Users register(UserRequestDto userRequestDto) {
        if(usersRepo.existsByUsername(userRequestDto.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        Users user=Users.builder()
                .username(userRequestDto.getUsername())
                .password(passwordEncoder.encode(userRequestDto.getPassword()))
                .build();
        return   usersRepo.save(user);

    }

    public String verify(UserRequestDto userRequestDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken( userRequestDto.getUsername(),userRequestDto.getPassword()));
        if(authentication.isAuthenticated()) return jwtService.getToken(userRequestDto);
          return "Invalid Credentials";
    }

}
