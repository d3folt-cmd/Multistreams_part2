package it;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Student st1 = new Student("Surname1","Alex",true,3,4.0,17);
        Student st2 = new Student("LSurname2", "Max", true, 3,5.0 ,22);
        Student st3 = new Student("ASurname3", "Anya", false, 3,3.5 ,20);
        Student st4 = new Student("BSurname4", "Egor", true, 3,3.7 ,17);
        Student st5 = new Student("GSurname5", "Nastya", false, 3, 4.3,16);

        System.out.println();

        Group group = new Group("Univer");
        group.addStudent(st1);
        group.addStudent(st2);
        group.addStudent(st3);
        group.addStudent(st4);
        group.addStudent(st5);
        System.out.println();

        try {
            group.saveToFile(new File("D:\\result1.txt"));
            Group group2 = Group.read(new FileReader("D:\\result1.txt"));
            System.out.println(group2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        group.sortSurnameAndPrint();

        group.getStudentValidForArmy();

        sendInviteToArmy(group);
    }

    public static void sendInviteToArmy(Voenkom soldierGroup){ //Военкомат рассматривает группу студентов как группу военно обязанных
        Soldier[] soldierArr = soldierGroup.getStudentValidForArmy(); //Группу годных служить как солдат
        for(Soldier soldier: soldierArr){
            soldier.messageFromVoenkom();
        }
    }

}
