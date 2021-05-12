package katalogi.domain;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import katalogi.dao.KatalogiDao;

/**
 * Sovelluslogiikkaa edustava luokka.
 */
public class KatalogiService {
    
    private List<Album> albums;
    private KatalogiDao dao;
    private String fileName = "albums.txt";
    
    public KatalogiService(String paramFileName) {
        
            //System.out.println(" KatalogiService 1 "+albums);
        
        albums = new ArrayList();
        
        if (paramFileName.equals("")) {
            paramFileName = fileName;
        }
        
        try {
            //dao = new KatalogiDao("albums.txt");
            dao = new KatalogiDao(paramFileName);
            
            albums = dao.getFromFile();
            
        } catch (Exception ex) {
            Logger.getLogger(KatalogiService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            //System.out.println(" KatalogiService 2 "+albums);
    }
    
    /**
    * Laskee sovelluksen muistissa olevasta levykokoelmasta Tilasto-näkymän
    * tarvitsemat tunnusluvut.
    */
    public HashMap getStatistics() {
        
            //System.out.println(" tilastoi ");
        HashMap stat = new HashMap();
        
        //albums.stream()
        
        ArrayList attributes = new ArrayList();
        
        for (int i = 0; i < albums.size(); i++) {
            
                //System.out.println(" getEsittaja "+albums.get(i).getEsittaja());
            attributes.add(albums.get(i).getArtist());
        }
        
            //System.out.println(" distinct "+attribuutti.stream().distinct().count());
        
        stat.put("Esittäjiä", "" + attributes.stream().distinct().count());
        attributes.clear();
            
        for (int i = 0; i < albums.size(); i++) {
            
                //System.out.println(" getNimi "+albums.get(i).getNimi());
            attributes.add(albums.get(i).getName());
        }
        
            //System.out.println(" distinct "+attribuutti.stream().distinct().count());
            
        stat.put("Nimiä", "" + attributes.stream().distinct().count());
        attributes.clear();
        
        for (int i = 0; i < albums.size(); i++) {
            
                //System.out.println(" getVuosi "+albums.get(i).getVuosi());
            attributes.add(albums.get(i).getYear());
        }
        
            //System.out.println(" distinct "+attribuutti.stream().distinct().count());
        
        stat.put("Vuosia", "" + attributes.stream().distinct().count());
        attributes.clear();
        
        for (int i = 0; i < albums.size(); i++) {
            
                //System.out.println(" getTyylilaji "+albums.get(i).getTyylilaji());
            attributes.add(albums.get(i).getGenre());
        }
        
            //System.out.println(" distinct "+attribuutti.stream().distinct().count());
        
        stat.put("Tyylilajeja", "" + attributes.stream().distinct().count());
        attributes.clear();
        
        for (int i = 0; i < albums.size(); i++) {
            
                //System.out.println(" getOmistaja "+albums.get(i).getOmistaja());
            attributes.add(albums.get(i).getOwner());
        }
        
            //System.out.println(" distinct "+attribuutti.stream().distinct().count());
        
        stat.put("Omistajia", "" + attributes.stream().distinct().count());
        attributes.clear();
        
        return stat;
    }
    
    /**
    * Poistaa levykokoelmasta parametrina annetun levyn.
    * @param poistettava määrittää poistettavan levyn
    * @return jäljellä olevat albums
    */
    public ArrayList<Album> remove(Album param) {

        for (int i = 0; i < albums.size(); i++) {

            if (albums.get(i).sameOrDifferent(param)) {
                
                albums.remove(i);
                break;
            }
        }
        
        dao.saveToFile();
        
        return (ArrayList) albums;
    }
    
    /**
    * Hakee sovelluksen muistissa olevasta levykokoelmasta parametreja vastaavia
    * attribuutteja.
    * @param param1 määrittää haetun attribuutin ("Esittaja")
    * @param param2 määrittää haetun attribuutin arvon ("Esittaja1")
    * @return hakuparametreja vastaavat albums
    */
    public ArrayList<Album> get(String param1, String param2) {

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
    */
    public int add(Album levy) throws IOException {

        int code = 0;

        code = checkAlbum(levy);

        if (code < 2) {
            
            albums.add(levy);
            
            boolean ok = dao.saveToFile();
            
            if (!ok) {
                code = 1;
            }
            
            //writer = new FileWriter(this.paramFileName);
            
            /*
            for (int i = 0; i < albums.size(); i++) {
                
                levy = albums.get(i);
                String rivi = levy.getEsittaja() + ";" + levy.getNimi()
                    + ";" + levy.getVuosi() + ";" + levy.getTyylilaji() + ";" + levy.getOmistaja();

                try {

                    writer.write(rivi + "\n");

                } catch (IOException ex) {

                    koodi = 1;
                    Logger.getLogger(KatalogiDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            */
            
            //writer.close();
        }

        return code;
    }
    
    /**
    * Tarkistaa levykokoelmaan lisättävän levyn syötetyt attribuutit.
    */
    private int checkAlbum(Album levy) {
        
            //System.out.println(" -- tarkistaLevy "+levy);
            //System.out.println(" --- tarkistaLevy "+albums);
        
        int code = 0;

        if (levy.getArtist().equals("") | levy.getName().equals("") |
                levy.getYear().equals("") | levy.getGenre().equals("") |
                levy.getOwner().equals("")) {
            // ilmo.setText("Puutteellinen syöte.");
            code = 3;
        } else {
            try {
                Integer.parseInt(levy.getYear());
            } catch (Exception e2) {
                    
                //ilmo.setText("Virheellinen syöte.");
                code = 4;
            }
        }
        
            //System.out.println(" tarkistaLevy2 ");
            //System.out.println(" koodi "+koodi);

        if (code == 0) {
                //System.out.println(" -albums "+albums);
            for (int i = 0; i < albums.size(); i++) {
                Album vrt = albums.get(i);

                    //System.out.println(" vrt "+vrt);
                    //System.out.println(" levy "+levy);
                
                if (vrt.sameOrDifferent(levy)) {
                    code = 2;
                }
                
                    //System.out.println(" koodi "+koodi);
            }
        }
        
            //System.out.println(" koodi- "+koodi);

        return code;
    }
}
