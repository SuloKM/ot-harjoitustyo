package katalogi.domain;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class KatalogiServiceTest {

    File file;
    Album album;
    KatalogiService service;
    List<Album> albums;
    
    @Before
    public void setUp() throws Exception {
        service = new KatalogiService();
        service.removeAll();
    }

    @Test
    public void removingAlbumSuccessfull() throws IOException { 
        album = new Album("Esittaja1","Nimi1","2021","Tyylilaji","Omistaja1");
        int code = service.add(album);
        albums = service.get("", "");
        assertEquals(1, albums.size());
        album = new Album("Esittaja1","Nimi1","2021","Tyylilaji","Omistaja1");
        albums = service.remove(album);
        assertEquals(0, albums.size());
    }
    
    @Test
    public void savingRecordSuccessfull() throws IOException {
        album = new Album("Esittaja1","Nimi1","2021","Tyylilaji","Omistaja1");
        int code = service.add(album);
        assertEquals(0, code);
    }
    
    @Test
    public void savingSameRecordUnsuccessfull() throws IOException {
        album = new Album("Esittaja1","Nimi1","2021","Tyylilaji","Omistaja1");
        int code = service.add(album);
        assertEquals(0, code);
        album = new Album("Esittaja1","Nimi1","2021","Tyylilaji","Omistaja1");
        code = service.add(album);
        assertEquals(2, code);
    }
    
    @Test
    public void savingInvalidInputUnsuccessfull() throws IOException {
        album = new Album("","Nimi1","2021","Tyylilaji","Omistaja1");
        int code = service.add(album);
        assertEquals(3, code);
    }
    
    @Test
    public void fetchAll() throws IOException {
        album = new Album("Esittaja1","Nimi1","2021","Tyylilaji1","Omistaja1");
        int code = service.add(album);
        assertEquals(0, code);    
        album = new Album("Esittaja2","Nimi2","2021","Tyylilaji2","Omistaja1");
        code = service.add(album);
        assertEquals(0, code); 
        album = new Album("Esittaja1","Nimi3","2021","Tyylilaji1","Omistaja1");
        code = service.add(album);
        assertEquals(0, code);  
        album = new Album("Esittaja2","Nimi4","2021","Tyylilaji2","Omistaja1");
        code = service.add(album);
        assertEquals(0, code);
        ArrayList result = new ArrayList();
        result = service.get("","");
        assertEquals(4, result.size());
    }
    
    @Test
    public void fetchArtist() throws IOException {
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
        for (int i = 0; i < 5; i ++ ) {
            album = new Album("Esittaja" + i,"Nimi" + i,"2021","Tyylilaji","Omistaja1");
            int code = service.add(album);
        }
        HashMap map = service.getStatistics();
        assertEquals(5, map.size());
    }
    
    @After
    public void tearDown() {
        service.removeAll();
    }
}
