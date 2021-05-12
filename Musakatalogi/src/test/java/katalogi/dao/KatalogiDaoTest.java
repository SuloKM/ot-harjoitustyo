/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package katalogi.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import katalogi.domain.Album;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author aleksikoivisto
 */
public class KatalogiDaoTest {
    
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    
    File file;
    Album album;
    List<Album> albums;
    KatalogiDao dao;
    
    @Before
    public void setUp() {

        albums = new ArrayList();
        
        /*
        try {
            
        } catch (IOException ex) {
            Logger.getLogger(KatalogiDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        
        try {
            file = testFolder.newFile("albums.txt");
            dao = new KatalogiDao(file.toString());
        } catch (Exception ex) {
            Logger.getLogger(KatalogiDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        albums.add(new Album("E2","N1","2021","G1","O1"));
        dao.saveToFile(albums);
    }
    
    @Test
    public void writeFileSuccesfull() {

        albums.add(new Album("E2","N1","2021","G1","O1"));
        boolean ok = dao.saveToFile(albums);
        assertEquals(true, ok);
        
        /*
            System.out.println(" writeFileSuccesfull ");
        albums.add(new Album("E2","N1","2021","G1","O1"));
        dao.saveToFile(albums);
        albums = dao.getFromFile();
            System.out.println(" albums toString " + albums.toString());
            System.out.println(" albums size " + albums.size());
        assertEquals(1, albums.size());
            System.out.println(" ..writeFileSuccesfull ");
        */
    }

    @Test
    public void readFileSuccesfull() {
        
            //System.out.println(" readFileSuccesfull ");
        albums = dao.getFromFile();
        assertEquals(2, albums.size());
            //System.out.println(" albums toString " + albums.toString());
            //System.out.println(" albums size " + albums.size());
    }
    
    @After
    public void tearDown() {
        file.delete();
    }
}
