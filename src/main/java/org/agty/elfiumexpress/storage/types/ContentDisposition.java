package org.agty.elfiumexpress.storage.types;

public class ContentDisposition {
    public static final String ATTACHMENT = "attachment";
    public static final String INLINE = "inline";

    /**
     * Get the Content Disposition By Identity
     * @param identity string: image, pdf, etc.
     * @return Content disposition: inline, attachment, etc.
     */
    public static String getContentDispositionByIdentity(String identity) {
        if (identity.equals("image") || identity.equals("pdf")) {
            return ContentDisposition.INLINE;
        }
        return ContentDisposition.ATTACHMENT;
    }

    /**
     * Get the Content Disposition By Content-Type
     * @param contentType string: "application/pdf", "image/gif", etc.
     * @return Content disposition: inline, attachment, etc.
     */
    public static String getContentDispositionByContentType(String contentType) {
        return getContentDispositionByIdentity(FileTypes.identifyByContentType(contentType));
    }

    /**
     * Get the Content Disposition By Extension
     * @param ext string: "pdf", "gif", etc.
     * @return Content disposition: inline, attachment, etc.
     */
    public static String getContentDispositionByExtension(String ext) {
        return getContentDispositionByIdentity(FileTypes.identifyByExtension(ext));
    }

    /**
     * Get the Content Disposition By Extension or Content-Type
     * @param ext string: "pdf", "gif", etc.
     * @param contentType string: "application/pdf", "image/gif", etc.
     * @return Content disposition: inline, attachment, etc.
     */
    public static String getContentDispositionByExtOrContentType(String ext, String contentType) {
        String identify;

        if (!(identify = getContentDispositionByExtension(ext)).equals(ContentDisposition.ATTACHMENT)) return identify;
        if (!(identify = getContentDispositionByContentType(contentType)).equals(ContentDisposition.ATTACHMENT)) return identify;

        return ContentDisposition.ATTACHMENT;
    }
}
