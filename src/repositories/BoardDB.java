package repositories;

import models.Board;

import java.util.HashMap;

public class BoardDB {

    private HashMap<Integer, Board> boardMap;

    public BoardDB(){
        this.boardMap = new HashMap<>();
    }

    public int generateId(){
        return this.boardMap.size() + 1;
    }

}
