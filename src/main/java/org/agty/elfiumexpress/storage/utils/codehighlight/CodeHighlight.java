package org.agty.elfiumexpress.storage.utils.codehighlight;

public class CodeHighlight {
    private final HighlightFactory highlightFactory = new HighlightFactory();

    public String highlight(String code, String language) {
        Highlight highlight = highlightFactory.getHighlight(language);
        return highlight.highlight(code);
    }
}
