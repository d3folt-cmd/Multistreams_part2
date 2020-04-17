package dz;

import java.util.Arrays;

public class BlackList {
    private Class[] classArray = new Class[5];

    public void addClass(Class cl) {
        boolean full = false;
        for (int i = 0; i < classArray.length; i++) {
            if (classArray[i] == null) {
                classArray[i] = cl;
                break;
            } else if (classArray[i] != null && cl.getName().equals(classArray[i].getName())) {
                System.out.println("Class is already in blacklist");
                break;
            } else if (i == classArray.length - 1)
                full = true;
        }
        if (full) {
            classArray = Arrays.copyOf(classArray, classArray.length + 1);
            classArray[classArray.length - 1] = cl;
        }
    }

    public void removeClass(Class cl) {
        boolean isIn = false;
        for (int i = 0; i < classArray.length; i++) {
            if (classArray[i] != null && cl.getName().equals(classArray[i].getName())) {
                classArray[i] = null;
                isIn = true;
                break;
            }
        }
        if (!isIn)
            System.out.println("Class not found");
    }

    public boolean contains(Object object) {
        for (int i = 0; i < classArray.length; i++) {
            if (classArray[i] != null && object.getClass().getName().equals(classArray[i].getName())) {
                return true;
            }
        }
        return false;
    }

    static class ClassBlackListed extends RuntimeException{
    }

    @Override
    public String toString() {
        return "BlackList{" +
                "classArray=" + Arrays.toString(classArray) +
                '}';
    }
}
