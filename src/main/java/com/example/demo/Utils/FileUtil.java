/*package com.example.demo.Utils;


import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Util {

    public static void saveImages(Long id, String fileName, MultipartFile multipartFile) throws IOException {

        String uploadDir = "src/main/webapp/WEB-INF/post-photos/" + id;

        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException ioException) {
            throw new IOException("Could not save image files:" + fileName + ioException);
        }

    }

    public static long saveFileAndReplace(String lastFile, MultipartFile file, String newFile, long id) throws IOException {
        String uploadDir = "src/main/webapp/WEB-INF/post-photos/" + id;

        Path uploadPath = Paths.get(uploadDir + "/");
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        try {
            if (lastFile != null) {
                if (!lastFile.equals(newFile)) {
                    Files.delete(uploadPath.resolve(lastFile));
                }
            }
            return Files.copy(file.getInputStream(), uploadPath.resolve(newFile), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}*/
