package org.agty.elfiumexpress.storage.types;

import java.util.*;

public class FileTypes {

    /**
     * Content-types
     */
    private static final Map<String, List<String>> contentTypes = new ContentTypes().getContentTypes();
    private static final Map<String, List<String>> extensions = new ContentTypes().getExtensions();
    
    private static class ContentTypes {
        public Map<String, List<String>> getContentTypes() {
            Map<String, List<String>> contentTypes = new HashMap<>(10);
            
            contentTypes.put("image", Arrays.asList("image/svg+xml", "image/heic", "image/heif", "image/avif", "image/jpeg", "image/png", "image/gif", "image/webp", "image/tiff", "image/apng", "image/x-icon", "bmpimage/bmp"));
            contentTypes.put("pdf",   Arrays.asList("application/pdf", "application/x-pdf", "application/acrobat", "applications/vnd.pdf", "text/pdf", "text/x-pdf", "application/x-bzpdf", "application/x-gzpdf"));
            contentTypes.put("video", Arrays.asList("video/x-msvideo", "video/mpeg", "video/mp4", "video/webm", "video/quicktime"));
            contentTypes.put("audio", Arrays.asList("application/ogg", "audio/mpeg"));
            contentTypes.put("text",  Arrays.asList("text/plain", "application/x-sh", "application/sql", "text/sql", "text/x-sql"));
            contentTypes.put("code",  Arrays.asList("text/x-java-source", "text/x-c", "text/x-c++src", "text/php", "text/x-php", "text/css", "text/html", "application/json", "application/javascript"));
            contentTypes.put("exec",  Arrays.asList("application/x-csh", "application/x-sh", "application/x-msdownload"));

             return contentTypes;
        }
        
        public Map<String, List<String>> getExtensions() {
            Map<String, List<String>> extensions = new HashMap<>(10);
            
            extensions.put("image", Arrays.asList("svg", "heic", "heif", "avif", "jpeg", "jpg", "png", "gif", "webp", "tiff", "apng", "ico", "bmp"));
            extensions.put("pdf",   Arrays.asList("pdf", "x-pdf", "acrobat", "vnd.pdf", "pdf", "bzpdf", "gzpdf"));
            extensions.put("video", Arrays.asList("avi", "mpg", "mpeg", "mp4", "webm", "qt"));
            extensions.put("audio", Arrays.asList("mp3", "ogg"));
            extensions.put("text",  Arrays.asList("cfg", "txt", "ini", "bat", "sh", "sql", "log", "conf"));
            extensions.put("code",  Arrays.asList("java", "c", "cpp", "py", "php", "css", "html", "htm", "inc", "js", "json"));
            extensions.put("exec",  Arrays.asList("sh", "bash", "exe", "msi"));

             return extensions;
        }
    }

    /**
     * Identify the file by extension
     * @param ext extension
     * @return filetype
     */
    public static String identifyByExtension(String ext) {
        for(String key : extensions.keySet()) {
            if (extensions.get(key).contains(ext)) return key;
        }
        return "application";
    }

    /**
     * Identify the file by Content-Type
     * @param contentType Content-Type
     * @return filetype
     */
    public static String identifyByContentType(String contentType) {
        for(String key : contentTypes.keySet()) {
            if (contentTypes.get(key).contains(contentType)) return key;
        }
        return "application";
    }

    private static boolean detectType(String contentType, String type) {
        if (identifyByExtension(contentType).contains(type)) return true;
        return identifyByContentType(contentType).contains(type);
    }
    
    public static boolean isImage(String extOrType) {
        return detectType(extOrType, "image");
    }
    
    public static boolean isVideo(String extOrType) {
        return detectType(extOrType, "video");
    }
    
    public static boolean isPdf(String extOrType) {
        return detectType(extOrType, "pdf");
    }
    
    public static boolean isAudio(String extOrType) {
        return detectType(extOrType, "audio");
    }
    
    public static boolean isText(String extOrType) {
        return detectType(extOrType, "text");
    }
    
    public static boolean isCode(String extOrType) {
        return detectType(extOrType, "code");
    }

    public static boolean isExec(String extOrType) {
        return detectType(extOrType, "exec");
    }

    public static String detectExtensionByContentType(String contentType) {
        return FileMime.getExtension(contentType);
    }
}
