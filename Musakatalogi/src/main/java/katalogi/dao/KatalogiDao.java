/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package katalogi.dao;

import java.util.List;
import katalogi.domain.Album;

/**
 *
 * @author aleksikoivisto
 */
public interface KatalogiDao {
    
    List<Album> getFromDB();
    
    boolean saveToDB(List<Album> albums);
    
    boolean emptyTable();
}
