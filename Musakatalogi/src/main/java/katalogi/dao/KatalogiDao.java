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

            //System.out.println(" KatalogiDao ");
        
        this.tiedostonNimi = file;

        tiedosto = new File(tiedostonNimi);
        
            //System.out.println(" tiedosto "+tiedosto);

        levyt = new ArrayList<>();
        
        try {
            
            Scanner reader = new Scanner(tiedosto);
            
            while (reader.hasNextLine()) {
                
                    //System.out.println(" reader.hasNextLine ");
                
                String[] parts = reader.nextLine().split(";");
                
                for (int i=0; i<parts.length;i++) {
                    
                    //System.out.println(" parts "+i+ ": "+parts[i]);
                }

                Levy levy = new Levy(parts[0],parts[1],parts[2],parts[3],parts[4]);
                
                    //System.out.println(" levy "+levy);
                    
                levyt.add(levy);
                
                    //System.out.println(" levyt haettu ");
            }
            
            //boolean poistettu = tiedosto.delete();
            //    System.out.println(" tiedosto poistettu "+poistettu);
        }   
        catch (Exception e) {
            writer = new FileWriter(tiedosto);
            writer.close();
        }
    }
    
    public int lisaa(Levy levy) throws IOException {

        int koodi = 0;
                
            //System.out.println(" KatalogiDao ");
            //System.out.println(" levy "+levy);

        
            
        koodi = tarkistaLevy(levy);

            //System.out.println(" koodi "+koodi);

        if (koodi < 2) {
            
            levyt.add(levy);
            
            writer = new FileWriter(this.tiedosto);
            
            for (int i=0; i < levyt.size(); i++) {
                
                levy = levyt.get(i);
                String rivi = levy.getEsittaja()+";"+levy.getNimi()
                    +";"+levy.getVuosi()+";"+levy.getTyylilaji()+";"+levy.getOmistaja();
                
                //System.out.println(" levy "+levy);

                try {

                    writer.write(rivi+"\n");

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

            //System.out.println(" tarkistaLevy "+levy);
        
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

                    //System.out.println(" vrt "+vrt);

                if (levy.getEsittaja().equals(vrt.getEsittaja())) {
                    if (levy.getNimi().equals(vrt.getNimi())) {
                        if (levy.getVuosi().equals(vrt.getVuosi())) {
                            if (levy.getTyylilaji().equals(vrt.getTyylilaji())) {
                                if (levy.getOmistaja().equals(vrt.getOmistaja())) {
                                    koodi = 2;
                                }
                            }
                        }
                    }
                }
            }
        }
        
            //System.out.println(" koodi "+koodi);
        
        return koodi;
    }
}