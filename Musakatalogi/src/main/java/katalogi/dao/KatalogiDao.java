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
import katalogi.domain.Levy;

/**
 * Pysyväistallennusta edustava luokka.
 */
public class KatalogiDao {
    
    public List<Levy> levyt;
    private String tiedostonNimi;
    private File tiedosto;
    private FileWriter writer;
    private Scanner reader;
    
    /**
    * Luodaan uusi dao-olio. Samalla, jos parametrin niminen tiedosto
    * on jo olemassa, lukee siihen kirjatut levyt.
    * Jos valmista tiedostoa ei löydy, alustaa uuden.
    */
    public KatalogiDao(String file) throws Exception {

        this.tiedostonNimi = file;

        tiedosto = new File(tiedostonNimi);

        levyt = new ArrayList<>();
        
        try {
            
            reader = new Scanner(tiedosto);
            
            //boolean poistettu = tiedosto.delete();
            //    System.out.println(" tiedosto poistettu "+poistettu);
        } catch (Exception e) {
            writer = new FileWriter(tiedosto);
            writer.close();
        }
    }
    
    /**
    * Hakee sovelluksen käynnistysvaiheessa tiedoston sisällön.
    */
    public List haeTiedostosta() {
        
            //System.out.println(" haeTiedostosta "+levyt);

        try {
            reader = new Scanner(tiedosto);

            while (reader.hasNextLine()) {

                String[] parts = reader.nextLine().split(";");

                Levy levy = new Levy(parts[0], parts[1], parts[2], parts[3], parts[4]);

                levyt.add(levy);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(KatalogiDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return levyt;
    }
    
    /**
    * Tallentaa aiemmin määritettyyn tiedostoon sovelluksen muistissa olevan 
    * levykokoelman.
    */
    public boolean tallennaTiedostoon() {

        String rivi = "";
        boolean ok = true;
        
        try {

            if (writer == null) {
                writer = new FileWriter(tiedosto);
            }

            for (int i = 0; i < levyt.size(); i++) {

                Levy levy = levyt.get(i);

                rivi = levy.getEsittaja() + ";" + levy.getNimi()
                        + ";" + levy.getVuosi() + ";" + levy.getTyylilaji() + ";" + levy.getOmistaja();

                writer.write(rivi + "\n");
            }
        } catch (IOException ex) {
            Logger.getLogger(KatalogiDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ok;
    }
}