package com.api.logParse.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {

    private int id;
    private int killsMundo;
    private List<Player> players;

    public Game() {
        killsMundo = 0;
    }

    public void addMortePeloMundo() {
        killsMundo++;
    }

    public int getTotalDeKillsNoGame() {

        players.forEach(player -> {
            killsMundo += player.getKill();
        });

        return killsMundo;
    }

    public List<String> getNickJogadores() {

        final List<String> nicks = new ArrayList<String>();

        players.forEach(player -> {
            nicks.add(player.getNick());
        });

        return nicks;
    }

    public Map<String, Integer> getDetalheKills() {

        final Map<String, Integer> map = new HashMap<String, Integer>();

        players.forEach(player -> {
            map.put(player.getNick(), player.getKill());
        });

        return map;
    }

    //Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getkillsMundo() {
        return killsMundo;
    }

    public void setkillsMundo(final int totalKills) {
        killsMundo = totalKills;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(final List<Player> players) {
        this.players = players;
    }

}
