package threads;

import services.UserService;

public class NameThread extends Thread {

    /**
     * We will extend thread class
     * Whenever we will try to perform some operation with the help of this class that operation will not get performer synchronusly
     * It will get performed in parallel
     */
    public void run(){
        for(int i = 0; i < 10; i++){
            System.out.println("Somendra");
        }
    }

}
