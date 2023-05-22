package de.hsb.counter.counting.repository;

import de.hsb.counter.counting.model.CounterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CounterRepository extends JpaRepository<CounterEntity, Long> {

    Optional<CounterEntity> findFirstByOrderByTimeStampDesc();
}
