package ru.vetukov.java.core.abstracts;

import java.util.Calendar;

public abstract class AbstractOperation {

    private long id;
    private Calendar dateTime;
    private String description;

    public AbstractOperation() {
    }

    public AbstractOperation(long id, Calendar dateTime, String description) {
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
    }

    public AbstractOperation(long id) {
        this.id = id;
    }

    public AbstractOperation(Calendar dateTime, String description) {
        this.dateTime = dateTime;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Calendar getDateTime() {
        return dateTime;
    }

    public void setDateTime(Calendar dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
