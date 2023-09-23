package exercise;

import exercise.model.User;
import exercise.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User.UserBuilder;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Список полномочий, которые будут предоставлены пользователю после аутентификации
        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("user"));

        // BEGIN
        Optional<User> user = repository.findUserByUsername(username);
        if (user.isEmpty()) {
            throw new UserNotFoundException("No one user was found by this username!");
        }
        User realUser = user.get();
//        UserBuilder builder;
//        builder = org.springframework.security.core.userdetails.User.withUsername(username);
//        builder.password(realUser.getPassword());
//        builder.authorities(authorities);
        return new org.springframework.security.core.userdetails.User(
            realUser.getUsername(), realUser.getPassword(), authorities);
        // END
    }
}
