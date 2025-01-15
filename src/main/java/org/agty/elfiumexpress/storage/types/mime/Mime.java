package org.agty.elfiumexpress.storage.types.mime;

import java.util.ArrayList;
import java.util.List;

public class Mime {
    private String mime;
    private String extension;
    private List<String> extensionsArray = new ArrayList<>();
    public String description;

    public Mime() {}

    public Mime(String mime, String extension, String description) {
        this.mime = mime;
        this.extension = extension;
        this.description = description;
    }

    public String getMime() {
        return mime;
    }

    public String getExtension() {
        return extension;
    }

    public List<String> getExtensionsList() {
        if (extensionsArray.isEmpty()) {
            extensionsToArray();
        }
        return extensionsArray;
    }

    public String getDescription() {
        return description;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private void extensionsToArray() {
        if (getExtension() != null && !getExtension().isEmpty()) {
            if (getExtension().contains(",")) {
                extensionsArray.addAll(List.of(getExtension().split(",")));
            } else {
                extensionsArray.add(getExtension().trim());
            }
        }
    }

    public boolean containsExtension(String extension) {
        return getExtensionsList().contains(extension.trim());
    }

    public boolean equalsMimeType(String mimeType) {
        if (mimeType != null && !mimeType.isEmpty()) {
            return this.mime.equals(mimeType);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Mime{" +
                "mime='" + mime + '\'' +
                ", extension='" + extension + '\'' +
                ", extensionsArray=" + extensionsArray +
                ", description='" + description + '\'' +
                '}';
    }
}
