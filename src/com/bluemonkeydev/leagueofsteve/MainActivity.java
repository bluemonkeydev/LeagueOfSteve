package com.bluemonkeydev.leagueofsteve;



import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.bluemonkeydev.leagueofsteve.data.Person;

public class MainActivity extends ListActivity {

	List<Person> people = null;

	public class backgroundLoadListView extends AsyncTask<Void, Void, Void> {

		private static final String S3_PATH = "http://s3.amazonaws.com/bluemonkeydev/los/rankings.xml";

		@Override
		protected void onPostExecute(Void result) {
			MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(MainActivity.this, people);
			setListAdapter(adapter);
		}

		@Override
		protected void onPreExecute() {
			Toast.makeText(MainActivity.this, "Loading current rankings...", Toast.LENGTH_LONG).show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			try {
				InputStream rankings = new URL(S3_PATH).openStream();
				people = Person.loadFromIOStream(rankings);
				DetailActivity.setPeople(people);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

	}

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		new backgroundLoadListView().execute();

		/*
		try {
			people = Person.loadFromIOStream(getAssets().open("rankings.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		DetailActivity.setPeople(people);

		MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(this, people);
		setListAdapter(adapter);
		 */
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onListItemClick(ListView list, View view, int position, long id) {
		super.onListItemClick(list, view, position, id);
		Person person = people.get(position);
		if (person.rickRoll == true) {
			Intent intent = new Intent(this, VideoActivity.class);
			startActivity(intent);
		} else {
			Intent intent = new Intent(this, DetailActivity.class);
			intent.putExtra("los_clicked", position);
			startActivity(intent);
		}
	}

}
