package org.agty.elfiumexpress.modules.express.repository;

import org.agty.agtysql.data.Arguments;
import org.agty.agtysql.interfaces.SqlRow;
import org.agty.elfiumexpress.dao.PermanentConnection;
import org.agty.elfiumexpress.api.entity.ActionItem;
import org.agty.elfiumexpress.api.entity.SortBody;
import org.agty.elfiumexpress.modules.express.entity.ExpressGroup;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public class ExpressGroupRepo {
    /**
     * Get the group by id
     * @param id ID group
     * @return ExpressGroup
     */
    public ExpressGroup getById(Long id) {
        SqlRow row = PermanentConnection.getConnection().fetch(new Arguments().setTable("{groups}").setWhere("id_group = " + id));
        return ExpressGroup.rowToEntity(row);
    }

    /**
     * Insert or update the group
     * @param expressGroup The ExpressGroup entity
     * @return true if no errors
     */
    public boolean save(ExpressGroup expressGroup) {
        Arguments arguments = new Arguments();
        arguments.setTable("{groups}");
        arguments.setData("r_group", expressGroup.getRGroup());
        arguments.setData("title", expressGroup.getTitle());
        arguments.setData("comment", expressGroup.getComment());

        if (expressGroup.hasIdGroup()) {
            arguments.setWhere("[id_group] = " + expressGroup.getIdGroup());
            PermanentConnection.getConnection().update(arguments);
        } else {
            Long maxAlign = PermanentConnection.getConnection().max(new Arguments().setTable("{groups}").setWhere("r_group=" + expressGroup.getRGroup()).setActionField("align"));

            arguments.setData("align", maxAlign != null ? ++maxAlign : 0);

            PermanentConnection.getConnection().insert(arguments);
        }

        return !PermanentConnection.getConnection().hasErrors();
    }

    /**
     * Get a collection of groups
     * @param idRootGroup ID parent's group
     * @return List collection
     */
    public List<ExpressGroup> findAll(Long idRootGroup) {
        List<ExpressGroup> expressGroups = new LinkedList<>();

        Arguments arguments = new Arguments();
        arguments.setTable("{groups}");
        arguments.setWhere("[r_group] = " + idRootGroup);
        arguments.setOrderBy("align ASC, title ASC");

        List<SqlRow> rows = PermanentConnection.getConnection().findAll(arguments);
        for (SqlRow row : rows) {
            expressGroups.add(ExpressGroup.rowToEntity(row));
        }

        if (PermanentConnection.getConnection().hasErrors()) {
            System.out.println(PermanentConnection.getConnection().getErrors());
        }

        return expressGroups;
    }

    /**
     * Delete the group
     * @param idGroup ID
     */
    public void del(Long idGroup) {
        PermanentConnection.getConnection().delete(new Arguments().setTable("{groups}").setWhere("id_group = " + idGroup));
    }

    /**
     * Get the collection of all parents groups
     * @param idGroup ID group then starts
     * @return List Colletctio
     */
    public LinkedList<ExpressGroup> fullPath(Long idGroup) {
        LinkedList<ExpressGroup> expressGroups = new LinkedList<>();

        getFullPath(idGroup, expressGroups);

        return expressGroups;
    }

    private void getFullPath(Long idGroup, LinkedList<ExpressGroup> expressGroups) {
        SqlRow row = PermanentConnection.getConnection().fetch(new Arguments().setTable("{groups}").setWhere("id_group = " + idGroup));
        expressGroups.addFirst(ExpressGroup.rowToEntity(row));

        if (row.getLong("r_group") != null) {
            getFullPath(row.getLong("r_group"), expressGroups);
        }
    }

    /**
     * API. Sorting groups
     * @param bodies list items
     */
    public void sort(SortBody[] bodies) {
        for(SortBody body : bodies) {
            PermanentConnection.getConnection().update(
                    new Arguments()
                            .setTable("{groups}")
                            .setData("align", body.getAlign())
                            .setWhere("id_group = " + body.getId())
            );
        }
    }

    /**
     * API. Move the group into another group
     * @param actionItem item
     */
    public void move(ActionItem actionItem) {
        Long maxAlign = PermanentConnection.getConnection().max(new Arguments().setTable("{groups}").setWhere("r_group=" + actionItem.getDst()).setActionField("align"));

        PermanentConnection.getConnection().update(
                new Arguments()
                        .setTable("{groups}")
                        .setData("r_group", actionItem.getDst())
                        .setData("align", maxAlign != null ? ++maxAlign : 0)
                        .setWhere("id_group = " + actionItem.getSrc())
        );
    }
}
