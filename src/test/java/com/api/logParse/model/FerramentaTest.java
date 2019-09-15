package com.api.logParse.model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;

public class FerramentaTest {

    @Test
    public void testLerArquivo() {
        assertNotNull(Ferramenta.lerArquivo("games.log"));
    }

    @Test
    public void testCriaGame() {
        fail("Not yet implemented");
    }

    @Test
    public void testMostrarDetalhesGame() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetRankingGeral() {
        fail("Not yet implemented");
    }

    @Test
    public void testOrdenaMapPorKill() {
        fail("Not yet implemented");
    }

}
