package org.agty.elfiumexpress.storage.utils;

public class ContentUtils {
    public static String nl2br(String text) {
        return text.replaceAll("\n", "<br />");
    }

    public static String nl2p(String text) {
        return text.replaceAll("\n\n", "<p />");
    }
}
