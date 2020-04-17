package it;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

public class Student extends Human implements Comparable, Soldier, Serializable {
    private Group group;
    private int course;
    private double averageRating;
    private int age;

    public Student(String surname, String name, boolean male, int course, double averageRating, int age) {
        super(surname, name, male, age);
        if (course > 0 && course <= 6) {
            this.course = course;
        } else {
            System.out.println("Invalid course number of student: " + getName() + " " + getSurname());
        }
        if (averageRating > 0 && averageRating <= 5) {
            this.averageRating = averageRating;
        } else {
            System.out.println("Invalid averageRating of student: " + getName() + " " + getSurname());
        }
    }

    @Override
    public String toString() {
        return "Student{" + super.toString() +
                ", group=" + group.getGroupName() +
                ", course=" + course +
                ", averageRating=" + averageRating +
                '}';
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        if (course > 0 && course <= 6) this.course = course;
        else System.out.println("Invalid course number");
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        if (averageRating > 0 && averageRating <= 5) {
            this.averageRating = averageRating;
        } else {
            System.out.println("Invalid averageRating");
        }
    }

    public void save(PrintWriter pw){
        pw.println(getName());
        pw.println(getSurname());
        pw.println(getAge());
        pw.println(isMale());
        pw.println(getCourse());
        pw.println(getAverageRating());
    }

    public static Student createFromBufferedReader(BufferedReader r) throws IOException {
        String name = r.readLine();
        String surname = r.readLine();
        Integer age =  Integer.parseInt(r.readLine());
        Boolean male = Boolean.parseBoolean(r.readLine());
        Integer course = Integer.parseInt(r.readLine());
        Double avg = Double.parseDouble(r.readLine());
        return new Student(surname, name, male, course, avg, age);
    }

    @Override
    public int compareTo(Object o) {
        if (o == null) {
            return -1;
        }
        Human anotherHuman = (Human) o;
        return this.getSurname().compareToIgnoreCase(anotherHuman.getSurname());
    }

    @Override
    public void messageFromVoenkom() {
        System.out.println("new message from Voencom:");
        System.out.println("Soldier " + this.getSurname() + " Welcome to the army!!!");
    }

}