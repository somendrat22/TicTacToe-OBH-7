package services;

import Utilities.Logger;
import builders.AppBuilder;
import enums.GameStatus;
import exceptions.InvalidCredentials;
import exceptions.UserNotFoundException;
import models.Game;
import models.User;
import repositories.GameDB;
import repositories.UserDB;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {


    public User isUserValid(String email, String password){
        UserDB userDB = AppBuilder.getUserDB();
        User user = userDB.getUserByEmail(email);
        if(user == null){
            throw new InvalidCredentials(String.format("User with email id %s does not exist", email));
        }
        if(!user.getPassword().equals(password)){
            throw new InvalidCredentials("Wrong password entered");
        }
       return user;
    }

    public User isEmailExist(String email){
       User user = AppBuilder.getUserDB().getUserByEmail(email);
       if(user == null){
          throw new UserNotFoundException("Email does not exist");
       }
       return user;
    }


    public void getAllGameStatsOfUser(String email){
        User user = isEmailExist(email);
        if(user == null){
            return;
        }
        // We want to get all the games played by user
        GameService gameService = AppBuilder.getGameService();
        List<Game> games = gameService.getAllGamesPlayedByUser(user);
        this.printGameStats(user, games);

    }

    public User createUser(String email, String password, String name){
        UserDB userDB = AppBuilder.getUserDB();
        int id = userDB.generateNewId();
        User user = new User(id, name, email, password);
        userDB.saveUser(user);
        return user;
    }

    public void printGameStats(User user, List<Game> userPlayedGames) {

        int totalGames = userPlayedGames.size();

        int wins = 0;
        int losses = 0;
        int draws = 0;

        long totalDuration = 0;
        long longestGame = 0;
        long shortestGame = Long.MAX_VALUE;
        int completedGames = 0;

        Map<String, Integer> gamesByStatus = new HashMap<>();

        for (Game game : userPlayedGames) {

            // Win/Loss/Draw calculation
            if (game.getWinner() != null) {
                if (game.getWinner().getEmail().equals(user.getEmail())) {
                    wins++;
                } else {
                    losses++;
                }
            } else if (game.getGameStatus().equals(GameStatus.TIED.toString())) {
                draws++;
            }

            // Duration calculation
            if (game.getStartTime() != null && game.getEndTime() != null) {

                long duration = Duration.between(
                                game.getStartTime(),
                                game.getEndTime())
                        .toMinutes();

                totalDuration += duration;
                completedGames++;

                if (duration > longestGame) {
                    longestGame = duration;
                }

                if (duration < shortestGame) {
                    shortestGame = duration;
                }
            }

            // Status count
            String status = game.getGameStatus();

            if (gamesByStatus.containsKey(status)) {
                gamesByStatus.put(status, gamesByStatus.get(status) + 1);
            } else {
                gamesByStatus.put(status, 1);
            }
        }

        double winPercentage = 0;
        if (totalGames > 0) {
            winPercentage = (wins * 100.0) / totalGames;
        }

        long averageDuration = 0;
        if (completedGames > 0) {
            averageDuration = totalDuration / completedGames;
        }

        if (shortestGame == Long.MAX_VALUE) {
            shortestGame = 0;
        }

        // Print Stats
        System.out.println("\n========== PLAYER STATS ==========");
        System.out.println("Player            : " + user.getName());
        System.out.println("Total Games       : " + totalGames);
        System.out.println("Wins              : " + wins);
        System.out.println("Losses            : " + losses);
        System.out.println("Draws             : " + draws);
        System.out.printf("Win Percentage    : %.2f%%\n", winPercentage);

        System.out.println("\n------ Duration Stats ------");
        System.out.println("Average Duration  : " + averageDuration + " mins");
        System.out.println("Longest Game      : " + longestGame + " mins");
        System.out.println("Shortest Game     : " + shortestGame + " mins");

        System.out.println("\n------ Games By Status ------");
        for (Map.Entry<String, Integer> entry : gamesByStatus.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        System.out.println("=================================");
    }



}
