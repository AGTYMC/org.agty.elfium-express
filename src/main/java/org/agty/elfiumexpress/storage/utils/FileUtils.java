package org.agty.elfiumexpress.storage.utils;

import org.agty.elfiumexpress.storage.entity.UploadedFile;
import org.agty.utils.AgtyUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public class FileUtils {
    private static int counter;

    private static int getCounter() {
        if (counter == Integer.MAX_VALUE) counter = 0;
        return ++counter;
    }

    public static String md5HashFile(InputStream inputStream) {
        try {
            return DigestUtils.md5DigestAsHex(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String generateHashFile(MultipartFile file) {
        try {
            return md5HashFile(file.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String generateUniqueName(MultipartFile file, Object salt) {
        String ext = AgtyUtils.getFileExtensionWithDot(file.getOriginalFilename());
        return AgtyUtils.md5(
        (salt != null ? salt.toString() : "")
                + AgtyUtils.rand(100, 300)
                + file.getOriginalFilename()
                + getCounter()
        ) + ext;
    }

    public static String generateUniqueName(UploadedFile file, Object salt) {
        //String ext = AgtyUtils.getFileExtension(file.getName());
        return AgtyUtils.md5(
                (salt != null ? salt.toString() : "")
                + AgtyUtils.rand(100, 300)
                + file.getName()
                + getCounter()
        );
    }

}
