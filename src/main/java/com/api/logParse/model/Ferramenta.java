package com.api.logParse.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Ferramenta {

    public static List<String> lerArquivo(final String nomeArquivo) {

        String conteudo = null;
        final List<String> listGames = new ArrayList<String>();

        try {
            final FileReader arq = new FileReader(nomeArquivo);
            final BufferedReader lerArq = new BufferedReader(arq);

            // lê a primeira linha
            String linha = lerArq.readLine();

            conteudo = linha;

            while (linha != null) {

                conteudo += linha + "\n";

                if (linha.contains("ShutdownGame")) {
                    listGames.add(conteudo);
                    conteudo = "";
                }

                // lê da segunda até a última linha
                linha = lerArq.readLine();
            }

            arq.close();

        } catch (final IOException e) {
            System.err.println("Erro na abertura do arquivo: " + e.getMessage());
        }

        if (listGames.size() == 0) {
            return null;
        } else {
            return listGames;
        }
    }

    public static Game criaGame(final String gameTexto, final int gameId) {
        final Game game = new Game();

        Map<String, Player> map = new HashMap<String, Player>();

        final String[] linhas = gameTexto.split("\\n");

        // Percorre linha por linha buscando Players e Kills
        for (final String linha : linhas) {
            map = buscaPlayers(linha, map);
            buscaKills(linha, map, game);
        }

        final List<Player> listPlayers = new ArrayList<Player>();

        for (final Map.Entry<String, Player> pair : map.entrySet()) {
            listPlayers.add(pair.getValue());
        }

        game.setPlayers(listPlayers);
        game.setId(gameId);

        return game;
    }

    private static Map<String, Player> buscaPlayers(final String linha, final Map<String, Player> map) {
        final String nickJogador;

        //Busca novos Players
        if (linha.contains("ClientUserinfoChanged")) {

            nickJogador = linha.substring(34, linha.indexOf("\\t"));

            final Player player = new Player();
            player.setNick(nickJogador);

            if (!map.containsKey(nickJogador)) {
                map.put(nickJogador, player);
            }
        }
        return map;
    }

    private static void buscaKills(final String linha, final Map<String, Player> map, final Game game) {
        final String motivo;

        if (linha.contains("Kill")) {

            final String[] arraylinha = linha.split(":");

            motivo = arraylinha[3];

            // Verifica Player que morreram pelo mundo
            if (motivo.contains("<world>")) {
                final String nickJogador = motivo.substring(16, motivo.indexOf("by") - 1);

                final Player player = map.get(nickJogador);

                player.morreuProMundo();
                game.addMortePeloMundo();

                // Verifica Players que morreram por outro Player
            } else if (motivo.contains("killed")) {
                final String nickJogador = motivo.substring(1, motivo.indexOf("killed") - 1);

                final Player player = map.get(nickJogador);

                player.matou();
            }
        }
    }

    public static void mostrarDetalhesGame(final Game game) {

        System.out.println("Informacoes do Game:\n");
        System.out.println("id: " + game.getId());
        System.out.println("Total_kills: " + game.totalDeKillsNoGame());
        System.out.println("Players: " + game.nickJogadores());
        System.out.println("Kills: " + game.detalheKills());
        System.out.println("\n---------------------\n");

    }

    public static void getRankingGeral(final List<Game> listGames) {

        final Map<String, Integer> map = new HashMap<String, Integer>();

        // Percorre Players de cada Game
        listGames.forEach(game -> {

            final List<Player> listPlayers = game.getPlayers();

            listPlayers.forEach(player -> {

                if (!map.containsKey(player.getNick())) {
                    map.put(player.getNick(), player.getKill());
                } else {
                    final int killPlayer = map.get(player.getNick());
                    map.put(player.getNick(), player.getKill() + killPlayer);
                }
            });
        });

        // Ordena Kills em ordem decrescente
        final Map<String, Integer> mapOrdenado = ordenaMapPorKill(map);

        System.out.println("Ranking Geral:");
        for (final Map.Entry<String, Integer> pair : mapOrdenado.entrySet()) {
            System.out.println("Player: " + pair.getKey() + " - Kill(s): " + pair.getValue());
        }

    }

    public static HashMap<String, Integer> ordenaMapPorKill(final Map<String, Integer> map) {
        {
            // Crie uma lista a partir dos elementos do HashMap
            final List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(map.entrySet());

            // Ordenar a lista
            Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(final Map.Entry<String, Integer> o1, final Map.Entry<String, Integer> o2) {
                    return o1.getValue().compareTo(o2.getValue());
                }
            });
            Collections.reverse(list);

            // colocar dados da lista ordenados em hashmap
            final HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
            for (final Map.Entry<String, Integer> aa : list) {
                temp.put(aa.getKey(), aa.getValue());
            }
            return temp;
        }
    }

}
