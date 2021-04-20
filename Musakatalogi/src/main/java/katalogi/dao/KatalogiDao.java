/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package katalogi.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import katalogi.domain.Levy;

/**
 *
 * @author aleksikoivisto
 */
public class KatalogiDao {
    
    public List<Levy> levyt;
    private String tiedostonNimi;
    private File tiedosto;
    private FileWriter writer;
    
    public KatalogiDao(String file) throws Exception {

        this.tiedostonNimi = file;

        tiedosto = new File(tiedostonNimi);

        levyt = new ArrayList<>();
        
        try {
            
            Scanner reader = new Scanner(tiedosto);
            
            while (reader.hasNextLine()) {

                String[] parts = reader.nextLine().split(";");

                Levy levy = new Levy(parts[0], parts[1], parts[2], parts[3], parts[4]);

                levyt.add(levy);

            }
            
            //boolean poistettu = tiedosto.delete();
            //    System.out.println(" tiedosto poistettu "+poistettu);
        } catch (Exception e) {
            writer = new FileWriter(tiedosto);
            writer.close();
        }
    }
    
    public ArrayList<Levy> poista(Levy poistettava) {

        for (int i = 0; i < levyt.size(); i++) {

            if (levyt.get(i).onkoSama(poistettava)) {
                
                levyt.remove(i);
                break;
            }
        }
        
        return (ArrayList) levyt;
    }
    
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
    
    public boolean tallennaTiedostoon() throws IOException {
        
            //System.out.println("tallennaTiedostoon levyt " + levyt);
        
        String rivi = "";
        boolean ok = true;
        
        if (writer == null) {
            
            writer = new FileWriter(tiedosto); 
        }
        
        for (int i = 0; i < levyt.size(); i++) {
            
            Levy levy = levyt.get(i);
            
            rivi = levy.getEsittaja() + ";" + levy.getNimi()
                    + ";" + levy.getVuosi() + ";" + levy.getTyylilaji() + ";" + levy.getOmistaja();
            
            try {

                writer.write(rivi + "\n");

            } catch (IOException ex) {

                Logger.getLogger(KatalogiDao.class.getName()).log(Level.SEVERE, null, ex);
                ok = false;
            }
        }
        
        return ok;
    }
    
    public int lisaa(Levy levy) throws IOException {

        int koodi = 0;
                
            //System.out.println(" KatalogiDao lisaa levy "+levy);
            
        koodi = tarkistaLevy(levy);

        if (koodi < 2) {
            
            levyt.add(levy);
            
            writer = new FileWriter(this.tiedosto);
            
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
            
            writer.close();
        }

        return koodi;
    }
    
    private int tarkistaLevy(Levy levy) {
        
        int koodi = 0;

        if (levy.getEsittaja().equals("") | levy.getNimi().equals("") |
                levy.getVuosi().equals("") | levy.getTyylilaji().equals("") |
                levy.getOmistaja().equals("")) 
        {
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

        if (koodi == 0) {
            for (int i=0; i < levyt.size(); i++) {
                Levy vrt = levyt.get(i);

                if (vrt.onkoSama(levyt.get(i))) {
                    koodi = 2;
                }
            }
        }

        return koodi;
    }
}