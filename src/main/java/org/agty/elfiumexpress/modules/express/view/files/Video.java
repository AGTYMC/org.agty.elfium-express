package org.agty.elfiumexpress.modules.express.view.files;

import org.agty.elfiumexpress.modules.express.view.Files;
import org.agty.elfiumexpress.storage.entity.UploadedFile;

public class Video implements Files {
    @Override
    public String getContent(UploadedFile file) {
        StringBuilder content = new StringBuilder();
        content.append("<div class=\"express-file-video\">");

        content.append("<div class=\"express-file-video-file\">");
        content.append(     "<video preload=\"yes\" controls loop=\"loop\">");
        content.append(         "<source src=\"/content/files/").append(file.getFile()).append("\" type=\"video/").append(file.getExtension()).append("\" />");
        content.append(     "</video>");
        content.append("</div>");

        content.append(File.fileInfoBlock(file, "_blank"));

        content.append("</div>");
        return content.toString();
    }
}
