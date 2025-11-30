package com.enixyu.sqlite.model;

import androidx.annotation.NonNull;

public class Student {

    private int id;
    private String name;
    private int age;
    private String grade;
    private String sex;
    private String store;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Student() {

    }

    public Student(String name, int age, String grade, String sex,String store) {
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.sex = sex;
        this.store=store;
    }

    @NonNull
    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", grade='" + grade + '\'' +
                ", sex='" + sex + '\'' +
                ", store='" + store + '\'' +
                '}';
    }
}
