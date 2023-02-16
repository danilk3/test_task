package com.example.test_task.security.jwt;

import com.example.test_task.model.User;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;

@NoArgsConstructor
public class JwtUserFactory {

    public static JwtUser createJwtUserFromUser(User user) {
        return new JwtUser(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getCreatedAt(),
                new ArrayList<SimpleGrantedAuthority>() {
                    {
                        add(new SimpleGrantedAuthority("USER_ROLE"));
                    }
                }
        );
    }
}
