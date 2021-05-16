package katalogi.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import katalogi.dao.*;

/**
 * Sovelluslogiikkaa edustava luokka.
 */
public class KatalogiService {
    
    private List<Album> albums;
    private DBKatalogiDao dao;
    
    public KatalogiService() {
        albums = new ArrayList();
        dao = new DBKatalogiDao();
        albums = dao.getFromDB();
    }
    
    /**
    * Laskee sovelluksen muistissa olevasta levykokoelmasta Tilasto-näkymän
    * tarvitsemat tunnusluvut.
    * 
    * @return tilaston luvut HashMap-oliossa
    */
    public HashMap getStatistics() {

        HashMap stat = new HashMap();

        ArrayList attributes = new ArrayList();
        
        for (int i = 0; i < albums.size(); i++) {

            attributes.add(albums.get(i).getArtist());
        }

        stat.put("Esittäjiä", "" + attributes.stream().distinct().count());
        attributes.clear();
            
        for (int i = 0; i < albums.size(); i++) {

            attributes.add(albums.get(i).getName());
        }
 
        stat.put("Nimiä", "" + attributes.stream().distinct().count());
        attributes.clear();
        
        for (int i = 0; i < albums.size(); i++) {

            attributes.add(albums.get(i).getYear());
        }

        stat.put("Vuosia", "" + attributes.stream().distinct().count());
        attributes.clear();
        
        for (int i = 0; i < albums.size(); i++) {

            attributes.add(albums.get(i).getGenre());
        }

        stat.put("Tyylilajeja", "" + attributes.stream().distinct().count());
        attributes.clear();
        
        for (int i = 0; i < albums.size(); i++) {

            attributes.add(albums.get(i).getOwner());
        }

        stat.put("Omistajia", "" + attributes.stream().distinct().count());
        attributes.clear();
        
        return stat;
    }
    
    /**
    * Poistaa levykokoelmasta parametrina annetun levyn.
    * 
    * @param poistettava määrittää poistettavan levyn
    * @return jäljellä olevat albumit
    */
    public ArrayList<Album> remove(Album param) {

        for (int i = 0; i < albums.size(); i++) {

            if (albums.get(i).sameOrDifferent(param)) {
                
                albums.remove(i);
                break;
            }
        }
        
        dao.saveToDB(albums);
        
        return (ArrayList) albums;
    }
    
        /**
    * Poistaa levykokoelmasta parametrina annetun levyn.
    * 
    * @param poistettava määrittää poistettavan levyn
    * @return jäljellä olevat albumit
    */
    public boolean removeAll() {

        this.albums.clear();

        return dao.emptyTable();
    }
    
    /**
    * Hakee sovelluksen muistissa olevasta levykokoelmasta parametreja vastaavia
    * attribuutteja.
    * 
    * @param param1 määrittää haetun attribuutin ("Esittaja")
    * @param param2 määrittää haetun attribuutin arvon ("Esittaja1")
    * @return hakuparametreja vastaavat albumit
    */
    public ArrayList<Album> get(String param1, String param2) {

        albums = dao.getFromDB();
        
        if (param1.equals("")) {

            return (ArrayList) albums;

        } else {
            
            ArrayList<Album> result = new ArrayList<Album>();
            
            for (int i = 0; i < albums.size(); i++) {
                
                if (param1.equals("Esittaja")) {
                    
                    if (albums.get(i).getArtist().equals(param2)) {
                        result.add(albums.get(i));
                    }
                    
                } else if (param1.equals("Nimi")) {
                    
                    if (albums.get(i).getName().equals(param2)) {
                        result.add(albums.get(i));
                    }
                } else if (param1.equals("Vuosi")) {
                    
                    if (albums.get(i).getYear().equals(param2)) {
                        result.add(albums.get(i));
                    }
                } else if (param1.equals("Tyylilaji")) {
                    
                    if (albums.get(i).getGenre().equals(param2)) {
                        result.add(albums.get(i));
                    }   
                } else if (param1.equals("Omistaja")) {

                    if (albums.get(i).getOwner().equals(param2)) {
                        result.add(albums.get(i));
                    }
                }
            }
            
            return result;
        }
    }
    
    /**
    * Lisää parametrina annetun levyn sovelluksen muistissa olevaan
    * levykokoelmaan ja kutsuu paramFileNameon tallentavaa metodia.
    * 
    * @param album lisättävä albumi
    * @return lisäyksen onnistumisen vahvistava koodi
    * @throws IOException
    */
    public int add(Album album) throws IOException {

        //int code = 0;

        int code = checkAlbum(album);

        if (code < 2) {
            
            albums.add(album);
            
            //boolean ok = true;
            
            boolean ok = dao.saveToDB(albums);
            
            if (!ok) {
                code = 1;
            }
        }

        return code;
    }
    
    /**
    * Tarkistaa levykokoelmaan lisättävän levyn syötetyt attribuutit.
    * @param album tarkistettava albumi
    * @return tarkistuksen tuottama tilakoodi
    */
    private int checkAlbum(Album album) {

        int code = 0;

        if (album.getArtist().equals("") | album.getName().equals("") |
                album.getYear().equals("") | album.getGenre().equals("") |
                album.getOwner().equals("")) {
            code = 3;
        } else {
            try {
                Integer.parseInt(album.getYear());
            } catch (Exception e2) {
                code = 4;
            }
        }

        if (code == 0) {
            for (int i = 0; i < albums.size(); i++) {
                Album vrt = albums.get(i);

                if (vrt.sameOrDifferent(album)) {
                    code = 2;
                }
            }
        }

        return code;
    }
}
