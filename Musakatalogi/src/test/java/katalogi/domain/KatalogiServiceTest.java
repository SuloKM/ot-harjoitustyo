package katalogi.domain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import katalogi.domain.KatalogiService;
import katalogi.domain.Levy;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class KatalogiServiceTest {
    
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    File file;
    Levy levy;
    //KatalogiDao service;
    KatalogiService service;
    
    @Before
    public void setUp() throws Exception {
        
            System.out.println(" setUp.. ");
        
        file = testFolder.newFile("testi_levyt.txt");
        
            //System.out.println(" file.getAbsolutePath "+file.getAbsolutePath());

        try (FileWriter fw = new FileWriter(file.getAbsolutePath())) {
            fw.write("");
            //fw.write("Esittaja1;Nimi1;2021;Tyylilaji1;Omistaja1");
            //fw.append("");
            //fw.close();
        }

        // file.write("1;siivoa;false;testertester\n");
        // service = new KatalogiDao("testi_levyt.txt");
        //service = new KatalogiDao(file.getAbsolutePath());
        service = new KatalogiService();
        
            System.out.println(" ..setUp ");
    }
    
    @Test
    public void showStatistics() throws IOException {
        
            System.out.println(" showStatistics ");
        
        HashMap map = service.tilastoi();
            //System.out.println(" map.size "+map.size());
        assertEquals(5, map.size());
    }

    @Test
    public void savingRecordSuccessfull() throws IOException {
        
            System.out.println(" savingRecordSuccessfull ");
        
        levy = new Levy("Esittaja1","Nimi1","2021","Tyylilaji","Omistaja1");
        int koodi = service.lisaa(levy);
        assertEquals(0, koodi);
        
            //System.out.println(" koodi "+koodi);
    }
    
    @Test
    public void savingSameRecordUnsuccessfull() throws IOException {
        
            System.out.println(" savingSameRecordUnsuccessfull ");
        
        levy = new Levy("Esittaja1","Nimi1","2021","Tyylilaji","Omistaja1");
        int koodi = service.lisaa(levy);
        assertEquals(0, koodi);
        levy = new Levy("Esittaja1","Nimi1","2021","Tyylilaji","Omistaja1");
        koodi = service.lisaa(levy);
        assertEquals(2, koodi);
        
            //System.out.println(" koodi "+koodi);
    }
    
    @Test
    public void savingInvalidInputUnsuccessfull() throws IOException {
        
            System.out.println(" savingInvalidInputUnsuccessfull ");
        
        levy = new Levy("","Nimi1","2021","Tyylilaji","Omistaja1");
        int koodi = service.lisaa(levy);
        assertEquals(3, koodi);
        
            //System.out.println(" koodi "+koodi);
    }
    
    @Test
    public void fetchAll() throws IOException {
        
            System.out.println(" fetchAll ");
        
        levy = new Levy("Esittaja1","Nimi1","2021","Tyylilaji1","Omistaja1");
        int koodi = service.lisaa(levy);
        assertEquals(0, koodi);
            //System.out.println(" koodi1 "+koodi);
            
        levy = new Levy("Esittaja2","Nimi2","2021","Tyylilaji2","Omistaja1");
        koodi = service.lisaa(levy);
        assertEquals(0, koodi);
            //System.out.println(" koodi2 "+koodi);
            
        levy = new Levy("Esittaja1","Nimi3","2021","Tyylilaji1","Omistaja1");
        koodi = service.lisaa(levy);
        assertEquals(0, koodi);
            //System.out.println(" koodi3 "+koodi);
            
        levy = new Levy("Esittaja2","Nimi4","2021","Tyylilaji2","Omistaja1");
        koodi = service.lisaa(levy);
        assertEquals(0, koodi);
            //System.out.println(" koodi4 "+koodi);

        ArrayList haetut = new ArrayList();
        haetut = service.hae("","");
        
        assertEquals(4, haetut.size());
    }
    
    @Test
    public void fetchEsittaja() throws IOException {
        
            System.out.println(" fetchEsittaja ");
        
        levy = new Levy("Esittaja1","Nimi1","2021","Tyylilaji1","Omistaja1");
        int koodi = service.lisaa(levy);
        levy = new Levy("Esittaja2","Nimi2","2021","Tyylilaji2","Omistaja1");
        koodi = service.lisaa(levy);
        levy = new Levy("Esittaja1","Nimi3","2021","Tyylilaji1","Omistaja1");
        koodi = service.lisaa(levy);
        levy = new Levy("Esittaja2","Nimi4","2021","Tyylilaji2","Omistaja1");
        koodi = service.lisaa(levy);

        ArrayList haetut = new ArrayList();
        haetut = service.hae("Esittaja","Esittaja1");
        
        assertEquals(2, haetut.size());
    }
    
    @Test
    public void fetchNimi() throws IOException {
        
            System.out.println(" fetchNimi ");
        
        levy = new Levy("Esittaja1","Nimi1","2021","Tyylilaji1","Omistaja1");
        int koodi = service.lisaa(levy);
        levy = new Levy("Esittaja2","Nimi2","2021","Tyylilaji2","Omistaja1");
        koodi = service.lisaa(levy);
        levy = new Levy("Esittaja1","Nimi3","2021","Tyylilaji1","Omistaja1");
        koodi = service.lisaa(levy);
        levy = new Levy("Esittaja2","Nimi4","2021","Tyylilaji2","Omistaja1");
        koodi = service.lisaa(levy);

        ArrayList haetut = new ArrayList();
        haetut = service.hae("Nimi","Nimi1");
        
        assertEquals(1, haetut.size());
    }
    
    @Test
    public void fetchVuosi() throws IOException {
        
            System.out.println(" fetchVuosi ");
        
        levy = new Levy("Esittaja1","Nimi1","2021","Tyylilaji1","Omistaja1");
        int koodi = service.lisaa(levy);
        levy = new Levy("Esittaja2","Nimi2","2021","Tyylilaji2","Omistaja1");
        koodi = service.lisaa(levy);
        levy = new Levy("Esittaja1","Nimi3","2021","Tyylilaji1","Omistaja1");
        koodi = service.lisaa(levy);
        levy = new Levy("Esittaja2","Nimi4","2021","Tyylilaji2","Omistaja1");
        koodi = service.lisaa(levy);

        ArrayList haetut = new ArrayList();
        haetut = service.hae("Vuosi","2021");
        
        assertEquals(4, haetut.size());
    }
    
    @Test
    public void fetchTyylilaji() throws IOException {
        
            System.out.println(" fetchTyylilaji ");
        
        levy = new Levy("Esittaja1","Nimi1","2021","Tyylilaji1","Omistaja1");
        int koodi = service.lisaa(levy);
        levy = new Levy("Esittaja2","Nimi2","2021","Tyylilaji2","Omistaja1");
        koodi = service.lisaa(levy);
        levy = new Levy("Esittaja1","Nimi3","2021","Tyylilaji1","Omistaja1");
        koodi = service.lisaa(levy);
        levy = new Levy("Esittaja2","Nimi4","2021","Tyylilaji2","Omistaja1");
        koodi = service.lisaa(levy);

        ArrayList haetut = new ArrayList();
        haetut = service.hae("Tyylilaji","Tyylilaji2");
        
        assertEquals(2, haetut.size());
    }
    
    @Test
    public void fetchOmistaja() throws IOException {
        
            System.out.println(" fetchOmistaja ");

        levy = new Levy("Esittaja1","Nimi1","2021","Tyylilaji1","Omistaja1");
        int koodi = service.lisaa(levy);
        levy = new Levy("Esittaja2","Nimi2","2021","Tyylilaji2","Omistaja1");
        koodi = service.lisaa(levy);
        levy = new Levy("Esittaja1","Nimi3","2021","Tyylilaji1","Omistaja1");
        koodi = service.lisaa(levy);
        levy = new Levy("Esittaja2","Nimi4","2021","Tyylilaji2","Omistaja2");
        koodi = service.lisaa(levy);

        ArrayList haetut = new ArrayList();
        haetut = service.hae("Omistaja","Omistaja1");
        
        assertEquals(3, haetut.size());
    }
    
    @After
    public void tearDown() {
        
            //System.out.println(" tearDown ");
        
        file.delete();
    }
    
}
