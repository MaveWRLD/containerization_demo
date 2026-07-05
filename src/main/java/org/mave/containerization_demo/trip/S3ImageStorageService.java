package org.mave.containerization_demo.trip;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.core.sync.RequestBody;

import java.io.IOException;
import java.util.UUID;

@Service
@ConditionalOnProperty(name = "app.storage", havingValue = "s3")
public class S3ImageStorageService implements ImageStorageService {

    @Value("${IMAGES_BUCKET_NAME}")
    private String bucketName;

    @Value("${CLOUDFRONT_DOMAIN}")
    private String cloudFrontDomain;

    private final S3Client s3 = S3Client.builder()
            .region(Region.EU_CENTRAL_1)
            .build();

    @Override
    public String store(MultipartFile file) throws IOException {
        String key = UUID.randomUUID() + "_" + file.getOriginalFilename();
        s3.putObject(
                PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(key)
                        .contentType(file.getContentType())
                        .build(),
                RequestBody.fromInputStream(file.getInputStream(), file.getSize())
        );
        return "https://" + cloudFrontDomain + "/" + key;
    }
}
