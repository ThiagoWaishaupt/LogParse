package com.api.logParse.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.api.logParse.LogParseApplication;

public class FerramentaTest {

    @Test
    public void testLerArquivoNaoNulo() {
        assertNotNull(Ferramenta.lerArquivo("games.log"));
    }

    @Test
    public void testIniciaGameComZeroKills() {
        final Game game = new Game();
        assertEquals(game.getkillsMundo(), 0);
    }

    @Test
    public void testIniciaPlayerComZeroKills() {
        final Player player = new Player();
        assertEquals(player.getKill(), 0);
    }

    @Test
    public void testBuscaGamePorId() {
        assertNotNull(LogParseApplication.buscaGamePorId(1));
    }

    @Test
    public void testOrdenaMapPorKill() {

    }

}
