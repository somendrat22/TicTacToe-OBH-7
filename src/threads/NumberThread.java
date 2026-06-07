package threads;

import services.UserService;

public class NumberThread extends UserService implements Runnable {
    public void run(){
        for(int i  = 0; i < 10; i++){
            if(i == 5){
                try {
                    Thread.sleep(10000);
                }catch (Exception e){

                }

            }
            System.out.println(i);
        }
    }
}
