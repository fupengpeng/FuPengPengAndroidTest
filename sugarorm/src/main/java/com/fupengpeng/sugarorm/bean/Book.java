package com.fupengpeng.sugarorm.bean;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

/**
 * Created by fupengpeng on 2017/5/24 0024.
 */

public class Book extends SugarRecord {

    @Unique
    private String isbn;
    private String title;
    private String edition;

    public Book(){}

    public Book(String isbn, String title, String edition) {
        this.isbn = isbn;
        this.title = title;
        this.edition = edition;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", edition='" + edition + '\'' +
                '}';
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }
}
