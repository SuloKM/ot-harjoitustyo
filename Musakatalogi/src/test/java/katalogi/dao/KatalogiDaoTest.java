/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package katalogi.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
        
            //System.out.println(" setUp.. ");
        
        file = testFolder.newFile("testi_levyt.txt");

        try (FileWriter fw = new FileWriter(file.getAbsolutePath())) {
            fw.append("");
            fw.close();
        }

        dao = new KatalogiDao("testi_levyt.txt");
        
            //System.out.println(" ..setUp ");
    }

    @Test
    public void savingRecordSuccessfull() throws IOException {
        
            //System.out.println(" levynTallennus ");
        
        levy = new Levy("Esittaja1","Nimi1","2021","Tyylilaji","Omistaja1");
        int koodi = dao.lisaa(levy);
            //System.out.println(" koodi "+koodi);
    }
    
    
    @Test
    public void savingSameRecordUnsuccessfull() throws IOException {
        
            //System.out.println(" samanLevynTallennus ");
        
        levy = new Levy("Esittaja1","Nimi1","2021","Tyylilaji","Omistaja1");
        int koodi = dao.lisaa(levy);
        levy = new Levy("Esittaja1","Nimi1","2021","Tyylilaji","Omistaja1");
        koodi = dao.lisaa(levy);
        assertEquals(2, koodi);
            //System.out.println(" koodi "+koodi);
    }
    
    /*
    @Test
    public void savingInvalidInputUnsuccessfull() throws IOException {
        
            System.out.println(" virheellinen syöte ");
        
        levy = new Levy("","Nimi1","2021","Tyylilaji","Omistaja1");
        int koodi = dao.lisaa(levy);
            System.out.println(" koodi "+koodi);
    }
    */
    
    @After
    public void tearDown() {
        
            //System.out.println(" tearDown ");
        
        file.delete();
    }
    
}
