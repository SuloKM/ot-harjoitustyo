/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package katalogi.domain;

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
public class AlbumTest {

    @Test
    public void equalWhenSameArtist() {
        Album a1 = new Album("artist1", "name1", "2021", "genre1", "owner1");
        Album a2 = new Album("artist1", "name1", "2021", "genre1", "owner1");
        assertTrue(a1.sameOrDifferent(a2));
    }
    
    @Test
    public void notEqualWhenDifferentArtist() {
        Album a1 = new Album("artist1", "name1", "2021", "genre1", "owner1");
        Album a2 = new Album("artist2", "name1", "2021", "genre1", "owner1");
        assertFalse(a1.sameOrDifferent(a2));
    }
    
    @Test
    public void albumToString() {
        Album a1 = new Album("artist1", "name1", "2021", "genre1", "owner1");
        String albumToString = a1.toString();
        String comparisonString = "Album{artist=artist1, name=name1, year=2021, genre=genre1, owner=owner1}";
        assertEquals(comparisonString, a1.toString());
    }
}
