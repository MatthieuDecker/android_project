package Model;

public class Magasin {
    private int _MagasinID;
    private String _Nom;
    private Geolocalisation _Geolocalisation;
    private Addresse _Addresse;

    public Magasin(int magasinID, String nom, double latitude, double longitude, Addresse addresse){
        _MagasinID = magasinID;
        _Nom = nom;
        _Geolocalisation = new Geolocalisation(latitude, longitude);
        _Addresse = addresse;
    }
    
    public Magasin(int magasinID, String nom, Geolocalisation geolocalisation){
        _MagasinID = magasinID;
        _Nom = nom;
        _Geolocalisation = geolocalisation;
    }
    
    public String getNom(){
        return _Nom;
    }

    public void setNom(String nom){
        _Nom = nom;
    }
    
    public Geolocalisation getGeolocalisation(){
        return _Geolocalisation;
    }

    public void setGeolocalisation(Geolocalisation geolocalisation){
        _Geolocalisation = geolocalisation;
    }

    public Addresse getAddresse(){
        return _Addresse;
    }

    public void setAddresse(Addresse addresse){
        _Addresse = addresse;
    }
}
