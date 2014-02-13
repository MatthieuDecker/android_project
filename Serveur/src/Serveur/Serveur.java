package Serveur;

import Handler.ListeArticleMagasinHandler;
import Handler.MagasinPlusProcheHandler;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Serveur {
    
    
    public static void main(String argv[]) throws Exception {
        String clientMessage;
        byte[] buffer = new byte[1024];
        
        ExecutorService service = Executors.newFixedThreadPool(15);
        
        DatagramSocket connectionUDP = new DatagramSocket(6789);
        
        while(true)          
        {   
            DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
            connectionUDP.receive(receivePacket);
                       
            clientMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
            
            System.out.println(receivePacket.getAddress());
            System.out.println(receivePacket.getPort());
            
            if(clientMessage.charAt(0) == Character.forDigit(Constance.MAGASIN_PLUS_PROCHE, 10))
            {
                MagasinPlusProcheHandler test = new MagasinPlusProcheHandler(connectionUDP, receivePacket.getAddress(), receivePacket.getPort(), clientMessage.substring(1));
                service.execute(test);
            }
            
            else if(clientMessage.charAt(0)== Character.forDigit(Constance.LISTE_ARTICLE_MAGASIN, 10))
            {
                ListeArticleMagasinHandler handler = new ListeArticleMagasinHandler(connectionUDP, receivePacket.getAddress(), receivePacket.getPort(), clientMessage.substring(1));
                service.execute(handler);
            }
        /*    else if(clientMessage.charAt(0)==Character.forDigit(Constantes.NEW_BET, 10))
            {
            }*/
        }
    }
}
