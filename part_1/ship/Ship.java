package dz;

public class Ship implements Runnable{
    private String name;
    private int box = 10;

    public Ship(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "name='" + name + '\'' +
                ", box=" + box +
                '}';
    }

    @Override
    public void run() {
        Thread thr = Thread.currentThread();
        int n = 0;
        for(int i = 0; i < box; i++){
            System.out.println(" Unloaded " + (i + 1) + " boxes from " + name);
            n++;
            try {
                Thread.currentThread().sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(box == n){
            System.out.println(name + " unloaded");
        }
    }
}
