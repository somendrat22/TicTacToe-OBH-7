package repositories;

import models.Game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameDB {

    private HashMap<Integer, Game> gameMap;

    public GameDB(){
        this.gameMap = new HashMap<>();
    }

    public void save(int id, Game game){
        gameMap.put(id, game);
    }

    public int generateNewId(){
        return this.gameMap.size() + 1;
    }

    public List<Game> getAllGames(){
        List<Game> games = new ArrayList<>();
        for(Integer key : gameMap.keySet()){
            games.add(gameMap.get(key));
        }
        return games;
    }

}
