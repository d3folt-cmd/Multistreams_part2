package it;

import java.io.Serializable;

public class Human implements Serializable {
    private String name;
    private String surname;
    private int age;
    private boolean male;

    public Human(String surname_, String name_, boolean male_, int age_) {
        if(surname_!=null){
            this.surname = surname_;
        }
        else{
            System.out.println("Invalid surname");
        }
        if(name_!=null) {
            this.name = name_;
        }
        else{
            System.out.println("Invalid name");
        }
        this.male = male_;
        this.age = age_;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name!=null) {
            this.name = name;
        }
        else{
            System.out.println("Invalid name");
        }
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if(surname!=null){
            this.surname = surname;
        }
        else{
            System.out.println("Invalid surname");
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if(age > 0 && age <100) this.age = age;
        else System.out.println("Invalid age");
    }


    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }


    @Override
    public String toString() {
        return
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", male=" + male;
    }

}
