package com.projet;

import GPS.GPSTracker;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapArticleActivity extends Activity {

	// Google Map
	private GoogleMap googleMap;
	private LatLng store_position;
	private LatLng user_position;
	private String store_name;
	private String store_address;
	private GPSTracker _gps;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_article);
		
		//Recuperation des informations du magasin
		Intent intent = getIntent();
		Double store_latitude = Double.parseDouble(intent.getStringExtra("STORE_LATITUDE"));
		Double store_longitude = Double.parseDouble(intent.getStringExtra("STORE_LONGITUDE"));
		store_position = new LatLng(store_latitude,store_longitude);
		store_name = intent.getStringExtra("STORE_NAME");
		store_address = intent.getStringExtra("STORE_ADDRESS");
		_gps = new GPSTracker(MapArticleActivity.this);
		user_position = new LatLng(_gps.getLatitude(),_gps.getLongitude());
		

		try {
			// Loading map
			initilizeMap(store_position,store_name);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * function to load map. If map is not created it will create it for you
	 * */
	private void initilizeMap(LatLng store_position,String store_name) {
		if (googleMap == null) {
			googleMap = ((MapFragment) getFragmentManager().findFragmentById(
					R.id.map)).getMap();
			googleMap.addMarker(new MarkerOptions().position(store_position).title(store_name + "\n" + store_address));
			googleMap.addMarker(new MarkerOptions().position(user_position).title("Votre position"));
			googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(store_position, 15));

			// check if map is created successfully or not
			if (googleMap == null) {
				Toast.makeText(getApplicationContext(),
						"Sorry! unable to create maps", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		initilizeMap(store_position,store_name);
	}

}
