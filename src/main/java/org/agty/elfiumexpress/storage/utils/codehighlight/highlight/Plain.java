package org.agty.elfiumexpress.storage.utils.codehighlight.highlight;

import org.agty.elfiumexpress.storage.utils.ContentUtils;
import org.agty.elfiumexpress.storage.utils.codehighlight.Highlight;

public class Plain implements Highlight {
    @Override
    public String highlight(String text) {
        return ContentUtils.nl2br(text);
    }
}
