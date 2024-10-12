package com.ensa.tp4.service;

import com.ensa.tp4.beans.Player;
import com.ensa.tp4.dao.IDao;

import java.util.ArrayList;
import java.util.List;

public class PlayerService implements IDao<Player> {
    private List<Player> players;
    private static PlayerService instance;
    private PlayerService(){
        this.players=new ArrayList<>();
    }
    public static PlayerService getInstance(){
        if(instance==null)
            instance=new PlayerService();
        return instance;
    }

    @Override
    public boolean create(Player o) {
        return players.add(o);
    }

    @Override
    public boolean update(Player o) {
        for(Player player:players){
            if(player.getId()==o.getId()){
                player.setImg(o.getImg());
                player.setNom(o.getNom());
                player.setStar(o.getStar());
            }
        }
        return true;
    }

    @Override
    public boolean delete(Player o) {
        return players.remove(o);
    }

    @Override
    public Player findById(int id) {
        for(Player player:players) {
            if (player.getId() == id) {
                return player;
            }
        }
        return null;
    }

    @Override
    public List<Player> findAll() {
        return players;
    }
}
