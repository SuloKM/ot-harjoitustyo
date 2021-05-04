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
    
    private List<Levy> levyt;
    private KatalogiDao dao;
    
    public KatalogiService() {
        
            //System.out.println(" KatalogiService 1 "+levyt);
        
        levyt = new ArrayList();
        
        try {
            dao = new KatalogiDao("levyt.txt");
            
            levyt = dao.haeTiedostosta();
            
        } catch (Exception ex) {
            Logger.getLogger(KatalogiService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            //System.out.println(" KatalogiService 2 "+levyt);
    }
    
    /**
    * Laskee sovelluksen muistissa olevasta levykokoelmasta Tilasto-näkymän
    * tarvitsemat tunnusluvut.
    */
    public HashMap tilastoi() {
        
            //System.out.println(" tilastoi ");
        HashMap tilasto = new HashMap();
        
        //levyt.stream()
        
        ArrayList attribuutti = new ArrayList();
        
        for (int i = 0; i < levyt.size(); i++) {
            
                //System.out.println(" getEsittaja "+levyt.get(i).getEsittaja());
            attribuutti.add(levyt.get(i).getEsittaja());
        }
        
            //System.out.println(" distinct "+attribuutti.stream().distinct().count());
        
        tilasto.put("Esittäjiä", "" + attribuutti.stream().distinct().count());
        attribuutti.clear();
            
        for (int i = 0; i < levyt.size(); i++) {
            
                //System.out.println(" getNimi "+levyt.get(i).getNimi());
            attribuutti.add(levyt.get(i).getNimi());
        }
        
            //System.out.println(" distinct "+attribuutti.stream().distinct().count());
            
        tilasto.put("Nimiä", "" + attribuutti.stream().distinct().count());
        attribuutti.clear();
        
        for (int i = 0; i < levyt.size(); i++) {
            
                //System.out.println(" getVuosi "+levyt.get(i).getVuosi());
            attribuutti.add(levyt.get(i).getVuosi());
        }
        
            //System.out.println(" distinct "+attribuutti.stream().distinct().count());
        
        tilasto.put("Vuosia", "" + attribuutti.stream().distinct().count());
        attribuutti.clear();
        
        for (int i = 0; i < levyt.size(); i++) {
            
                //System.out.println(" getTyylilaji "+levyt.get(i).getTyylilaji());
            attribuutti.add(levyt.get(i).getTyylilaji());
        }
        
            //System.out.println(" distinct "+attribuutti.stream().distinct().count());
        
        tilasto.put("Tyylilajeja", "" + attribuutti.stream().distinct().count());
        attribuutti.clear();
        
        for (int i = 0; i < levyt.size(); i++) {
            
                //System.out.println(" getOmistaja "+levyt.get(i).getOmistaja());
            attribuutti.add(levyt.get(i).getOmistaja());
        }
        
            //System.out.println(" distinct "+attribuutti.stream().distinct().count());
        
        tilasto.put("Omistajia", "" + attribuutti.stream().distinct().count());
        attribuutti.clear();
        
        return tilasto;
    }
    
    /**
    * Poistaa levykokoelmasta parametrina annetun levyn.
    * @param poistettava määrittää poistettavan levyn
    * @return jäljellä olevat levyt
    */
    public ArrayList<Levy> poista(Levy poistettava) {

        for (int i = 0; i < levyt.size(); i++) {

            if (levyt.get(i).onkoSama(poistettava)) {
                
                levyt.remove(i);
                break;
            }
        }
        
        dao.tallennaTiedostoon();
        
        return (ArrayList) levyt;
    }
    
    /**
    * Hakee sovelluksen muistissa olevasta levykokoelmasta parametreja vastaavia
    * attribuutteja.
    * @param param1 määrittää haetun attribuutin ("Esittaja")
    * @param param2 määrittää haetun attribuutin arvon ("Esittaja1")
    * @return hakuparametreja vastaavat levyt
    */
    public ArrayList<Levy> hae(String param1, String param2) {

        if (param1.equals("")) {

            return (ArrayList) levyt;

        } else {
            
            ArrayList<Levy> haetut = new ArrayList<Levy>();
            
            for (int i = 0; i < levyt.size(); i++) {
                
                if (param1.equals("Esittaja")) {
                    
                    if (levyt.get(i).getEsittaja().equals(param2)) {
                        haetut.add(levyt.get(i));
                    }
                    
                } else if (param1.equals("Nimi")) {
                    
                    if (levyt.get(i).getNimi().equals(param2)) {
                        haetut.add(levyt.get(i));
                    }
                } else if (param1.equals("Vuosi")) {
                    
                    if (levyt.get(i).getVuosi().equals(param2)) {
                        haetut.add(levyt.get(i));
                    }
                } else if (param1.equals("Tyylilaji")) {
                    
                    if (levyt.get(i).getTyylilaji().equals(param2)) {
                        haetut.add(levyt.get(i));
                    }   
                } else if (param1.equals("Omistaja")) {

                    if (levyt.get(i).getOmistaja().equals(param2)) {
                        haetut.add(levyt.get(i));
                    }
                }
            }
            
            return haetut;
        }
    }
    
    /**
    * Lisää parametrina annetun levyn sovelluksen muistissa olevaan
    * levykokoelmaan ja kutsuu tiedostoon tallentavaa metodia.
    */
    public int lisaa(Levy levy) throws IOException {

        int koodi = 0;

        koodi = tarkistaLevy(levy);

        if (koodi < 2) {
            
            levyt.add(levy);
            
            boolean ok = dao.tallennaTiedostoon();
            
            if (!ok) {
                koodi = 1;
            }
            
            //writer = new FileWriter(this.tiedosto);
            
            /*
            for (int i = 0; i < levyt.size(); i++) {
                
                levy = levyt.get(i);
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

        return koodi;
    }
    
    /**
    * Tarkistaa levykokoelmaan lisättävän levyn syötetyt attribuutit.
    */
    private int tarkistaLevy(Levy levy) {
        
            //System.out.println(" -- tarkistaLevy "+levy);
            //System.out.println(" --- tarkistaLevy "+levyt);
        
        int koodi = 0;

        if (levy.getEsittaja().equals("") | levy.getNimi().equals("") |
                levy.getVuosi().equals("") | levy.getTyylilaji().equals("") |
                levy.getOmistaja().equals("")) {
            // ilmo.setText("Puutteellinen syöte.");
            koodi = 3;
        } else {
            try {
                Integer.parseInt(levy.getVuosi());
            } catch (Exception e2) {
                    
                //ilmo.setText("Virheellinen syöte.");
                koodi = 4;
            }
        }
        
            //System.out.println(" tarkistaLevy2 ");
            //System.out.println(" koodi "+koodi);

        if (koodi == 0) {
                //System.out.println(" -levyt "+levyt);
            for (int i = 0; i < levyt.size(); i++) {
                Levy vrt = levyt.get(i);

                    //System.out.println(" vrt "+vrt);
                    //System.out.println(" levy "+levy);
                
                if (vrt.onkoSama(levy)) {
                    koodi = 2;
                }
                
                    //System.out.println(" koodi "+koodi);
            }
        }
        
            //System.out.println(" koodi- "+koodi);

        return koodi;
    }
}
