package org.mave.containerization_demo.trip;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class TripMemory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String destination;
    private LocalDate tripDate;

    @Column(length = 2000)
    private String notes;

    private String imagePath;
}
