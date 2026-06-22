package org.mave.containerization_demo.trip;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TripMemoryRepository extends JpaRepository<TripMemory, Long> {
}
