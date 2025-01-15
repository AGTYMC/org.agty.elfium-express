package org.agty.elfiumexpress.dao;

import org.agty.agtysql.AgtySQL;

public class PermanentConnection {
    public static int connectionCount;
    private static final AgtySQL agtySQL = new AgtySQL();
    public static AgtySQL getConnection() {
        /*System.out.println("connectionCount: " + connectionCount);
        connectionCount++;*/
        return agtySQL;
    }
}
