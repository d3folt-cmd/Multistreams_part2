package it;
import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class Group implements Voenkom, Serializable {
    private String groupName = "?";
    private Student[] groupList = new Student[10];
    private int count = 0;

    public Group() {
    }

    public Group(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }


    public void addStudent(Student newStudent) {
        if (newStudent.getGroup() == null) {
            boolean isAdd = false;
            for (int i = 0; i < groupList.length; i++) {
                if (groupList[i] == null) {
                    groupList[i] = newStudent;
                    newStudent.setGroup(this);
                    System.out.println("Student " + newStudent.getName() + " is added to the group: " + groupName);
                    isAdd = true;
                    count++;
                    break;
                }
            }
            try {
                if (!isAdd) {
                    throw new FullGroupException();
                }
            } catch (FullGroupException e) {
                System.out.println("The group is full ");
            }

        } else
            System.out.println("Student " + newStudent.getName() + " already studying in group: " + newStudent.getGroup().getGroupName());
    }


    public void interactiveAddStudent() { // интерактивное добавление студента
        int freePlace = -1;
        for (int i = 0; i < groupList.length; i++) {
            if (groupList[i] == null) {
                freePlace = i;
                System.out.println("Free place was found in group " + groupName + ". Please enter student data ");
                break;
            }
        }
        if (freePlace < 0) {
            System.out.println("The group is full");
        }
    }


    public void delStudent(String surname) { //удаление по фамилии
        int countSt = 0;
        for (Student st : groupList) {
            if (st != null && st.getSurname().equals(surname)) {
                countSt++;
            }
        }
        if (countSt == 0) {
            System.out.println("No student " + surname + " in " + groupName + " group"); //если студентов нет
        }
        if (countSt == 1) {
            for (int i = 0; i < groupList.length; i++) {
                if (groupList[i] != null && groupList[i].getSurname().equals(surname)) {
                    groupList[i].setGroup(null);
                    groupList[i] = null;
                    count--;
                    System.out.println("Student " + surname + " is deleted from the group" + groupName);
                    break;
                }
            }
        }
        if (countSt > 1) {
            System.out.println("Student " + surname + " in group " + groupName + " more then 1!");
            searchStudent(surname);
            System.out.println("Please use the method delStudent(int number)");
        }
    }

    public void delStudent(int number) { //удаляем студента по номеру в группе
        try {
            String bufferStudSurname = groupList[number - 1].getSurname();
            groupList[number - 1].setGroup(null);
            groupList[number - 1] = null;
            count--;
            sortGroupListAfterDelStudent();
            System.out.println("Student " + bufferStudSurname + " is deleted from the group" + groupName);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid student number");
        }
    }

    private void sortGroupListAfterDelStudent() { // убираем дырку null в массиве после удаления
        for (int i = 0; i < groupList.length - 1; i++) {
            if (groupList[i] == null && groupList[i + 1] != null) {
                groupList[i] = groupList[i + 1];
                groupList[i + 1] = null;
            }
        }
    }

    public void searchStudent(String surname) { //поиск всех студентов с такой фамилией
        boolean isFound = false;
        for (int i = 0; i < groupList.length; i++) {
            if (groupList[i] != null && groupList[i].getSurname().equals(surname)) {
                System.out.println("Student " + surname + " is found in group. List number is " + (i + 1) + ".");
                isFound = true;
            }
        }
        if (!isFound) {
            System.out.println("No student " + surname + " in " + groupName + " group");
        }
    }


    private class FullGroupException extends Exception {
    }


    private Student[] arrWithoutNull(Student[] arr) {
        int stCounter = 0;
        for (Student st : arr) {
            if (st == null) {
                break;
            }
            stCounter++;
        }
        Student[] sortList = new Student[stCounter];
        System.arraycopy(arr, 0, sortList, 0, stCounter);
        return sortList;
    }

    String stListToString(Student[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append("groupName=");
        sb.append(groupName);
        sb.append("\n");
        for (Student st : arr) {
            if (st == null) {
                break;
            }
            sb.append(st);
            sb.append("\n");
        }
        return sb.toString();
    }

    public void sortSurnameAndPrint() {
        Student[] sortList = arrWithoutNull(groupList);
        Arrays.sort(sortList);
        System.out.println("afterSurnameSort");
        System.out.println(stListToString(sortList));
    }

    public void sortAndPrint() {
        Student[] sortList = new Student[groupList.length];
        System.arraycopy(groupList, 0, sortList, 0, groupList.length);

        System.out.println("Please enter parameter of sort(surname/name/age/averageRating):");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String s = reader.readLine();
            boolean invalidEnter = true;
            if (s.equals("surname")) {
                Arrays.sort(sortList, Comparator.comparing(Human::getSurname));
                System.out.println("after Surname Sort");
                System.out.println(stListToString(sortList));
                invalidEnter = false;
            }
            if (s.equals("name")) {
                Arrays.sort(sortList, Comparator.comparing(Human::getName));
                System.out.println("after Name Sort");
                System.out.println(stListToString(sortList));
                invalidEnter = false;
            }
            if (s.equals("age")) {
                Arrays.sort(sortList, Comparator.comparingInt(Human::getAge));
                System.out.println("after Age Sort");
                System.out.println(stListToString(sortList));
                invalidEnter = false;
            }
            if (s.equals("averageRating")) {
                Arrays.sort(sortList, Comparator.comparingDouble(Student::getAverageRating));
                System.out.println("after AverageRating Sort");
                System.out.println(stListToString(sortList));
                invalidEnter = false;
            }
            if (invalidEnter) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Invalid enter");
        }
    }

    @Override
    public Student[] getStudentValidForArmy() {
        Student[] studentValidForArmy = new Student[groupList.length];
        int counterOfValidStudent = 0;
        for (int i = 0; i < groupList.length; i++) {
            if (groupList[i] != null && groupList[i].isMale() && groupList[i].getAge() >= 18) {
                studentValidForArmy[counterOfValidStudent] = groupList[i];
                counterOfValidStudent++;
            }
        }
        return arrWithoutNull(studentValidForArmy);
    }

    public int getCount() {
        return count;
    }

    public void saveToFile(File file) throws FileNotFoundException {
        try (PrintWriter pw = new PrintWriter(file)) {
            pw.println(groupName);
            pw.println(getCount());
            for (int i = 0; i < groupList.length; i++) {
                if (groupList[i] != null) {
                    groupList[i].save(pw);
                }
            }
        }
        System.out.println("Done!");
    }

    public static Group read(FileReader reader) throws IOException {
        Group gr = new Group();
        try (BufferedReader bf = new BufferedReader(reader)) {
            String name_ = bf.readLine();
            gr.setGroupName(name_);
            int n = Integer.parseInt(bf.readLine());
            for (int i = 0; i < n; i++) {
                gr.addStudent(Student.createFromBufferedReader(bf));
            }
        }
        return gr;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupName='" + groupName + '\'' +
                ", groupList=" + stListToString(groupList) +
                '}';
    }

    public void serializeGroup(File f) throws IOException {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(f));) {
            os.writeObject(this);
        }
    }

    public Group deserializeGroup(File f) throws IOException, ClassNotFoundException {
        try (ObjectInputStream os = new ObjectInputStream(new FileInputStream(f));) {
            Group g = (Group) os.readObject();
            return g;
        }
    }
}