package org.agty.elfiumexpress.storage;

import org.springframework.stereotype.Component;

/**
 * Настройки хранилища
 */
@Component
public class StorageProperties {
    /**
     * Folder location for storing files
     */
    private String location = "content/files";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
