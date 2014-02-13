package Controller;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import Constance.Constance;
import GPS.GPSTracker;
import Model.Geolocalisation;
import Model.ListeVentesRequest;
import Model.Magasin;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.gson.Gson;
import com.projet.ArticleListActivity;
import com.projet.R;

public class RequestController extends Thread {

	// For gson
	private Gson gson = new Gson();

	// For socket connection
	private int socket_port = 6789;
	private DatagramSocket _Socket;
	private DatagramPacket _sendPacket;
	private InetAddress _Local;
	private int _Request;

	// for request and received
	private String buffer;
	private byte[] final_request;

	// main activity
	private Activity _Activity;

	// GPS
	GPSTracker _GPS;

	// for the response
	private Magasin _Shop;

	public RequestController(int request, Activity activity) {
		_Request = request;
		_Activity = activity;
		_GPS = new GPSTracker(_Activity);
	}

	@Override
	public void run() {
		switch (_Request) {
		case Constance.MAGASIN_PLUS_PROCHE:
			if (_GPS.canGetLocation() == false) {
				_Activity.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						_GPS.showSettingsAlert();
					}
				});
			} else {
				Geolocalisation geo = new Geolocalisation(_GPS.getLatitude(),
						_GPS.getLongitude());
				buffer = "1" + gson.toJson(geo);
				final_request = buffer.getBytes();
				sendRequest(final_request);
				receiveRequest(Constance.MAGASIN_PLUS_PROCHE);
			}
			break;

		case Constance.LISTE_ARTICLE_MAGASIN:
			EditText search_text = (EditText) _Activity
					.findViewById(R.id.search_text);
			CheckBox price_order = (CheckBox) _Activity.findViewById(R.id.checkBox1);
			String articleToFind = search_text.getText().toString();
			String classement = "distance";
			if(price_order.isChecked()){
				classement="prix";
			}
			
			// Texte de recherche vide
			if (articleToFind.isEmpty()) {
				_Activity.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						AlertDialog.Builder alert = new AlertDialog.Builder(
								_Activity);
						alert.setMessage("Tapez le nom d'un article \n Par exemple:\nbananes, tournevis...");
						alert.setTitle("Champs recherche vide !");
						alert.show();
					}
				});
			} else {
				ListeVentesRequest test = new ListeVentesRequest(
						new Geolocalisation(_GPS.getLatitude(),
								_GPS.getLongitude()), articleToFind, classement);
				buffer = "2" + gson.toJson(test);
				final_request = buffer.getBytes();
				sendRequest(final_request);
				receiveRequest(Constance.LISTE_ARTICLE_MAGASIN);
			}
			break;
		}
	}

	/*
	 * Connect to the server Change your Constance.LOCAL_ADDRESS with your
	 * computer local address. This function send the request to the server
	 */
	public void sendRequest(byte[] request) {

		try {
			_Socket = new DatagramSocket();
			_Local = InetAddress.getByName(Constance.LOCAL_ADDRESS);
			_sendPacket = new DatagramPacket(request, request.length, _Local,
					socket_port);
			_Socket.send(_sendPacket);

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/*
	 * Receive packet from the server
	 */
	public void receiveRequest(int request) {
		byte[] receiveData = new byte[4096];
		DatagramPacket receivePacket = new DatagramPacket(receiveData,
				receiveData.length);

		try {
			_Socket.receive(receivePacket);
			String server_response = new String(receivePacket.getData(), 0,
					receivePacket.getLength());

			switch (request) {

			/*
			 * The user wants to know where is the nearest store from his
			 * position.
			 */
			case Constance.MAGASIN_PLUS_PROCHE:
				_Shop = gson.fromJson(server_response, Magasin.class);
				_Activity.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						AlertDialog.Builder alert = new AlertDialog.Builder(
								_Activity);
						alert.setTitle("Magasin le plus proche");
						alert.setMessage(_Shop.getNom() + ": \n" +_Shop.getAddresse().toStringAddress());
						alert.show();
					}
				});
				break;

			case Constance.LISTE_ARTICLE_MAGASIN:
				final String list_article = server_response;
				_Activity.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						Intent intent = new Intent(_Activity,
								ArticleListActivity.class).putExtra("ARTICLES",
								list_article);
						_Activity.startActivity(intent);
					}
				});

				break;

			default:
				Log.d("TEST", "reponse par defaut...");

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
