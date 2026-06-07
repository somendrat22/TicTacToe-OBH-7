package controllers;

import Utilities.Logger;
import builders.AppBuilder;
import exceptions.UserNotFoundException;
import models.User;
import services.GameService;
import services.UserService;

import java.util.Scanner;

public class GameController {

    private Scanner scn;

    public GameController(){
        this.scn = new Scanner(System.in);
    }

    public User getPlayerDetails(){
        UserService userService = AppBuilder.getUserService();
        System.out.println("[GameController]: Are you a registered user ? Press 1 (Yes)/ Press 2 (No)");
        int optionEntered = scn.nextInt();
        if(optionEntered == 1){
            System.out.println("[GameController]: Enter your email");
            String email = scn.next();
            System.out.println("[GameController]: Enter your password");
            String password = scn.next();
            User user = userService.isUserValid(email, password);
            if(user != null){
                return user;
            }else{
                return null;
            }
        }else if(optionEntered == 2){
            System.out.println("[GameController]: Enter your name");
            String name = scn.next();
            System.out.println("[GameController]: Enter your email");
            String email = scn.next();
            System.out.println("[GameController]: Enter your password");
            String password = scn.next();
            User user = userService.createUser(email, password, name);
            return user;
        }else{
            System.out.println("[GameController]: Invalid option entered");
            return null;
        }
    }

    public void startGame(){
        System.out.println("[GameController]: Welcome to the game !!");
        while(true){
            System.out.println("[GameController]: Start a new game - Press 1");
            System.out.println("[GameController]: Check your game stats - Press 2");
            System.out.println("[GameController]: Check your game stats - Press 3");
            System.out.println("[GameController]: Waiting for user input");
            int optionPicked = scn.nextInt();
            if(optionPicked == 1){
                System.out.println("[GameController]: Key pressed 1 need user details");
                System.out.println("[GameController]: Need Player 1 details");
                User player1 = getPlayerDetails();
                System.out.println("[GameController]: Need Player 2 details");
                User player2 = getPlayerDetails();
                GameService gameService = AppBuilder.getGameService();
                User winner = gameService.startFreshGame(player1, player2);
                Logger.log(GameController.class.getName(), winner.getName() + " won the game");
            }else if(optionPicked == 2){
                Logger.log(GameController.class.getName(), "Please enter your email");
                String email = AppBuilder.getScanner().next();
                // Logic -> Get All games played by user
                try{
                    AppBuilder.getUserService().getAllGameStatsOfUser(email);
                }catch (UserNotFoundException e){
                    Logger.log(GameController.class.getName(), e.getMessage());
                }
            }else if(optionPicked == 3){

            }else{
                System.out.println("[GameController]: Invalid option entered");
            }
        }
    }

}
