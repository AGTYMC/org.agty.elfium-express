package org.agty.elfiumexpress.modules.express.enums;

public enum Types {
    URI(1), BOOKMARK(2), FILE(3);

    private final int type;

    Types(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
