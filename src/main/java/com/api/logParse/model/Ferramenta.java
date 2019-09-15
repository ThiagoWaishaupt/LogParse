package com.api.logParse.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ferramenta {

    public static List<String> lerArquivo(final String nomeArquivo) {

        String conteudo = null;
        final List<String> listGames = new ArrayList<String>();

        try {
            final FileReader arq = new FileReader(nomeArquivo);
            final BufferedReader lerArq = new BufferedReader(arq);

            String linha = lerArq.readLine(); // lê a primeira linha

            conteudo = linha;

            while (linha != null) {

                conteudo += linha + "\n";

                if (linha.contains("ShutdownGame")) {
                    listGames.add(conteudo);
                    conteudo = "";
                }

                linha = lerArq.readLine(); // lê da segunda até a última linha
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

            if (motivo.contains("<world>")) {
                final String nickJogador = motivo.substring(16, motivo.indexOf("by") - 1);

                final Player player = map.get(nickJogador);

                player.morreuProMundo();
                game.addMortePeloMundo();

            } else if (motivo.contains("killed")) {
                final String nickJogador = motivo.substring(1, motivo.indexOf("killed") - 1);

                final Player player = map.get(nickJogador);

                player.matou();
            }
        }
    }

}
