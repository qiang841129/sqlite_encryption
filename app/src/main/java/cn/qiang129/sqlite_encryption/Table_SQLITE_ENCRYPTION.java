package cn.qiang129.sqlite_encryption;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "T_ZH_FLXJ_XSFW")
public class Table_SQLITE_ENCRYPTION {
    public static final String table_name = "T_ZH_FLXJ_XSFW";
    public static final String col_OBJ_ID = "OBJ_ID";
    public static final String col_ZY = "ZY";
    public static final String col_JL = "JL";

    //ID
    @DatabaseField(columnName = "OBJ_ID", id = true)
    private String OBJ_ID;

    //专业（1输2变3配）
    @DatabaseField(columnName = "ZY")
    private String ZY;

    //巡视范围距离
    @DatabaseField(columnName = "JL")
    private String JL;

    public String getOBJ_ID() {
        return OBJ_ID;
    }

    public void setOBJ_ID(String OBJ_ID) {
        this.OBJ_ID = OBJ_ID;
    }

    public String getZY() {
        return ZY;
    }

    public void setZY(String ZY) {
        this.ZY = ZY;
    }

    public String getJL() {
        return JL;
    }

    public void setJL(String JL) {
        this.JL = JL;
    }
}
