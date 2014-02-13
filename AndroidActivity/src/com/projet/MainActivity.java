package com.projet;

import Constance.Constance;
import Controller.RequestController;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	// UI
	Button search_button;
	Button button_store;
	EditText search_text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		search_button = (Button) findViewById(R.id.search_button);
		search_text = (EditText) findViewById(R.id.search_text);
		button_store = (Button) findViewById(R.id.button_stores);

		/*
		 * Click on "button_store" button.
		 */
		button_store.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				RequestController request = new RequestController(
						Constance.MAGASIN_PLUS_PROCHE, MainActivity.this);
				request.start();
			}
		});

		/*
		 * Click on "search_button"
		 */
		search_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				RequestController request = new RequestController(
						Constance.LISTE_ARTICLE_MAGASIN, MainActivity.this);
				request.start();
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}
}
