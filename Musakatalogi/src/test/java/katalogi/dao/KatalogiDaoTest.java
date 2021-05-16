/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package katalogi.dao;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
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

    Album album;
    List<Album> albums;
    DBKatalogiDao dao;
    
    @Before
    public void setUp() {

        albums = new ArrayList();

        dao = new DBKatalogiDao();

        for (int i = 0; i < 4; i ++ ) {
            albums.add(new Album("E" + i,"N" + i,"2021","G1","O1"));
        }

        boolean ok = dao.emptyTable();
    }
    
    @Test
    public void writeDBSuccesfull() {

        boolean ok = true;

        ok = dao.saveToDB(albums);
        
        assertTrue(ok);
    }
    
    @Test
    public void readDBSuccesfull() {
        
        boolean ok = true;
        
        ok = dao.saveToDB(albums);
        
        assertTrue(ok);
        
        albums = dao.getFromDB();

        assertEquals(4, albums.size());
    }
}
