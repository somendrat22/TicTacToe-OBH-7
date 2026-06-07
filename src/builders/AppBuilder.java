package builders;

import controllers.GameController;
import models.Game;
import repositories.BoardDB;
import repositories.GameDB;
import repositories.UserDB;
import services.GameService;
import services.UserService;

import java.util.Scanner;

public class AppBuilder {

    private static UserDB userDB;
    private static Scanner scanner;
    private static GameDB gameDB;
    private static BoardDB boardDB;
    private static GameController gameController;
    private static UserService userService;
    private static GameService gameService;

    public AppBuilder(){
        System.out.println("[AppBuilder]: Essential classes object build started");
        AppBuilder.gameController = new GameController();
        AppBuilder.boardDB = new BoardDB();
        AppBuilder.userDB = new UserDB();
        AppBuilder.userService = new UserService();
        AppBuilder.gameDB = new GameDB();
        AppBuilder.gameService = new GameService();
        System.out.println("[AppBuilder]: Essential classes object build ended");
    }

    public static UserService getUserService(){
        if(AppBuilder.userService == null){
            AppBuilder.userService = new UserService();
        }
        return AppBuilder.userService;
    }

    public static GameController getGameController(){
        if(gameController == null){
            AppBuilder.gameController = new GameController();
        }
        return gameController;
    }

    public static UserDB getUserDB(){
        if(userDB == null){
           AppBuilder.userDB = new UserDB();
        }
        return userDB;
    }

    public static GameDB getGameDb(){
        if(gameDB == null){
            AppBuilder.gameDB = new GameDB();
        }
        return gameDB;
    }

    public static BoardDB getBoardDB(){
        if(boardDB == null){
           AppBuilder.boardDB = new BoardDB();
        }
        return boardDB;
    }

    public static Scanner getScanner(){
        if(scanner == null){
            AppBuilder.scanner = new Scanner(System.in);
        }
        return scanner;
    }

    public static GameService getGameService(){
        if(gameService != null){
            AppBuilder.gameService = new GameService();
        }
        return gameService;
    }
}
