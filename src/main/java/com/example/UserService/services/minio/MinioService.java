package com.example.UserService.services.minio;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.InputStream;

@Service
public class MinioService {

    @Autowired
    private MinioClient minioClient;

    public String uploadFile(String bucketName, String objectName, InputStream inputStream, long size, String contentType) {
        try {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .stream(inputStream, size, -1)
                            .contentType(contentType)
                            .build()
            );

            String url = "http://localhost:9000/" + bucketName + "/" + objectName;

            System.out.println("File uploaded successfully. URL: " + url);
            return url;
        } catch (Exception e) {
            System.err.println("Error encountered: " + e);
            return null;
        }
    }
}

