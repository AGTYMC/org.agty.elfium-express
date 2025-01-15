package org.agty.elfiumexpress.storage.service;

import org.agty.elfiumexpress.storage.entity.UploadedFile;
import org.agty.elfiumexpress.storage.repository.FileUploadRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedList;
import java.util.List;

@Service
public class FileUploadService {
    private final FileUploadRepo fileUploadRepo;

    public FileUploadService(FileUploadRepo fileUploadRepo) {
        this.fileUploadRepo = fileUploadRepo;
    }

    public List<UploadedFile> save(MultipartFile[] files) {
        List<UploadedFile> uploadedFiles = new LinkedList<UploadedFile>();

        if (files == null) return uploadedFiles;

        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                UploadedFile uploadedFile = fileUploadRepo.store(file);
                if (uploadedFile != null) uploadedFiles.add(uploadedFile);
            }
        }

        return uploadedFiles;
    }

    public UploadedFile getFile(String filename) {
        return fileUploadRepo.findByName(filename);
    }
}
