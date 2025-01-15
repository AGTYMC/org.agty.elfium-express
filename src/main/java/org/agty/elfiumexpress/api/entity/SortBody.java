package org.agty.elfiumexpress.api.entity;

public class SortBody {
    private String id;
    private int align;

    public SortBody(String id, int align) {
        this.id = id;
        this.align = align;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAlign() {
        return align;
    }

    public void setAlign(int align) {
        this.align = align;
    }
}
