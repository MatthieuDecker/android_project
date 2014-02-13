package Model;

public class Categorie {
    private int _CategorieID;
    private String _Nom;

    public Categorie(int categorieID, String nom){
        _CategorieID = categorieID;
        _Nom = nom;
    }

    public String getNom(){
        return _Nom;
    }

    public void setNom(String nom){
        _Nom = nom;
    }
}
