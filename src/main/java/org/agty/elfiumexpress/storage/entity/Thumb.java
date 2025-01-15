package org.agty.elfiumexpress.storage.entity;

import org.agty.agtysql.interfaces.SqlRow;

public class Thumb {
    private Long id_thumbs;
    private String thumb;
    private String file;

    public Thumb() {}

    public Thumb(String thumb, String file) {
        this.thumb = thumb;
        this.file = file;
    }

    public static Thumb rowToThumb(SqlRow row) {
        Thumb thumb = new Thumb();
        thumb.setId(row.getLong("id_thumbs"));
        thumb.setFile(row.getString("file"));
        thumb.setThumb(row.getString("thumb"));
        return thumb;
    }

    public Long getId() {
        return id_thumbs;
    }

    public void setId(Long id_thumbs) {
        this.id_thumbs = id_thumbs;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
