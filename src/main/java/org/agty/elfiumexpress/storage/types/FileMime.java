package org.agty.elfiumexpress.storage.types;

import com.google.gson.Gson;
import org.agty.elfiumexpress.storage.types.mime.MimeList;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileMime {
    private static final List<FileMimeType> fileMimeType = new ArrayList<>();
    private static MimeList mimeList;

    public static void init() {

        if (mimeList == null) {
            try {
                ResourceLoader resourceLoader = new FileSystemResourceLoader();
                Resource resource = resourceLoader.getResource("classpath:data/mimeTypes-v1.0.0.json");
                mimeList = new Gson().fromJson(new InputStreamReader(resource.getInputStream()), MimeList.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static String getExtension(String mime) {
        init();
        return mimeList.getFirstExtensionByMimeType(mime);
    }

    public static String getContentType(String extension) {
        init();
        return mimeList.getMimeTypeByExtension(extension);
    }

    private static class FileMimeType {
        private final String mimeType;
        private final List<String> extensions = new ArrayList<>();
        public final String description;

        public FileMimeType(String mimeType, String extension, String description) {
            this.mimeType = mimeType;
            this.description = description;
            if (extension != null && !extension.isEmpty()) {
                if (extension.contains(",")) {
                    String[] extensionParts = extension.split(",");
                    for (String extensionPart : extensionParts) {
                        extensions.add(extensionPart.trim());
                    }
                } else {
                    extensions.add(extension.trim());
                }
            }
        }

        public String getMimeType() {
            return mimeType;
        }

        public List<String> getExtensions() {
            return extensions;
        }

        public String getDescription() {
            return description;
        }

        public boolean containsExtension(String extension) {
            if (extension != null && !extension.isEmpty()) {
                return extensions.contains(extension.trim());
            }

            return false;
        }

        public boolean equalsMimeType(String mimeType) {
            if (mimeType != null && !mimeType.isEmpty()) {
                return this.mimeType.equals(mimeType);
            }

            return false;
        }
    }
}
