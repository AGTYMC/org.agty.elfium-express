package org.agty.elfiumexpress.storage.repository;

import org.agty.agtysql.data.Arguments;
import org.agty.agtysql.interfaces.SqlRow;
import org.agty.elfiumexpress.dao.PermanentConnection;
import org.agty.elfiumexpress.storage.entity.Thumb;
import org.agty.utils.AgtyUtils;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ThumbsRepo {
    public void save(Thumb thumb) {
        if (!thumbIsExist(thumb.getThumb())) {
            Arguments arguments = new Arguments().setTable("{thumbs}").setData("file", thumb.getFile()).setData("thumb", thumb.getThumb());
            PermanentConnection.getConnection().insert(arguments);
        }
    }

    public List<Thumb> getThumbListByFile(String file) {
        List<Thumb> thumbs = new ArrayList<>();

        Arguments arguments = new Arguments().setTable("{thumbs}").setWhere("file = '" + AgtyUtils.hencode(file) + "'");
        List<SqlRow> rows = PermanentConnection.getConnection().findAll(arguments);
        for (SqlRow row : rows) {
            thumbs.add(Thumb.rowToThumb(row));
        }

        return thumbs;
    }

    public Thumb getThumb(String thumbName) {
        Arguments arguments = new Arguments().setTable("{thumbs}").setWhere("thumb = '" + AgtyUtils.hencode(thumbName) + "'");
        return Thumb.rowToThumb(PermanentConnection.getConnection().fetch(arguments));
    }

    public boolean thumbIsExist(String thumbName) {
        Thumb thumb = getThumb(thumbName);
        return thumb.getId() != null;
    }

    public void deleteThumb(Long id) {
        PermanentConnection.getConnection().delete(new Arguments().setTable("{thumbs}").setWhere("id_thumbs = " + id));
    }

    public void deleteThumbByFile(String file) {
        List<Thumb> thumbs = getThumbListByFile(file);

        if (!thumbs.isEmpty()) {
            for (Thumb thumb : thumbs) {

                deleteThumb(thumb.getId());

                try {
                    Files.deleteIfExists(Path.of("content/files/users/0/thumbs/" + thumb.getThumb()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
