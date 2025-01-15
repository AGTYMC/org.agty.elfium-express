package org.agty.elfiumexpress.modules.express.view.files;

import org.agty.elfiumexpress.modules.express.view.Files;
import org.agty.elfiumexpress.storage.entity.UploadedFile;

public class Audio implements Files {
    @Override
    public String getContent(UploadedFile file) {
        StringBuilder content = new StringBuilder();
        content.append("<div class=\"express-file-audio\">");

        content.append("<div class=\"express-file-audio-file\">");
        content.append(     "<audio preload=\"none\" controls>");
        content.append(         "<source src=\"/content/files/").append(file.getFile()).append("\" type=\"").append(file.getContentType()).append("\" />");
        content.append(     "</audio>");
        content.append("</div>");

        content.append(File.fileInfoBlock(file));

        content.append("</div>");
        return content.toString();
    }
}
