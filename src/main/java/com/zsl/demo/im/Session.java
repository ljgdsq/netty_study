package com.zsl.demo.im;

import java.util.Objects;

public class Session {
    private int id;
    private String userName;


    public Session() {
    }

    public Session(int id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return id == session.id &&
                Objects.equals(userName, session.userName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userName);
    }
}
