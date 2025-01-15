package org.agty.elfiumexpress.modules.express.common;

import org.agty.agtysql.interfaces.SqlRow;

public class ExpressType {
    private Long idType;
    private String title;

    public static ExpressType rowToEntity(SqlRow row) {
        ExpressType expressType = new ExpressType();
        expressType.setTitle(row.getString("title"));
        expressType.setIdType(row.getLong("id_type"));
        return expressType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getIdType() {
        return idType;
    }

    public void setIdType(Long idType) {
        this.idType = idType;
    }

    public static String getIconByType(Long idType) {
        if (idType != null && idType == 1L) {
            return "rhombus-tech.png";
        } else if (idType != null && idType == 2L) {
            return "rhombus-bookmark.png";
        }
        return "rhombus-blank.png";
    }
}
