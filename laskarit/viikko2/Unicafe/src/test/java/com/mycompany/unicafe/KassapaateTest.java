package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class KassapaateTest {

    Kassapaate kassapaate;
    Maksukortti maksukortti;

    @Before
    public void setUp() {
        this.maksukortti = new Maksukortti(500);
        this.kassapaate = new Kassapaate();
    }

    @Test
    public void arvotAlussaOikein() {
        assertEquals(100000, kassapaate.kassassaRahaa());
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void syoEdullisestiPalauttaaOikeanVaihtorahan() {
        assertEquals(260, kassapaate.syoEdullisesti(500));
    }
    
    @Test
    public void syoMaukkaastiPalauttaaOikeanVaihtorahan() {
        assertEquals(200, kassapaate.syoMaukkaasti(600));
    }
    
    @Test
    public void syoEdullisestiKasvattaaKassaaOikein() {
        kassapaate.syoEdullisesti(500);
        assertEquals(100240, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void syoMaukkaastiKasvattaaKassaaOikein() {
        kassapaate.syoMaukkaasti(500);
        assertEquals(100400, kassapaate.kassassaRahaa());
    }

    @Test
    public void syoEdullisestiKasvattaaMyytyjenMaaraa() {
        kassapaate.syoEdullisesti(300);
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void syoMaukkaastiKasvattaaMyytyjenMaaraa() {
        kassapaate.syoMaukkaasti(500);
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void syoEdullisestiToimiiKunRahaEiRiita() {
        assertEquals(200, kassapaate.syoEdullisesti(200));
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void syoMaukkaastiToimiiKunRahaEiRiita() {
        assertEquals(200, kassapaate.syoMaukkaasti(200));
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void lataaRahaaKortilleToimii() {
        kassapaate.lataaRahaaKortille(maksukortti, 100);
        assertEquals(100100, kassapaate.kassassaRahaa());
        assertEquals(600, maksukortti.saldo());
    }
    
    @Test
    public void syoEdullisestiKortillaToimiiRahanRiittaessa() {
        assertTrue(kassapaate.syoEdullisesti(maksukortti));
        assertEquals(260, maksukortti.saldo());
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void syoMaukkaastiKortillaToimiiRahanRiittaessa() {
        assertTrue(kassapaate.syoMaukkaasti(maksukortti));
        assertEquals(100, maksukortti.saldo());
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void syoEdullisestiKortillaToimiiKunRahaEiRiita() {
        maksukortti = new Maksukortti(100);
        assertFalse(kassapaate.syoEdullisesti(maksukortti));
        assertEquals(100, maksukortti.saldo());
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void syoMaukkaastiKortillaToimiiKunRahaEiRiita() {
        maksukortti = new Maksukortti(100);
        assertFalse(kassapaate.syoMaukkaasti(maksukortti));
        assertEquals(100, maksukortti.saldo());
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void syoMaukkaastiKortillaPalauttaaOikeanBooleanin() {
        assertTrue(kassapaate.syoMaukkaasti(maksukortti));
        assertFalse(kassapaate.syoMaukkaasti(maksukortti));
    }
    
    @Test
    public void kortilleVoiLadataRahaa() {
        kassapaate.lataaRahaaKortille(maksukortti, 10);
        assertEquals(510, maksukortti.saldo());
    }
    
    @Test
    public void kortilleEiVoiLadataNegatiivistaRahaa() {
        kassapaate.lataaRahaaKortille(maksukortti, -10);
        assertEquals(500, maksukortti.saldo());
    }

}
