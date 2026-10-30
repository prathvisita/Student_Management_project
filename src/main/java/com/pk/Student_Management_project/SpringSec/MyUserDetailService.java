package com.pk.Student_Management_project.SpringSec;

import com.pk.Student_Management_project.User.UserPrincipal;
import com.pk.Student_Management_project.User.Users;
import com.pk.Student_Management_project.User.UsersRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailService implements UserDetailsService {
    private final UsersRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user=userRepo.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("User not found");
        }
       // user=userRepo.findByUsername(username);
        return new UserPrincipal(user);
    }
}
