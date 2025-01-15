package org.agty.elfiumexpress.storage.repository;

import org.agty.agtysql.data.Arguments;
import org.agty.agtysql.interfaces.SqlRow;
import org.agty.elfiumexpress.dao.PermanentConnection;
import org.agty.elfiumexpress.storage.entity.Thumb;
import org.agty.elfiumexpress.storage.entity.UploadedFile;
import org.agty.elfiumexpress.storage.service.StorageService;
import org.agty.elfiumexpress.storage.utils.UploadedFileUtils;
import org.agty.utils.AgtyUtils;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Repository
public class FileUploadRepo {
    private final StorageService storageService;

    public FileUploadRepo(StorageService storageService) {
        this.storageService = storageService;
    }

    public UploadedFile store(MultipartFile file) {
        UploadedFile uploadedFile = UploadedFileUtils.multipartFileCheckAndConvertToUploadedFile(file, 0L);

        if (storageService.store(file, getStorePath(uploadedFile, 0L))) {
            return uploadedFile;
        }

        return null;
    }

    public Long save(UploadedFile uploadedFile) {
        if (uploadedFile == null || !uploadedFile.hasFile()) return null;

        Arguments arguments = new Arguments().setTable("{files}")
                .setData("name", uploadedFile.getName())
                .setData("file", uploadedFile.getFile())
                .setData("content_type", uploadedFile.getContentType())
                .setData("size", uploadedFile.getSize())
                .setData("ext", AgtyUtils.getFileExtension(uploadedFile.getName()))
                .setReturnLastInsertId(true);

        if (uploadedFile.idExists()) {
            arguments.setWhere("id_file = " + uploadedFile.getIdFile());
            PermanentConnection.getConnection().update(arguments);
            return uploadedFile.getIdFile();
        }
        else {
            return PermanentConnection.getConnection().insert(arguments);
        }
    }

    public UploadedFile findByName(String filename) {
        SqlRow row = PermanentConnection
                .getConnection()
                .fetch(
                    new Arguments()
                        .setTable("{files}")
                        .setWhere("file = '" + AgtyUtils.hencode(filename) + "'")
                );
        return row.isEmpty() ? null : UploadedFileUtils.rowToUploadedFile(row);
    }

    private String getStorePath(UploadedFile uploadedFile, long idUser) {
        return "users/" + idUser + "/" + uploadedFile.getFile();
    }

    public void deleteFile(UploadedFile uploadedFile, long idUser) {
        try {
            Files.deleteIfExists(Path.of("content/files/users/" + idUser + "/" + uploadedFile.getFile()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}