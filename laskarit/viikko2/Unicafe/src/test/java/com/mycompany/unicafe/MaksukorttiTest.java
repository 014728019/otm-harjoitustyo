package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void arvoAlussaOikein() {
        assertEquals(10, kortti.saldo());      
    }
    
    @Test
    public void saldonLisaysToimiiOikein() {
        kortti.lataaRahaa(5);
        assertEquals(15, kortti.saldo());
    }
    
    @Test
    public void saldoVaheneeOttaessaJosSaldoa() {
        kortti.otaRahaa(6);
        assertEquals(4, kortti.saldo());
    }
    
    @Test
    public void saldoEiVaheneJosEiTarpeeksiSaldoa() {
        kortti.otaRahaa(12);
        assertEquals(10, kortti.saldo());
    }
    
    @Test
    public void palauttaaTrueJosRiittavastiSaldoa() {
        assertTrue(kortti.otaRahaa(4));
    }
    
    @Test
    public void palauttaaFalseJosEiRiittavastiSaldoa() {
        assertFalse(kortti.otaRahaa(20));
    }
    
    @Test
    public void tulostusOikeanAsuinen() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
}
