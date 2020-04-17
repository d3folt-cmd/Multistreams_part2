package dz;

public class Main {

    public static void main(String[] args) {
        Stack stack = new Stack(3);
        Auto a = new Auto();
        Plane p = new Plane();
        Ship s = new Ship();

        stack.push(a);
        stack.push(p);
        stack.push(s);
        System.out.println(stack);
        stack.pop();
        System.out.println(stack);
        System.out.println(stack.peek());

        BlackList blacklist = new BlackList();
        blacklist.addClass(s.getClass());
        stack.setBlackList(blacklist);
        stack.push(s);
        System.out.println(stack);
    }

    static class Auto {
        @Override
        public String toString() {
            return "Auto";
        }
    }

    static class Plane {
        @Override
        public String toString() {
            return "Plane";
        }
    }

    static class Ship {
        @Override
        public String toString() {
            return "Ship";
        }
    }
}
