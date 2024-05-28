package se.vandingenen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.vandingenen.entity.Result;

public interface ResultRepository extends JpaRepository <Result, Integer> {
}
