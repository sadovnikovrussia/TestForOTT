package dev.sadovnikov.testforott;

import android.support.annotation.NonNull;

public class Company {
    private long id;
    private String name;

    Company(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @NonNull
    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
