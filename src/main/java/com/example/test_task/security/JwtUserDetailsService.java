package com.example.test_task.security;

import com.example.test_task.model.User;
import com.example.test_task.security.jwt.JwtUser;
import com.example.test_task.security.jwt.JwtUserFactory;
import com.example.test_task.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByEmail(username);

        // TODO: сделать красиво
        if (username == null) {
            throw new UsernameNotFoundException("User not found" + username);
        }

        JwtUser jwtUser = JwtUserFactory.createJwtUserFromUser(user);

        return jwtUser;
    }
}
