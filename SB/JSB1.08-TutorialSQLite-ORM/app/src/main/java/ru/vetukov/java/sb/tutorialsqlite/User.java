package ru.vetukov.java.sb.tutorialsqlite;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = User.TABLE_NAME)
public class User {

    public static final String TABLE_NAME           = "User";
    public static final String COLOMN_NAME_ID       = "_id";
    public static final String COLOMN_NAME_FNAME    = "fname";

    @DatabaseField(generatedId = true, columnName = COLOMN_NAME_ID)
    private long mId;
    @DatabaseField(columnName = COLOMN_NAME_FNAME)
    private String mFName;

    public static String getTableName() {
        return TABLE_NAME;
    }

    public static String getColomnNameId() {
        return COLOMN_NAME_ID;
    }

    public static String getColomnNameFname() {
        return COLOMN_NAME_FNAME;
    }

    public long getmId() {
        return mId;
    }

    public void setmId(long mId) {
        this.mId = mId;
    }

    public String getmFName() {
        return mFName;
    }

    public void setmFName(String mFName) {
        this.mFName = mFName;
    }
}
