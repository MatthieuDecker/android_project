package Handler;

import Model.Addresse;
import Model.Geolocalisation;
import Model.Magasin;
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

public class MagasinPlusProcheHandler implements Runnable{
    private final Gson gson = new Gson();
       
    private DatagramSocket _Socket;
    private Geolocalisation _GeolocalisationClient;
    private InetAddress _AdresseRetour;
    private int _Port;
    
    public MagasinPlusProcheHandler(DatagramSocket socket, InetAddress adresseRetour, int port, String clientMessage)
    {
        _Socket = socket;
        _GeolocalisationClient = gson.fromJson(clientMessage, Geolocalisation.class);
        _AdresseRetour = adresseRetour;
        _Port = port;
    }
    
    @Override
    public void run() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(Constance.URL_BD, Constance.USER_BD, Constance.PASSWORD_BD);
            PreparedStatement prepStmt = 
                    connection.prepareStatement("select * " +
                                                "from magasin " +
                                                "order by sqrt(pow(? - MAGLATITUDE, 2) + pow(? - MAGLONGITUDE, 2)) " +
                                                "LIMIT 0,1 ;");
            prepStmt.setDouble(1, _GeolocalisationClient.getLatitude());
            prepStmt.setDouble(2, _GeolocalisationClient.getLongitude());
            
            ResultSet rs = prepStmt.executeQuery();
            rs.first();
            Addresse addresse = new Addresse(rs.getInt(5), 
                                            rs.getString(6), 
                                            rs.getString(7), 
                                            rs.getString(8), 
                                            rs.getString(9));
            Magasin magasin = new Magasin(rs.getInt(1), 
                                    rs.getString(2),
                                    rs.getFloat(3), 
                                    rs.getFloat(4),
                                    addresse);
            try {
                byte[] buffer = gson.toJson(magasin).getBytes();
                
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
