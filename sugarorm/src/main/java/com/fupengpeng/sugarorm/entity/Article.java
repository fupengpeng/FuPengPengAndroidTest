package com.fupengpeng.sugarorm.entity;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.io.Serializable;

/**
 * Created by fupengpeng on 2017/5/24 0024.
 */

public class Article extends SugarRecord  implements Serializable{
    @Unique
    private String username;
    private String password;
    private String uid;

    public Article(){}

    public Article(String username, String password, String uid) {
        this.username = username;
        this.password = password;
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Article{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", uid='" + uid + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
