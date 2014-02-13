/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *Contien un objet Article, un objet Magasin et le prix
 * @author Gab
 */
public class Vente {
    private Article _Article;
    private Magasin _Magasin;
    private double _Prix;

    public Vente(Article _Article, Magasin _Magasin, double _Prix) {
        this._Article = _Article;
        this._Magasin = _Magasin;
        this._Prix = _Prix;
    }

    public Article getArticle() {
        return _Article;
    }

    public void setArticle(Article _Article) {
        this._Article = _Article;
    }

    public Magasin getMagasin() {
        return _Magasin;
    }

    public void setMagasin(Magasin _Magasin) {
        this._Magasin = _Magasin;
    }

    public double getPrix() {
        return _Prix;
    }

    public void setPrix(double _Prix) {
        this._Prix = _Prix;
    }
}
