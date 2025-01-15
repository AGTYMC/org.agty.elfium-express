package org.agty.elfiumexpress.modules.express.repository;

import org.agty.agtysql.data.Arguments;
import org.agty.agtysql.interfaces.SqlRow;
import org.agty.elfiumexpress.dao.PermanentConnection;
import org.agty.elfiumexpress.modules.express.common.ExpressType;
import org.agty.elfiumexpress.modules.express.entity.ExpressPanel;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public class ExpressTypeRepo {
    /**
     * Get the panel type by ID
     * @param id ID panel
     * @return ExpressPanel
     */
    public ExpressPanel getById(Long id) {
        SqlRow row = PermanentConnection.getConnection().fetch(new Arguments().setTable("{express_type}").setWhere("id_type = " + id));
        return ExpressPanel.rowToEntity(row);
    }

    /**
     * Get a collection of panel's types into a group
     * @return collection of panel's types
     */
    public List<ExpressType> findAll() {
        List<ExpressType> expressType = new LinkedList<>();

        List<SqlRow> list = PermanentConnection.getConnection().listArray(new Arguments().setTable("{express_type}").setOrderBy("id_type ASC"));

        for(SqlRow row : list) {
            expressType.add(ExpressType.rowToEntity(row));
        }

        if (PermanentConnection.getConnection().hasErrors()) System.out.println(PermanentConnection.getConnection().getErrors());

        return expressType;
    }
}
