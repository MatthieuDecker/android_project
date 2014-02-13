
package Model;

public class Addresse {
    private int _NoCivique;
    private String _NomRue;
    private String _Ville;
    private String _CodePostal;
    private String _Province;

    public Addresse(int _NoCivique, String _NomRue, String _Ville,  String _Province, String _CodePostal) {
        this._NoCivique = _NoCivique;
        this._NomRue = _NomRue;
        this._Ville = _Ville;
        this._Province = _Province;
        this._CodePostal = _CodePostal;
    }

    public int getNoCivique() {
        return _NoCivique;
    }

    public void setNoCivique(int _NoCivique) {
        this._NoCivique = _NoCivique;
    }

    public String getNomRue() {
        return _NomRue;
    }

    public void setNomRue(String _NomRue) {
        this._NomRue = _NomRue;
    }

    public String getVille() {
        return _Ville;
    }

    public void setVille(String _Ville) {
        this._Ville = _Ville;
    }

    public String getCodePostal() {
        return _CodePostal;
    }

    public void setCodePostal(String _CodePostal) {
        this._CodePostal = _CodePostal;
    }

    public String getProvince() {
        return _Province;
    }

    public void setProvince(String _Province) {
        this._Province = _Province;
    }
    
    
}
