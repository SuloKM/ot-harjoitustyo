/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aleksikoivisto
 */
public class KassapaateTest {
    
    Kassapaate kassapaate;
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        kassapaate = new Kassapaate();
        kortti = new Maksukortti(1000);
    }
    
    public KassapaateTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @After
    public void tearDown() {
    }
    
    @Test
    public void konstruktoriAsettaaSaldonOikein() {

        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void konstruktoriAsettaaMyydytEdullisetLounaatOikein() {

        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void konstruktoriAsettaaMyydytMaukkaatLounaatOikein() {

        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kassaOikeinKunSyotuEdullisesti() {

        kassapaate.syoEdullisesti(240);
        
        assertEquals(100240, kassapaate.kassassaRahaa());
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kassaOikeinKunSyotuEdullisesti2() {

        int vaihtoraha = kassapaate.syoEdullisesti(400);
        
        assertEquals(100240, kassapaate.kassassaRahaa());
        assertEquals(160, vaihtoraha);
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kassaOikeinKunSyotuEdullisesti3() {

        int vaihtoraha = kassapaate.syoEdullisesti(200);
        
        assertEquals(100000, kassapaate.kassassaRahaa());
        assertEquals(200, vaihtoraha);
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kassaOikeinKunSyotuMaukkaasti() {

        kassapaate.syoMaukkaasti(400);
        
        assertEquals(100400, kassapaate.kassassaRahaa());
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kassaOikeinKunSyotuMaukkaasti2() {

        int vaihtoraha = kassapaate.syoMaukkaasti(500);
        
        assertEquals(100400, kassapaate.kassassaRahaa());
        assertEquals(100, vaihtoraha);
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kassaOikeinKunSyotuMaukkaasti3() {

        int vaihtoraha = kassapaate.syoMaukkaasti(300);
        
        assertEquals(100000, kassapaate.kassassaRahaa());
        assertEquals(300, vaihtoraha);
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void korttiOstoToimiiEdullisesti() {

        assertEquals(true, kassapaate.syoEdullisesti(kortti));
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void korttiOstoEiToimi() {

        kortti = new Maksukortti(100);
        assertEquals(false, kassapaate.syoEdullisesti(kortti));
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
        assertEquals(100000, kassapaate.kassassaRahaa());
        kortti = new Maksukortti(100);
        assertEquals(false, kassapaate.syoMaukkaasti(kortti));
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void korttiOstoToimiiMaukkaasti() {

        assertEquals(true, kassapaate.syoMaukkaasti(kortti));
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void korttiLatausToimii() {

        kortti = new Maksukortti(0);
        kassapaate.lataaRahaaKortille(kortti, 240);
        assertEquals(true, kassapaate.syoEdullisesti(kortti));
    }
    
    @Test
    public void korttiLatausEiToimi() {

        kortti = new Maksukortti(0);
        kassapaate.lataaRahaaKortille(kortti, -1);
        assertEquals(false, kassapaate.syoEdullisesti(kortti));
    }
}