package com.projet;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Model.Vente;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Affiche la liste des articles en fonction de la recherche de l'utilisateur.
 * 
 * @author matthintosh
 * 
 */
public class ArticleListActivity extends Activity {

	private ArrayList<Vente> _listArticles;
	private Gson gson = new Gson();

	private ListView _listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_article_list);

		// new instance of ArrayList<Vente>
		_listArticles = gson.fromJson(getIntent().getStringExtra("ARTICLES"),
				new TypeToken<ArrayList<Vente>>() {
				}.getType());

		// New arrayList string to add article information
		ArrayList<String> listToAdd = new ArrayList<String>();
		for (int i = 0; i < _listArticles.size(); i++) {
			listToAdd.add(_listArticles.get(i).getArticle().getNom() + "\n"
					+ _listArticles.get(i).getPrix() + "$" + '\n'
					+ _listArticles.get(i).getMagasin().getNom());
		}

		// listView definition
		_listView = (ListView) findViewById(R.id.listViewArticle);
		ArrayAdapter<String> myarrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, listToAdd);
		_listView.setAdapter(myarrayAdapter);

		// Action when you select an item
		_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				//Transmissions des informations du magasin ou se trouve l'article
				Intent intent = new Intent(ArticleListActivity.this,
						MapArticleActivity.class);
				intent.putExtra("STORE_LATITUDE",Double.toString(_listArticles.get(position).getMagasin().getGeolocalisation().getLatitude()));
				intent.putExtra("STORE_LONGITUDE",Double.toString(_listArticles.get(position).getMagasin().getGeolocalisation().getLongitude()));
				intent.putExtra("STORE_NAME", _listArticles.get(position).getMagasin().getNom());
				intent.putExtra("STORE_ADDRESS", _listArticles.get(position).getMagasin().getAddresse().toStringAddress());
				startActivity(intent);
			}

		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.article_list, menu);
		return true;
	}

}
