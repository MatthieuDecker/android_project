package Model;

public class Article {
    private int _ArtID;
    private Categorie _Categorie;
    private String _Nom;

    
    public Article(int _ArtID, Categorie _Categorie, String _Nom) {
        this._ArtID = _ArtID;
        this._Categorie = _Categorie;
        this._Nom = _Nom;
    }

    public String getNom(){
        return _Nom;
    }

    public Categorie getCategorie(){
        return _Categorie;
    }

    public void setNom(String nom){
        _Nom = nom;
    }

    public void setCategorie(Categorie categorie){
        _Categorie = categorie;
    }
}
