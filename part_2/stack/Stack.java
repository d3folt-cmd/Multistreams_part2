package dz;

import java.util.Arrays;

public class Stack {
    private BlackList blackList;
    private Object[] stackArray;
    private int count;

    public Stack(int sizeStack) {
        this.stackArray = new Object[sizeStack];
        this.count = 0;
    }

    public BlackList getBlackList() {
        return blackList;
    }

    public void setBlackList(BlackList blackList) {
        this.blackList = blackList;
    }

    public void push(Object object){
        if(blackList != null && blackList.contains(object)){
            throw new BlackList.ClassBlackListed();
        }
        if(count == stackArray.length){
            throw new ArrayIndexOutOfBoundsException("Stack is Full");
        }
        stackArray[count] = object;
        count++;
    }

    public Object pop(){
        if(count-1<0){
            return null;
        }
        Object o = stackArray[count-1];
        stackArray[count-1] = null;
        count--;
        return o;
    }

    public Object peek() {
        if(count-1<0){
           return null;
        }
        return stackArray[count-1];
    }

    @Override
    public String toString() {
        return "Stack{" +
                "stackArray=" + Arrays.toString(stackArray) +
                '}';
    }
}
