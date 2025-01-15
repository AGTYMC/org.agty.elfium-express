package org.agty.elfiumexpress.api.entity;

public class ActionItem {
    private String object;
    private int src;
    private int dst;

    public ActionItem(String object, int src, int dst) {
        this.object = object;
        this.src = src;
        this.dst = dst;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public int getSrc() {
        return src;
    }

    public void setSrc(int src) {
        this.src = src;
    }

    public int getDst() {
        return dst;
    }

    public void setDst(int dst) {
        this.dst = dst;
    }
}
