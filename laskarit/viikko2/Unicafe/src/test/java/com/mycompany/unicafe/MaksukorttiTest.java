package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void konstruktoriAsettaaSaldonOikein() {
        //Maksukortti kortti = new Maksukortti(10);
        //String vastaus = kortti.toString();

        assertEquals("saldo: 10.0", kortti.toString());
    }
    
    @Test
    public void kortilleVoiLadataRahaa() {
        kortti.lataaRahaa(500);
        assertEquals("saldo: 15.0", kortti.toString());
    }
    
    @Test
    public void kortiltaVoiOttaaRahaa1() {
        kortti.otaRahaa(1000);
        assertEquals("saldo: 0.0", kortti.toString());
        //assertEquals(true, kortti.toString());
    }
    
    @Test
    public void kortiltaVoiOttaaRahaa2() {
        kortti.otaRahaa(1100);
        kortti.saldo();
        assertEquals("saldo: 10.0", kortti.toString());
        //assertEquals(false, kortti.toString());
    }
    
    
}
