package com.api.logParse;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.api.logParse.model.Ferramenta;

@SpringBootApplication
public class LogParseApplication {

    public static void main(final String[] args) {
        SpringApplication.run(LogParseApplication.class, args);

        final List<String> listaGamesText = Ferramenta.lerArquivo("games.log"); // Task 1

    }

}
