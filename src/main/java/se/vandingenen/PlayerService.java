package se.vandingenen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.vandingenen.database.Player;
import se.vandingenen.database.PlayerRepository;

@Service
public class PlayerService {

    private PlayerRepository playerrepo;

    @Autowired
    public PlayerService(PlayerRepository playerrepo) {
        this.playerrepo = playerrepo;
    }

    public void register(String name, String password) {
        Player player = new Player(name, password);
        playerrepo.save(player);
    }

    public boolean userNameExists(String username) {
        if (playerrepo.findByName(username).isPresent()) {
            return true;
        }
        return false;
    }

}
