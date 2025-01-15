package org.agty.elfiumexpress.storage.thumbs;

import java.util.ArrayList;
import java.util.List;

public class ThumbsObserver {
    private static ThumbsObserver instance;
    private static final List<String> thumbsInCreate = new ArrayList<>();

    public static synchronized void registerCreator(String thumb) {
        thumbsInCreate.add(thumb);
    }

    public static synchronized void unregisterCreator(String thumb) {
        thumbsInCreate.remove(thumb);
    }

    public static synchronized boolean thumbInProgress(String thumb) {
        return thumbsInCreate.contains(thumb);
    }
}
