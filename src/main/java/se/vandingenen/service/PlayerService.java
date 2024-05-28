package se.vandingenen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.vandingenen.security.PasswordEncryptor;
import se.vandingenen.entity.Player;
import se.vandingenen.repository.PlayerRepository;
import se.vandingenen.entity.Role;
import se.vandingenen.repository.RoleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    private PlayerRepository playerrepo;
    private RoleRepository roleRepository;

    @Autowired
    public PlayerService(PlayerRepository playerrepo, RoleRepository roleRepository) {

        this.playerrepo = playerrepo;
        this.roleRepository = roleRepository;
    }

    public void register(String name, String password) {
        Player player = new Player(name, password);
        String hashPassword = PasswordEncryptor.hashPassword(password);
        player.setPassword(hashPassword);

        Role role = roleRepository.findByName("PLAYER");
        if(role == null){
            role = checkRoleExists();
        }
        player.setRoles(List.of(role));
        playerrepo.save(player);

    }

    public boolean usernameExists(String username) {
        if (playerrepo.findByName(username).isPresent()) {
            return true;
        }
        return false;
    }
    public boolean validateLogin (String username, String password) {

        boolean result = false;
        Optional<Player> opUser = playerrepo.findByName(username);

        if (opUser.isPresent()) {
            String savedPassword = opUser.get().getPassword();
            return PasswordEncryptor.checkPassword(password, savedPassword);
        }

        return result;
    }
    private Role checkRoleExists(){
        Role role = new Role();
        role.setName("PLAYER");
        return roleRepository.save(role);
    }

}
