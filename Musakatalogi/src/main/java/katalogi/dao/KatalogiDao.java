package katalogi.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import katalogi.domain.Album;

/**
 * Pysyväistallennusta edustava luokka.
 */
public class KatalogiDao {
    
    public List<Album> albums;
    private String fileName;
    private File file;
    private FileWriter writer;
    private Scanner reader;
    
    /**
    * Luodaan uusi dao-olio. Samalla, jos parametrin niminen tiedosto
    * on jo olemassa, lukee siihen kirjatut levyt.
    * Jos valmista tiedostoa ei löydy, alustaa uuden.
    */
    public KatalogiDao(String paramFileName) throws Exception {

        this.fileName = paramFileName;

        file = new File(fileName);

        albums = new ArrayList<>();
        
        try {
            
            reader = new Scanner(file);
            
            //boolean poistettu = tiedosto.delete();
            //    System.out.println(" tiedosto poistettu "+poistettu);
            
        } catch (Exception e) {
            writer = new FileWriter(file);
            writer.close();
        }
    }
    
    /**
    * Hakee sovelluksen käynnistysvaiheessa tiedoston sisällön.
    */
    public List getFromFile() {
        
            //System.out.println(" haeTiedostosta "+levyt);

        try {
            reader = new Scanner(file);

            while (reader.hasNextLine()) {

                String[] parts = reader.nextLine().split(";");

                Album levy = new Album(parts[0], parts[1], parts[2], parts[3], parts[4]);

                albums.add(levy);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(KatalogiDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return albums;
    }
    
    /**
    * Tallentaa aiemmin määritettyyn tiedostoon sovelluksen muistissa olevan 
    * levykokoelman.
    */
    public boolean saveToFile() {

            //System.out.println(" saveToFile ");
        
        String row = "";
        boolean ok = true;
        
            //System.out.println(" albums toString " + albums.toString());
            //System.out.println(" albums.size() " + albums.size());
        
        try {
            
            writer = new FileWriter(file);

            for (int i = 0; i < albums.size(); i++) {

                Album album = albums.get(i);

                row = row + album.getArtist() + ";" + album.getName()
                        + ";" + album.getYear() + ";" + album.getGenre() + ";" + album.getOwner();
                
                writer.write(row + "\n");
            }

            writer.close();
            
        } catch (IOException ex) {
            ok = false;
            Logger.getLogger(KatalogiDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ok;
    }
    
    public boolean saveToFile(List<Album> albums) {
        
        this.albums = albums;
        boolean ok = this.saveToFile();
        return ok;
    }
}