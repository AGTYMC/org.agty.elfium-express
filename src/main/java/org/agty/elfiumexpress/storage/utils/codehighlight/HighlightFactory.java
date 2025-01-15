package org.agty.elfiumexpress.storage.utils.codehighlight;

import org.agty.elfiumexpress.storage.utils.codehighlight.highlight.Php;
import org.agty.elfiumexpress.storage.utils.codehighlight.highlight.Plain;

public class HighlightFactory {
    public Highlight getHighlight(String language) {
        switch (language) {
            case "php": return new Php();
        }
        return new Plain();
    }
}
