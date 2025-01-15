package org.agty.elfiumexpress.modules.express.view.files;

import org.agty.elfiumexpress.modules.express.view.Files;
import org.agty.elfiumexpress.storage.entity.UploadedFile;

public class Pdf implements Files {
    @Override
    public String getContent(UploadedFile file) {
        StringBuilder content = new StringBuilder();
        content.append("<div class=\"express-file-pdf\">");

        content.append("<div class=\"express-file-pdf-file\">");
        content.append(     "<iframe src=\"/content/files/").append(file.getFile()).append("\"></iframe>");
        content.append("</div>");

        content.append(File.fileInfoBlock(file, "_blank"));

        content.append("</div>");
        return content.toString();
    }
}
