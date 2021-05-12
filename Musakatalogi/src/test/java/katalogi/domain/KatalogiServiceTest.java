package katalogi.domain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import katalogi.domain.KatalogiService;
import katalogi.domain.Album;
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
    Album album;
    KatalogiService service;
    List<Album> albums;
    
    @Before
    public void setUp() throws Exception {

        file = testFolder.newFile("album.txt");
        service = new KatalogiService(file.toString());
    }

    @Test
    public void removingAlbumSuccessfull() throws IOException {
        
            //System.out.println(" removingAlbumSuccessfull ");
        album = new Album("Esittaja1","Nimi1","2021","Tyylilaji","Omistaja1");
        int code = service.add(album);
        albums = service.get("", "");
        assertEquals(1, albums.size());
            //System.out.println(" albums size " + albums.size());
        album = new Album("Esittaja1","Nimi1","2021","Tyylilaji","Omistaja1");
        albums = service.remove(album);
        assertEquals(0, albums.size());
    }
    
    @Test
    public void savingRecordSuccessfull() throws IOException {
        
          //System.out.println(" savingRecordSuccessfull ");
        
        album = new Album("Esittaja1","Nimi1","2021","Tyylilaji","Omistaja1");
        int code = service.add(album);
        assertEquals(0, code);
        
            //System.out.println(" code "+code);
    }
    
    @Test
    public void savingSameRecordUnsuccessfull() throws IOException {
        
          //System.out.println(" savingSameRecordUnsuccessfull ");
        
        album = new Album("Esittaja1","Nimi1","2021","Tyylilaji","Omistaja1");
        int code = service.add(album);
        assertEquals(0, code);
        album = new Album("Esittaja1","Nimi1","2021","Tyylilaji","Omistaja1");
        code = service.add(album);
        assertEquals(2, code);
        
            //System.out.println(" code "+code);
    }
    
    @Test
    public void savingInvalidInputUnsuccessfull() throws IOException {
        
          //System.out.println(" savingInvalidInputUnsuccessfull ");
        
        album = new Album("","Nimi1","2021","Tyylilaji","Omistaja1");
        int code = service.add(album);
        assertEquals(3, code);
        
            //System.out.println(" code "+code);
    }
    
    @Test
    public void fetchAll() throws IOException {
        
          //System.out.println(" fetchAll ");
        
        album = new Album("Esittaja1","Nimi1","2021","Tyylilaji1","Omistaja1");
        int code = service.add(album);
        assertEquals(0, code);
            //System.out.println(" code1 "+code);
            
        album = new Album("Esittaja2","Nimi2","2021","Tyylilaji2","Omistaja1");
        code = service.add(album);
        assertEquals(0, code);
            //System.out.println(" code2 "+code);
            
        album = new Album("Esittaja1","Nimi3","2021","Tyylilaji1","Omistaja1");
        code = service.add(album);
        assertEquals(0, code);
            //System.out.println(" code3 "+code);
            
        album = new Album("Esittaja2","Nimi4","2021","Tyylilaji2","Omistaja1");
        code = service.add(album);
        assertEquals(0, code);
            //System.out.println(" code4 "+code);

        ArrayList result = new ArrayList();
        result = service.get("","");
        
        assertEquals(4, result.size());
    }
    
    @Test
    public void fetchArtist() throws IOException {
        
          //System.out.println(" fetchEsittaja ");
        
        album = new Album("Esittaja1","Nimi1","2021","Tyylilaji1","Omistaja1");
        int code = service.add(album);
        album = new Album("Esittaja2","Nimi2","2021","Tyylilaji2","Omistaja1");
        code = service.add(album);
        album = new Album("Esittaja1","Nimi3","2021","Tyylilaji1","Omistaja1");
        code = service.add(album);
        album = new Album("Esittaja2","Nimi4","2021","Tyylilaji2","Omistaja1");
        code = service.add(album);

        ArrayList haetut = new ArrayList();
        haetut = service.get("Esittaja","Esittaja1");
        
        assertEquals(2, haetut.size());
    }
    
    @Test
    public void fetchName() throws IOException {
        
          //System.out.println(" fetchNimi ");
        
        album = new Album("Esittaja1","Nimi1","2021","Tyylilaji1","Omistaja1");
        int code = service.add(album);
        album = new Album("Esittaja2","Nimi2","2021","Tyylilaji2","Omistaja1");
        code = service.add(album);
        album = new Album("Esittaja1","Nimi3","2021","Tyylilaji1","Omistaja1");
        code = service.add(album);
        album = new Album("Esittaja2","Nimi4","2021","Tyylilaji2","Omistaja1");
        code = service.add(album);

        ArrayList haetut = new ArrayList();
        haetut = service.get("Nimi","Nimi1");
        
        assertEquals(1, haetut.size());
    }
    
    @Test
    public void fetchYear() throws IOException {
        
            //System.out.println(" fetchVuosi ");
        
        album = new Album("Esittaja1","Nimi1","2021","Tyylilaji1","Omistaja1");
        int code = service.add(album);
        album = new Album("Esittaja2","Nimi2","2021","Tyylilaji2","Omistaja1");
        code = service.add(album);
        album = new Album("Esittaja1","Nimi3","2021","Tyylilaji1","Omistaja1");
        code = service.add(album);
        album = new Album("Esittaja2","Nimi4","2021","Tyylilaji2","Omistaja1");
        code = service.add(album);

        ArrayList haetut = new ArrayList();
        haetut = service.get("Vuosi","2021");
        
        assertEquals(4, haetut.size());
    }
    
    @Test
    public void fetchGenre() throws IOException {
        
            //System.out.println(" fetchTyylilaji ");
        
        album = new Album("Esittaja1","Nimi1","2021","Tyylilaji1","Omistaja1");
        int code = service.add(album);
        album = new Album("Esittaja2","Nimi2","2021","Tyylilaji2","Omistaja1");
        code = service.add(album);
        album = new Album("Esittaja1","Nimi3","2021","Tyylilaji1","Omistaja1");
        code = service.add(album);
        album = new Album("Esittaja2","Nimi4","2021","Tyylilaji2","Omistaja1");
        code = service.add(album);

        ArrayList haetut = new ArrayList();
        haetut = service.get("Tyylilaji","Tyylilaji2");
        
        assertEquals(2, haetut.size());
    }
    
    @Test
    public void fetchOwner() throws IOException {
        
            //System.out.println(" fetchOmistaja ");

        album = new Album("Esittaja1","Nimi1","2021","Tyylilaji1","Omistaja1");
        int code = service.add(album);
        album = new Album("Esittaja2","Nimi2","2021","Tyylilaji2","Omistaja1");
        code = service.add(album);
        album = new Album("Esittaja1","Nimi3","2021","Tyylilaji1","Omistaja1");
        code = service.add(album);
        album = new Album("Esittaja2","Nimi4","2021","Tyylilaji2","Omistaja2");
        code = service.add(album);

        ArrayList haetut = new ArrayList();
        haetut = service.get("Omistaja","Omistaja1");
        
        assertEquals(3, haetut.size());
    }
    
    @Test
    public void showStatistics() throws IOException {
        
            //System.out.println(" showStatistics ");
        for (int i = 0; i < 5; i ++ ) {
            album = new Album("Esittaja" + i,"Nimi" + i,"2021","Tyylilaji","Omistaja1");
            int code = service.add(album);
        }
 
        HashMap map = service.getStatistics();
            //System.out.println(" map.size "+map.size());
        assertEquals(5, map.size());
    }
    
    @After
    public void tearDown() {
        
            //System.out.println(" tearDown ");
        
        file.delete();
    }
    
}
