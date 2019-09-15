package com.api.logParse;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.api.logParse.model.Ferramenta;
import com.api.logParse.model.Game;

@SpringBootApplication
public class LogParseApplication {

    public static void main(final String[] args) {
        SpringApplication.run(LogParseApplication.class, args);

        final List<String> listaGamesText = Ferramenta.lerArquivo("games.log"); // Task 1

        int idGame = 1;

        for (final String elementoGame : listaGamesText) {
            final Game game = Ferramenta.criaGame(elementoGame, idGame); // Task 1

            idGame++;
        }

    }

}
