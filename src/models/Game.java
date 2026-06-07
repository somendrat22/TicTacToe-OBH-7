package models;

import java.time.LocalDateTime;

public class Game {
    private int gameId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private User player1;
    private User player2;
    private String gameStatus;
    private Board board;
    private User winner;

    public Game(int gameId, LocalDateTime startTime, LocalDateTime endTime, User player1, User player2, String gameStatus, Board board, User winner) {
        this.gameId = gameId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.player1 = player1;
        this.player2 = player2;
        this.gameStatus = gameStatus;
        this.board = board;
        this.winner = winner;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public User getPlayer1() {
        return player1;
    }

    public void setPlayer1(User player1) {
        this.player1 = player1;
    }

    public User getPlayer2() {
        return player2;
    }

    public void setPlayer2(User player2) {
        this.player2 = player2;
    }

    public String getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public User getWinner() {
        return winner;
    }

    public void setWinner(User winner) {
        this.winner = winner;
    }
}
