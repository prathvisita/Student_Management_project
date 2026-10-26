package com.pk.Student_Management_project.Service;

import com.pk.Student_Management_project.DTO.UserRequestDto;
import com.pk.Student_Management_project.Model.Users;
import com.pk.Student_Management_project.Repository.UsersRepo;
import com.pk.Student_Management_project.configure.SecurityFilterConfg;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

   // private final SecurityFilterConfg defaultSecurityFilterChain;

    private final UsersRepo usersRepo;

    private BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder(12);

    public Users register(UserRequestDto userRequestDto) {
        if(usersRepo.existsByUsername(userRequestDto.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        Users user=Users.builder()
                .username(userRequestDto.getUsername())
                .password(bCryptPasswordEncoder.encode(userRequestDto.getPassword()))
                .build();

//        UserRequestDto userRequestDto1=UserRequestDto.builder()
//                .username(userRequestDto.getUsername())
//                .password(bCryptPasswordEncoder.encode(userRequestDto.getPassword()))
//                .build();
       // userRequestDto.setPassword(bCryptPasswordEncoder.encode(userRequestDto.getPassword()));
        return   usersRepo.save(user);

    }
}
