package org.mave.containerization_demo.trip;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@ConditionalOnProperty(name = "app.storage", havingValue = "local", matchIfMissing = true)
public class LocalImageStorageService implements ImageStorageService {

    private static final Path UPLOAD_DIR = Paths.get("uploads");

    @Override
    public String store(MultipartFile file) throws IOException {
        Files.createDirectories(UPLOAD_DIR);
        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Files.copy(file.getInputStream(), UPLOAD_DIR.resolve(filename));
        return "/uploads/" + filename;
    }
}
