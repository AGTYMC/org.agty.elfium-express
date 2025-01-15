package org.agty.elfiumexpress.modules.express.repository;

import org.agty.agtysql.data.Arguments;
import org.agty.agtysql.interfaces.SqlRow;
import org.agty.elfiumexpress.api.entity.ActionItem;
import org.agty.elfiumexpress.api.entity.SortBody;
import org.agty.elfiumexpress.dao.PermanentConnection;
import org.agty.elfiumexpress.modules.express.entity.ExpressPanel;
import org.agty.elfiumexpress.storage.entity.UploadedFile;
import org.agty.elfiumexpress.storage.repository.FileUploadRepo;
import org.agty.elfiumexpress.storage.utils.UploadedFileUtils;
import org.agty.elfiumexpress.utils.StringBuilderExtend;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public class ExpressPanelRepo {
    private FileUploadRepo fileUploadRepo;

    public ExpressPanelRepo(FileUploadRepo fileUploadRepo) {
        this.fileUploadRepo = fileUploadRepo;
    }

    /**
     * Get the panel by ID
     * @param id ID panel
     * @return ExpressPanel
     */
    public ExpressPanel getById(Long id) {
        SqlRow row = PermanentConnection.getConnection().fetch(new Arguments().setTable("{express}").setWhere("id_express = " + id));
        ExpressPanel expressPanel = ExpressPanel.rowToEntity(row);
        if (expressPanel.getIdExpress() != null) {
            expressPanel.setFiles( findUploadedFiles(expressPanel.getIdExpress()) );
        }
        return expressPanel;
    }

    /**
     * Get a collection of panels into a group
     * @param idGroup ID group
     * @return collection of panels
     */
    public List<ExpressPanel> findAll(Long idGroup) {
        List<ExpressPanel> expressPanels = new LinkedList<>();

        String query = getListQuery(
                new Arguments()
                        .setWhere("express.id_group = " + idGroup)
                        .setOrderBy("express.align ASC, express.title ASC")
        );

        List<SqlRow> list = PermanentConnection.getConnection().listArray(new Arguments().setQuery(query));

        for(SqlRow row : list) {
            expressPanels.add(ExpressPanel.rowToEntity(row));
        }

        if (PermanentConnection.getConnection().hasErrors()) System.out.println(PermanentConnection.getConnection().getErrors());

        return expressPanels;
    }

    /**
     * Get a collection of panel files
     * @param idPanel ID panel
     * @return collection of files
     */
    public List<UploadedFile> findUploadedFiles(Long idPanel) {
        Arguments arguments = new Arguments().setQuery("SELECT files.id_file, files.name, files.file, files.content_type, files.size, files.ext FROM {express_files} as expfiles LEFT JOIN {files} as files ON (files.id_file = expfiles.id_file) WHERE expfiles.id_express = " + idPanel + " ORDER BY files.id_file ASC");

        List<SqlRow> list = PermanentConnection.getConnection().listArray(arguments);
        List<UploadedFile> files = new LinkedList<>();

        for(SqlRow row : list) {
            files.add( UploadedFileUtils.rowToUploadedFile(row) );
        }

        if (PermanentConnection.getConnection().hasErrors()) System.out.println(PermanentConnection.getConnection().getErrors());

        return files;
    }

    /**
     * Insert or update the panel
     * @param expressPanel The ExpressGroup entity
     * @return Long ID panel
     */
    public Long save(ExpressPanel expressPanel) {
        Long idPanel;
        Arguments arguments = new Arguments();

        arguments.setTable("{express}");
        arguments.setData("id_group", expressPanel.getIdGroup());
        arguments.setData("title", expressPanel.getTitle());
        arguments.setData("about", expressPanel.getAbout());
        arguments.setData("uri", expressPanel.getUri());
        arguments.setData("id_type", expressPanel.getIdType());
        arguments.setData("body", expressPanel.getBody());

        if (!expressPanel.hasId()) {
            Long maxAlign = PermanentConnection.getConnection().max(new Arguments().setTable("{express}").setWhere("id_group=" +expressPanel.getIdGroup()).setActionField("align"));

            arguments.setData("align", maxAlign != null ? ++maxAlign : 0);

            PermanentConnection.getConnection().insert(arguments);
            idPanel = PermanentConnection.getConnection().lastInsertId(new Arguments().setTable("{express}"));
        } else {
            arguments.setWhere("[id_express] = " + expressPanel.getIdExpress());
            PermanentConnection.getConnection().update(arguments);
            idPanel = expressPanel.getIdExpress();
        }

        if (idPanel != null) {
            saveFiles(expressPanel.getFiles(), idPanel);
        } else {
            //TODO: удалять загруженные файлы
        }

        return idPanel;
    }

    private void saveFiles(List<UploadedFile> files, Long idPanel) {
        for (UploadedFile file : files) {
            Long idFile;

            if (file.idExists()) idFile = file.getIdFile();
            else idFile = fileUploadRepo.save(file);

            if (idFile != null) refToFile(idPanel, idFile);
        }
    }

    private void refToFile(Long idPanel, Long idFile) {
        Arguments arguments = new Arguments().setTable("{express_files}")
                .setData("id_express", idPanel).setData("id_file", idFile)
                .setWhere("id_express = " + idPanel + " AND id_file = " + idFile);

        if (!PermanentConnection.getConnection().rowIsExists(arguments)) {
            PermanentConnection.getConnection().insert(arguments);
        }
    }

    /**
     * Delete the panel
     * @param idPanel ID
     */
    public void del(Long idPanel) {
        PermanentConnection.getConnection().delete(new Arguments().setTable("{express}").setWhere("[id_express] = " + idPanel));
    }

    /**
     * API. Sorting panels
     * @param bodies list items
     */
    public void sort(SortBody[] bodies) {
        for(SortBody body : bodies) {
            PermanentConnection.getConnection().update(
                    new Arguments()
                            .setTable("{express}")
                            .setData("align", body.getAlign())
                            .setWhere("id_express = " + body.getId())
            );
        }
    }

    /**
     * API. Move the panel into a group
     * @param actionItem item
     */
    public void move(ActionItem actionItem) {
        Long maxAlign = PermanentConnection.getConnection().max(new Arguments().setTable("{express}").setWhere("id_group=" + actionItem.getDst()).setActionField("align"));

        PermanentConnection.getConnection().update(
                new Arguments()
                        .setTable("{express}")
                        .setData("id_group", actionItem.getDst())
                        .setData("align", maxAlign != null ? ++maxAlign : 0)
                        .setWhere("id_express = " + actionItem.getSrc())
        );
    }

    /**
     * List query
     * @param arguments Arguments
     * @return query
     */
    private String getListQuery(Arguments arguments) {
        StringBuilderExtend query = new StringBuilderExtend();

        query.appendAndSpaceRight("SELECT");

        query.appendAndSpaceRight("express.id_express,");
        query.appendAndSpaceRight("express.date,");
        query.appendAndSpaceRight("express.title,");
        query.appendAndSpaceRight("express.about,");
        query.appendAndSpaceRight("express.uri,");
        query.appendAndSpaceRight("express.id_type,");
        query.appendAndSpaceRight("express.body,");
        query.appendAndSpaceRight("express.id_group,");
        query.appendAndSpaceRight("groups.title as group_title");

        query.appendAndSpaceRight("FROM {express} as express");
        query.appendAndSpaceRight("LEFT JOIN {groups} as groups ON (groups.id_group = express.id_group)");

        if (arguments.hasWhere()) {
            query.appendAndSpaceRight("WHERE");
            query.appendAndSpaceRight(arguments.getWhere());
        }

        if (arguments.hasOrderBy()) {
            query.appendAndSpaceRight("ORDER BY");
            query.appendAndSpaceRight(arguments.getOrderBy());
        }

        return query.toString();
    }
}
