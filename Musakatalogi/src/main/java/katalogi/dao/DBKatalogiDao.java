package katalogi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import katalogi.domain.Album;

/**
 *
 * Pysyväistallennusta edustava luokka
 */
public class DBKatalogiDao implements KatalogiDao {

    private Connection db;
    private Statement s;
    ResultSet rs;

    public DBKatalogiDao() {
        
        try {
            db = DriverManager.getConnection("jdbc:sqlite:albums.db");

            s = db.createStatement();

            s.execute("CREATE TABLE Albums (id INTEGER PRIMARY KEY, artist TEXT, name TEXT, year TEXT, genre TEXT, owner TEXT)");

        } catch (SQLException ex) {

        }
    }
    
    /*
    * Hakee tietokannasta kaikki levyt.
    * @return kaikki levyt 
    */
    @Override
    public List<Album> getFromDB() {

        List<Album> albums = new ArrayList();
        Album album;
        
        try {

            rs = s.executeQuery("SELECT * FROM Albums");
            
            while (rs.next()) {

                album = new Album();
                album.setArtist(rs.getString("artist"));
                album.setName(rs.getString("name"));
                album.setYear(rs.getString("year"));
                album.setGenre(rs.getString("genre"));
                album.setOwner(rs.getString("owner"));
                albums.add(album);
            }
        } catch (SQLException ex) {

        }
        
        return albums;
    }

    /**
     * Tallentaa tietokantaan kaikki muistissa olevat levyt.
     * Ensin tyhjentää taulun.
     * 
     * @param albums Levykokoelma
     * @return kuittaus onnistumisesta true/false
     */
    @Override
    public boolean saveToDB(List<Album> albums) {

        boolean ok = true;
        Album album;
        
        try {
            s = db.createStatement();
            s.execute("DELETE FROM Albums");
        } catch (SQLException ex) {
            ok = false;
        }

        if (ok) {
            for (int i = 0; i < albums.size(); i++) {

                album = albums.get(i);

                try {

                    PreparedStatement p = db.prepareStatement("INSERT INTO Albums (artist, name , year, genre, owner) VALUES (?,?,?,?,?)");

                    p.setString(1, album.getArtist());
                    p.setString(2, album.getName());
                    p.setString(3, album.getYear());
                    p.setString(4, album.getGenre());
                    p.setString(5, album.getOwner());

                    p.execute();

                } catch (SQLException ex) {
                    ok = false;
                }
            }
        }
        
        return ok;
    }
    
    /**
     * Tyhjentää testejä varten Albums-taulun
     * 
     * @return tieto onnistumisesta true/false
     */
    @Override
    public boolean emptyTable() {
        
        boolean ok = true;
        
        try {
            s = db.createStatement();
            s.execute("DELETE FROM Albums");
        } catch (SQLException ex) {
            ok = false;
        }
        
        return ok;
    }
}
