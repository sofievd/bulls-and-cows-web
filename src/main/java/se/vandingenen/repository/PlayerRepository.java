package se.vandingenen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.vandingenen.entity.Player;

import java.util.Optional;
@Repository
public interface PlayerRepository extends JpaRepository <Player, Integer> {
    Optional<Player> findByName(String Username);
}
