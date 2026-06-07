package Utilities;

public class Logger {

    public static void log(String className, String message){
        System.out.println(
                String.format("[%s]: %s", className, message)
        );
    }

}
