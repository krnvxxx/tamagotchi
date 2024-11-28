package ru.game.tamagotchi.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.game.tamagotchi.model.UserEntity;
import ru.game.tamagotchi.repository.UserRepository;
import ru.game.tamagotchi.services.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserEntity findByLogin(String username) {
        return userRepository.findByLogin(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String rawPassword = userRepository.findByLogin(username).getPassword();
        if (rawPassword == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return User.builder()
                .username(username)
                .password(rawPassword)
                .roles("ADMIN")
                .build();
    }

    public UserEntity create(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }
}
