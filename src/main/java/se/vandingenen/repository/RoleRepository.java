package se.vandingenen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.vandingenen.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
