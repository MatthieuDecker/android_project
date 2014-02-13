/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Gab
 */
public class ListeVentesRequest {
    
    private String _Recherche;
    private String _Order;
    private Geolocalisation _Geolocalisation;
    
    
    public ListeVentesRequest(Geolocalisation _Geolocalisation, String _Recherche, String _Order) {
        this._Geolocalisation = _Geolocalisation;
        this._Recherche = _Recherche;
        this._Order = _Order;
    }

    public Geolocalisation getGeolocalisation() {
        return _Geolocalisation;
    }

    public void setGeolocalisation(Geolocalisation _Geolocalisation) {
        this._Geolocalisation = _Geolocalisation;
    }

    public String getRecherche() {
        return _Recherche;
    }

    public void setRecherche(String _Recherche) {
        this._Recherche = _Recherche;
    }

    public String getOrder() {
        return _Order;
    }

    public void setOrder(String _Order) {
        this._Order = _Order;
    }
    
    
}
