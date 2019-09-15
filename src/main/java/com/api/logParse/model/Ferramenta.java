package com.api.logParse.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

}
