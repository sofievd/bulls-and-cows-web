package se.vandingenen.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import se.vandingenen.entity.Player;
import se.vandingenen.repository.PlayerRepository;
import se.vandingenen.entity.Role;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private PlayerRepository repository;

    public CustomUserDetailsService (PlayerRepository repository){
        this.repository = repository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Player> optionalPlayer = repository.findByName(username);
        if(optionalPlayer.isPresent()){
            return new org.springframework.security.core.userdetails.User(optionalPlayer.get().getName(),
                    optionalPlayer.get().getPassword(), mapRolesToAuthorities((optionalPlayer.get().getRoles()
                    )));
                  } else{
            throw new UsernameNotFoundException("Invalid username or password !");
        }
    }
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        Collection<? extends GrantedAuthority> mapRoles
                = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return mapRoles;
    }
}
