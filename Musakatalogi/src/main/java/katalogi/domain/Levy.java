/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package katalogi.domain;

/**
 *
 * @author aleksikoivisto
 */
public class Levy {
    
    private String esittaja;
    private String nimi;
    private String vuosi;
    private String tyylilaji;
    private String omistaja;
    private boolean poista;

    public Levy() {
    }
    
    public Levy(String esittaja, String nimi, String vuosi, String tyylilaji, String omistaja) {
        
        this.esittaja   = esittaja;
        this.nimi       = nimi;
        this.vuosi      = vuosi;
        this.tyylilaji  = tyylilaji;
        this.omistaja   = omistaja;
    }
    
    public String getEsittaja() {
        return esittaja;
    }

    public void setEsittaja(String esittaja) {
        this.esittaja = esittaja;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getVuosi() {
        return vuosi;
    }

    public void setVuosi(String vuosi) {
        this.vuosi = vuosi;
    }

    public String getTyylilaji() {
        return tyylilaji;
    }

    public void setTyylilaji(String tyylilaji) {
        this.tyylilaji = tyylilaji;
    }

    public String getOmistaja() {
        return omistaja;
    }

    public void setOmistaja(String omistaja) {
        this.omistaja = omistaja;
    }
    
    public boolean isPoista() {
        return poista;
    }

    public void setPoista(boolean poista) {
        this.poista = poista;
    }
    
    public boolean onkoSama(Levy param) {
        
        if (!this.esittaja.equals(param.getEsittaja())) {
            return false;
        }
        
        if (!this.nimi.equals(param.getNimi())) {
            return false;
        }
        
        if (!this.vuosi.equals(param.getVuosi())) {
            return false;
        }
        
        if (!this.tyylilaji.equals(param.getTyylilaji())) {
            return false;
        }
        
        if (!this.omistaja.equals(param.getOmistaja())) {
            return false;
        }
        
        return true;
    }

    @Override
    public String toString() {
        return "Levy{" + "esittaja=" + esittaja + ", nimi=" + nimi + ", vuosi=" + vuosi + ", tyylilaji=" + tyylilaji + ", omistaja=" + omistaja + '}';
    }
}
