package org.agty.elfiumexpress.storage.service;

import org.agty.elfiumexpress.storage.StorageProperties;
import org.agty.elfiumexpress.storage.exceptions.StorageException;
import org.agty.elfiumexpress.storage.exceptions.StorageFileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
public class FileSystemStorageService implements StorageService {
    private final Path rootLocation;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {

        if(properties.getLocation().trim().isEmpty()){
            throw new StorageException("File upload location can not be Empty.");
        }

        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    public boolean store(MultipartFile file, String storePath) {

        try {

            /*
             * TODO
             *      отработать все ошибки
             *      экскизы https://stackoverflow.com/questions/1069095/how-do-you-create-a-thumbnail-image-out-of-a-jpeg-in-java
             *              /thumb/{400}x{400}/content/... + ограничение по высоте и ширине
             */
            if (file.isEmpty() || file.getOriginalFilename() == null) {
                throw new StorageException("Failed to store empty file.");
            }

            Path destinationFile = this.rootLocation
                    .resolve(Paths.get(storePath))
                    .normalize()
                    .toAbsolutePath();

            if (!destinationFile.getParent().getParent().getParent().equals(this.rootLocation.toAbsolutePath())) {
                // This is a security check
                throw new StorageException("Cannot store file outside current directory.");
            } else {
                if (!Files.exists(destinationFile.getParent())) {
                    Files.createDirectories(destinationFile.getParent());
                }
            }

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception e) {
                throw new StorageException("Failed to store file.", e);
            }
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file.", e);
        }

        return true;
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + filename);

            }
        }
        catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }
}