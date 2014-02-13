package Handler;

import Model.Addresse;
import Model.Article;
import Model.Categorie;
import Model.ListeVentesRequest;
import Model.Magasin;
import Model.Vente;
import Serveur.Constance;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListeArticleMagasinHandler implements Runnable{
    private final Gson gson = new Gson();
       
    private DatagramSocket _Socket;
   // private Geolocalisation _GeolocalisationClient;
    private ListeVentesRequest _ListeVentesRequest;
    private InetAddress _AdresseRetour;
    private String message;
    private int _Port;
    
    public ListeArticleMagasinHandler(DatagramSocket socket, InetAddress adresseRetour, int port, String clientMessage)
    {
        _Socket = socket;
        //_GeolocalisationClient = gson.fromJson(clientMessage, Geolocalisation.class);
        _ListeVentesRequest  = gson.fromJson(clientMessage, ListeVentesRequest.class);    
        _AdresseRetour = adresseRetour;
        _Port = port;
        message = clientMessage;
    }
    
    @Override
    public void run() {
        Connection connection = null;
        
        
        String sqlCommand = "select article.ARTID, article.ARTNOM, categorie_article.CATID, "+
                                    "categorie_article.CATNOM, magasin.MAGID, magasin.MAGNOM, "+
                                    "magasin.MAGLATITUDE, magasin.MAGLONGITUDE, magasin.MAGNOCIVIQUE, "+
                                    "magasin.MAGNOMRUE, magasin.MAGVILLE, magasin.MAGPROVINCE, "+
                                    "magasin.MAGCODEPOSTAL, vends.VENPRIX " +
                            "FROM article " +
                            "JOIN categorie_article on article.CATID = categorie_article.CATID " +
                            "JOIN vends on vends.ARTID = article.ARTID "+
                            "JOIN magasin on magasin.MAGID = vends.MAGID "+
                            "WHERE article.ARTNOM LIKE '%"+_ListeVentesRequest.getRecherche()+"%' ";
        
        String orderBy = "";
        
        if (_ListeVentesRequest.getOrder().equals("prix"))
        {
            orderBy = "order by vends.VENPRIX " ;
        }
        else if (_ListeVentesRequest.getOrder().equals("distance"))
        {
            orderBy = "order by sqrt(pow(? - magasin.MAGLATITUDE, 2) + pow(? - magasin.MAGLONGITUDE, 2)) " ;
        }
      
        sqlCommand += orderBy;    
        
        try {
            connection = DriverManager.getConnection(Constance.URL_BD, Constance.USER_BD, Constance.PASSWORD_BD);
            PreparedStatement prepStmt = 
                    connection.prepareStatement(sqlCommand );
            
            
            if (_ListeVentesRequest.getOrder().equals("distance")){
                prepStmt.setDouble(1, _ListeVentesRequest.getGeolocalisation().getLatitude());
                prepStmt.setDouble(2, _ListeVentesRequest.getGeolocalisation().getLongitude());
            }
            
            ResultSet rs = prepStmt.executeQuery();
        
            List<Vente> listeVentes = new ArrayList<Vente>();
            while(rs.next())
            {
                //Construire la liste des ventes à envoyer au client
                Categorie categorie = new Categorie(rs.getInt("CATID"),
                                                    rs.getString("CATNOM"));
                Article article = new Article(rs.getInt("ARTID"), 
                                               categorie, 
                                              rs.getString("ARTNOM"));
                Addresse addresse = new Addresse(rs.getInt("MAGNOCIVIQUE"), 
                                            rs.getString("MAGNOMRUE"), 
                                            rs.getString("MAGVILLE"), 
                                            rs.getString("MAGPROVINCE"), 
                                            rs.getString("MAGCODEPOSTAL"));
                
                Magasin magasin = new Magasin(rs.getInt("MAGID"), 
                                    rs.getString("MAGNOM"),
                                    rs.getFloat("MAGLATITUDE"), 
                                    rs.getFloat("MAGLONGITUDE"),
                                    addresse);
                
                Vente vente = new Vente(article, magasin,  rs.getFloat("VENPRIX"));
                listeVentes.add(vente);
            }
         
            try {
                byte[] buffer = gson.toJson(listeVentes).getBytes();
                
                DatagramPacket sendPacket = 
                        new DatagramPacket(buffer, 
                                           buffer.length,
                                           _AdresseRetour, 
                                           _Port);
                               
                _Socket.send(sendPacket);
            }
            catch (IOException e) {
                System.out.println("Erreur d'envoit de donnee : " + e.getMessage());
            }	
        } 
        catch (SQLException e) {
            System.out.println("Erreur de connection a la bd : " + e.getMessage());
        } 
        finally {
            if (connection != null) {
                try { 
                    connection.close();
                }
                catch (SQLException ignore) {
                }
            }
        }
    }
}

