package com.dmdev.http.web.service;

import com.dmdev.http.web.util.PropertiesUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImageService {
    private static final ImageService INSTANCE = new ImageService();
    private final String basePath = PropertiesUtil.get("image.base.url");

    public void upload(String imagePath, InputStream imageContent) throws IOException {
        Path imageFullPath = Path.of(basePath, imagePath);
        try(imageContent){
            Files.createDirectories(imageFullPath.getParent());
            Files.write(imageFullPath,imageContent.readAllBytes(), StandardOpenOption.CREATE,StandardOpenOption.TRUNCATE_EXISTING);
        }
    }

    public static ImageService getINSTANCE() {
        return INSTANCE;
    }

    @SneakyThrows
    public Optional<InputStream> get(String imagePath) {
        Path imageFullPath = Path.of(basePath, imagePath);
        return Files.exists(imageFullPath) ? Optional.of(Files.newInputStream(imageFullPath)) : Optional.empty();
    }
}
