package katalogi.domain;

/**
 *
 * Yksittäistä äänilevyä kuvaava luokka
 */
public class Album {
    
    private String artist;
    private String name;
    private String year;
    private String genre;
    private String owner;

    public Album() {
    }
    
    public Album(String artist, String name, String year, String genre, String owner) {
        
        this.artist     = artist;
        this.name       = name;
        this.year       = year;
        this.genre      = genre;
        this.owner      = owner;
    }
    
    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
    
    /**
    *
    * Päättelee kahden levyn attribuuttien perusteella ovatko sama.
    * @param 
    */
    public boolean sameOrDifferent(Album param) {
        
        if (!this.artist.equals(param.getArtist())) {
            return false;
        }
        
        if (!this.name.equals(param.getName())) {
            return false;
        }
        
        if (!this.year.equals(param.getYear())) {
            return false;
        }
        
        if (!this.genre.equals(param.getGenre())) {
            return false;
        }
        
        if (!this.owner.equals(param.getOwner())) {
            return false;
        }
        
        return true;
    }

    @Override
    public String toString() {
        return "Album{" + "artist=" + artist + ", name=" + name + ", year=" + year + ", genre=" + genre + ", owner=" + owner + '}';
    }
}
