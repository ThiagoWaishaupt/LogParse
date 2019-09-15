package com.api.logParse;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.api.logParse.model.Ferramenta;
import com.api.logParse.model.Game;

@SpringBootApplication
public class LogParseApplication {

    private static void detalhesGameERanking() { // Task 1 e 2
        final List<Game> listGames = new ArrayList<Game>();

        final List<String> listaGamesText = Ferramenta.lerArquivo("games.log");

        int idGame = 1;

        for (final String elementoGame : listaGamesText) {
            final Game game = Ferramenta.criaGame(elementoGame, idGame);

            listGames.add(game);

            Ferramenta.mostrarDetalhesGame(game);

            idGame++;
        }
        Ferramenta.getRankingGeral(listGames);
    }

    public static Game buscaGamePorId(final long id) { // Task 3

        Game game = null;

        final List<String> listaGamesText = Ferramenta.lerArquivo("games.log");

        int idGame = 1;

        for (final String elementoGame : listaGamesText) {
            game = Ferramenta.criaGame(elementoGame, idGame);

            if (idGame == id) {
                return game;
            }
            idGame++;
        }
        return game;
    }

    public static void main(final String[] args) {
        SpringApplication.run(LogParseApplication.class, args);

        detalhesGameERanking();
    }
}
