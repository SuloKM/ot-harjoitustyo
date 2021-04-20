/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package katalogi.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import katalogi.domain.Levy;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author aleksikoivisto
 */
public class KatalogiDaoTest {
    
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    File file;
    Levy levy;
    KatalogiDao dao;
    
    @Before
    public void setUp() throws Exception {
        
            System.out.println(" setUp.. ");
        
        file = testFolder.newFile("testi_levyt.txt");
        
            System.out.println(" file.getAbsolutePath "+file.getAbsolutePath());

        try (FileWriter fw = new FileWriter(file.getAbsolutePath())) {
            fw.write("");
            //fw.write("Esittaja1;Nimi1;2021;Tyylilaji1;Omistaja1");
            //fw.append("");
            //fw.close();
        }

        // file.write("1;siivoa;false;testertester\n");
        // dao = new KatalogiDao("testi_levyt.txt");
        dao = new KatalogiDao(file.getAbsolutePath());
        
            System.out.println(" ..setUp ");
    }

    /*
    @Test
    public void savingRecordSuccessfull() throws IOException {
        
            System.out.println(" levynTallennus ");
        
        levy = new Levy("Esittaja1","Nimi1","2021","Tyylilaji","Omistaja1");
        int koodi = dao.lisaa(levy);
            System.out.println(" koodi "+koodi);
    }
    
    @Test
    public void savingSameRecordUnsuccessfull() throws IOException {
        
            System.out.println(" samanLevynTallennus ");
        
        levy = new Levy("Esittaja1","Nimi1","2021","Tyylilaji","Omistaja1");
        int koodi = dao.lisaa(levy);
        levy = new Levy("Esittaja1","Nimi1","2021","Tyylilaji","Omistaja1");
        koodi = dao.lisaa(levy);
        assertEquals(2, koodi);
            System.out.println(" koodi "+koodi);
    }
    
    @Test
    public void savingInvalidInputUnsuccessfull() throws IOException {
        
            System.out.println(" virheellinen syöte ");
        
        levy = new Levy("","Nimi1","2021","Tyylilaji","Omistaja1");
        int koodi = dao.lisaa(levy);
        assertEquals(3, koodi);
            System.out.println(" koodi "+koodi);
    }
    */
    
    @Test
    public void fetchAll() throws IOException {
        
            System.out.println(" haeKaikki ");
        
        levy = new Levy("Esittaja1","Nimi1","2021","Tyylilaji1","Omistaja1");
        int koodi = dao.lisaa(levy);
        levy = new Levy("Esittaja2","Nimi2","2021","Tyylilaji2","Omistaja1");
        koodi = dao.lisaa(levy);
        levy = new Levy("Esittaja1","Nimi3","2021","Tyylilaji1","Omistaja1");
        koodi = dao.lisaa(levy);
        levy = new Levy("Esittaja2","Nimi4","2021","Tyylilaji2","Omistaja1");
        koodi = dao.lisaa(levy);

        ArrayList levyt = new ArrayList();
        levyt = dao.hae("","");
        
        assertEquals(4, levyt.size());
    }
    
    @Test
    public void fetchEsittaja() throws IOException {
        
            System.out.println(" haeEsittaja ");
        
        levy = new Levy("Esittaja1","Nimi1","2021","Tyylilaji1","Omistaja1");
        int koodi = dao.lisaa(levy);
        levy = new Levy("Esittaja2","Nimi2","2021","Tyylilaji2","Omistaja1");
        koodi = dao.lisaa(levy);
        levy = new Levy("Esittaja1","Nimi3","2021","Tyylilaji1","Omistaja1");
        koodi = dao.lisaa(levy);
        levy = new Levy("Esittaja2","Nimi4","2021","Tyylilaji2","Omistaja1");
        koodi = dao.lisaa(levy);

        ArrayList levyt = new ArrayList();
        levyt = dao.hae("Esittaja","Esittaja1");
        
        assertEquals(2, levyt.size());
    }
    
    @Test
    public void fetchNimi() throws IOException {
        
            System.out.println(" haeNimi ");
        
        levy = new Levy("Esittaja1","Nimi1","2021","Tyylilaji1","Omistaja1");
        int koodi = dao.lisaa(levy);
        levy = new Levy("Esittaja2","Nimi2","2021","Tyylilaji2","Omistaja1");
        koodi = dao.lisaa(levy);
        levy = new Levy("Esittaja1","Nimi3","2021","Tyylilaji1","Omistaja1");
        koodi = dao.lisaa(levy);
        levy = new Levy("Esittaja2","Nimi4","2021","Tyylilaji2","Omistaja1");
        koodi = dao.lisaa(levy);

        ArrayList levyt = new ArrayList();
        levyt = dao.hae("Nimi","Nimi1");
        
        assertEquals(1, levyt.size());
    }
    
    @Test
    public void fetchVuosi() throws IOException {
        
            System.out.println(" haeVuosi ");
        
        levy = new Levy("Esittaja1","Nimi1","2021","Tyylilaji1","Omistaja1");
        int koodi = dao.lisaa(levy);
        levy = new Levy("Esittaja2","Nimi2","2021","Tyylilaji2","Omistaja1");
        koodi = dao.lisaa(levy);
        levy = new Levy("Esittaja1","Nimi3","2021","Tyylilaji1","Omistaja1");
        koodi = dao.lisaa(levy);
        levy = new Levy("Esittaja2","Nimi4","2021","Tyylilaji2","Omistaja1");
        koodi = dao.lisaa(levy);

        ArrayList levyt = new ArrayList();
        levyt = dao.hae("Vuosi","2021");
        
        assertEquals(4, levyt.size());
    }
    
    @Test
    public void fetchTyylilaji() throws IOException {
        
            System.out.println(" haeTyylilaji ");
        
        levy = new Levy("Esittaja1","Nimi1","2021","Tyylilaji1","Omistaja1");
        int koodi = dao.lisaa(levy);
        levy = new Levy("Esittaja2","Nimi2","2021","Tyylilaji2","Omistaja1");
        koodi = dao.lisaa(levy);
        levy = new Levy("Esittaja1","Nimi3","2021","Tyylilaji1","Omistaja1");
        koodi = dao.lisaa(levy);
        levy = new Levy("Esittaja2","Nimi4","2021","Tyylilaji2","Omistaja1");
        koodi = dao.lisaa(levy);

        ArrayList levyt = new ArrayList();
        levyt = dao.hae("Tyylilaji","Tyylilaji2");
        
        assertEquals(2, levyt.size());
    }
    
    @Test
    public void fetchOmistaja() throws IOException {
        
            System.out.println(" haeOmistaja ");
        
        levy = new Levy("Esittaja1","Nimi1","2021","Tyylilaji1","Omistaja1");
        int koodi = dao.lisaa(levy);
        levy = new Levy("Esittaja2","Nimi2","2021","Tyylilaji2","Omistaja1");
        koodi = dao.lisaa(levy);
        levy = new Levy("Esittaja1","Nimi3","2021","Tyylilaji1","Omistaja1");
        koodi = dao.lisaa(levy);
        levy = new Levy("Esittaja2","Nimi4","2021","Tyylilaji2","Omistaja2");
        koodi = dao.lisaa(levy);

        ArrayList levyt = new ArrayList();
        levyt = dao.hae("Omistaja","Omistaja1");
        
        assertEquals(3, levyt.size());
    }
    
    @After
    public void tearDown() {
        
            //System.out.println(" tearDown ");
        
        file.delete();
    }
    
}
