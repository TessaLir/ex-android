package ru.vetukov.java.sb.homesqlite;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = Employee.TABLE_NAME)
public class Employee {

    public static final String TABLE_NAME = "Employee";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_FNAME = "fname";
    public static final String COLUMN_LNAME = "lname";
    public static final String COLUMN_PATR = "patronymic";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";

    @DatabaseField(generatedId = true, columnName = COLUMN_ID)
    private long mId;
    @DatabaseField(columnName = COLUMN_FNAME)
    private String mFName;
    @DatabaseField(columnName = COLUMN_LNAME)
    private String mLname;
    @DatabaseField(columnName = COLUMN_PATR)
    private String mPatron;
    @DatabaseField(columnName = COLUMN_PHONE)
    private String mPhone;
    @DatabaseField(columnName = COLUMN_EMAIL)
    private String mEmail;


    public long getmId() {
        return mId;
    }

    public String getmFName() {
        return mFName;
    }

    public String getmLname() {
        return mLname;
    }

    public String getmPatron() {
        return mPatron;
    }

    public String getmPhone() {
        return mPhone;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmId(long mId) {
        this.mId = mId;
    }

    public void setmFName(String mFName) {
        this.mFName = mFName;
    }

    public void setmLname(String mLname) {
        this.mLname = mLname;
    }

    public void setmPatron(String mPatron) {
        this.mPatron = mPatron;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }
}
