package com.bluemonkeydev.leagueofsteve;

import java.util.List;

import com.bluemonkeydev.leagueofsteve.data.Person;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends Activity {
	
	private static List<Person> people;
	private int selectedPerson;
	
	public static void setPeople(List<Person> people) {
		DetailActivity.people = people;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		
		selectedPerson = 1;
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			selectedPerson = extras.getInt("los_clicked");
		}
		
		Person person = people.get(selectedPerson);
		
		TextView textView = (TextView) findViewById(R.id.detailName);
		textView.setText(person.name);
		
		textView = (TextView) findViewById(R.id.detailScore);
		textView.setText(""+person.score);
		
		textView = (TextView) findViewById(R.id.detailBestScore);
		textView.setText(""+person.bestScore);
		
		textView = (TextView) findViewById(R.id.detailRank);
		textView.setText(""+person.rank);
		
		textView = (TextView) findViewById(R.id.detailCorrect);
		textView.setText(""+person.correct);
		
		textView = (TextView) findViewById(R.id.detailBestCorrect);
		textView.setText(""+person.bestCorrect);
		
		textView = (TextView) findViewById(R.id.detailWinner);
		textView.setText(person.winner);
		
	}
	
	public void viewBracket(View view) {
		if (selectedPerson >= 0) {
			Person person = people.get(selectedPerson);
			Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(person.url));
			startActivity(browserIntent);
		} else {
			Toast.makeText(view.getContext(), "No URL for this Dude", Toast.LENGTH_LONG).show();
		}
	}

}
