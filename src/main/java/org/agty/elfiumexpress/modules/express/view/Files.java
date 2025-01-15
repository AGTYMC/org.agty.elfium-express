package org.agty.elfiumexpress.modules.express.view;

import org.agty.elfiumexpress.storage.entity.UploadedFile;

public interface Files {
    String getContent(UploadedFile file);
}
