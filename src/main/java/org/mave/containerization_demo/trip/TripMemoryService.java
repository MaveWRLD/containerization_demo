package org.mave.containerization_demo.trip;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TripMemoryService {

    private final TripMemoryRepository repository;
    private final ImageStorageService imageStorageService;

    public List<TripMemory> findAll() {
        return repository.findAll();
    }

    public TripMemory findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Trip not found: " + id));
    }

    public TripMemory save(TripMemory trip, MultipartFile image) throws IOException {
        if (image != null && !image.isEmpty()) {
            trip.setImagePath(imageStorageService.store(image));
        }
        return repository.save(trip);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
