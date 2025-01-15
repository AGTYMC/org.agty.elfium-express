package org.agty.elfiumexpress.storage.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

/**
 * Storage service
 */
public interface StorageService {
    void init();
    boolean store(MultipartFile file, String storePath);
    Resource loadAsResource(String filename);
    Path load(String filename);
}
