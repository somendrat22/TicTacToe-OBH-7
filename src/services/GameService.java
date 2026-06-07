package services;

import Utilities.Logger;
import builders.AppBuilder;
import enums.GameStatus;
import models.Board;
import models.Game;
import models.User;
import repositories.BoardDB;
import repositories.GameDB;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GameService {


    public User startFreshGame(User player1, User player2){
        GameDB gameDB = AppBuilder.getGameDb();
        int id = gameDB.generateNewId();
        BoardDB boardDB = AppBuilder.getBoardDB();
        int boardId = boardDB.generateId();
        Board board = new Board(boardId);
        Game game = new Game(id, LocalDateTime.now(), null, player1, player2, GameStatus.IN_PROGRESS.toString(), board, null);
        gameDB.save(id, game);


        int count = 0;

        User currentPlayer = player1;
        User winner = null;
        char currentUserSymbol = 'x';
        while(count < 9){
            System.out.println("[GameService]: Game Started !!");
            Logger.log(GameService.class.getName(), currentPlayer.getName() + "This is your turn please enter the row and col value");
            Logger.log(GameService.class.getName(), "Enter row value");
            int row = AppBuilder.getScanner().nextInt();
            Logger.log(GameService.class.getName(), "Enter col value");
            int col = AppBuilder.getScanner().nextInt();
            if(row < 0 || row >= 3 || col < 0 || col >= 3 || board.getBoard()[row][col] != null){
                Logger.log(GameService.class.getName(), "Invalid input entered retry again");
                continue;
            }
            board.displayBoard();
            board.setValue(row, col, currentUserSymbol);
            board.displayBoard();
            if(board.getRowArr()[row] == 3 ||
                    board.getRowArr()[row] == -3 ||
                    board.getColArr()[col] == 3 ||
                    board.getColArr()[col] == -3 ||
                    board.getAntiDig() == 3||
                    board.getAntiDig() == -3 ||
                    board.getDig() == 3||
                    board.getDig() == -3
            ){
                winner = currentPlayer;
                break;
            }
            count++;
            if(currentUserSymbol == 'x'){
                currentUserSymbol = 'o';
                currentPlayer = player2;
            }else{
                currentUserSymbol = 'x';
                currentPlayer = player1;
            }
        }
        if(winner != null){
            game.setGameStatus(GameStatus.FINISHED.toString());
            game.setWinner(winner);
        }else{
            game.setGameStatus(GameStatus.TIED.toString());
        }
        game.setEndTime(LocalDateTime.now());
        return winner;
    }


    public List<Game> getAllGamesPlayedByUser(User user){
        GameDB gameDB = AppBuilder.getGameDb();
        List<Game> games = gameDB.getAllGames();
        List<Game> userPlayedGames = new ArrayList<>();
        for(Game game : games){
            if(game.getPlayer1() == user || game.getPlayer2() == user){
                userPlayedGames.add(game);
            }
        }
        return userPlayedGames;
    }



}
