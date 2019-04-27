package com.gdetotut.skillbox.project0804ormlite;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = Article.TABLE_NAME)
public class Article {

	static final String TABLE_NAME = "Article";
	private static final String COLUMN_ID = "_id";
	private static final String COLUMN_TITLE = "title";
	private static final String COLUMN_TEXT = "text";

	@DatabaseField(generatedId = true, columnName = COLUMN_ID)
	private long mId;

	@DatabaseField(columnName = COLUMN_TITLE)
	private String mTitle;

	@DatabaseField(columnName = COLUMN_TEXT)
	private String mText;

	public long getId() {
		return mId;
	}

	public void setId(final long mId) {
		this.mId = mId;
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(final String mTitle) {
		this.mTitle = mTitle;
	}

	public String getText() {
		return mText;
	}

	public void setText(final String mText) {
		this.mText = mText;
	}

}
