package com.bluemonkeydev.leagueofsteve;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bluemonkeydev.leagueofsteve.data.Person;

public class MySimpleArrayAdapter extends ArrayAdapter<Person> {

	private final Context context;

	public MySimpleArrayAdapter(Context context, List<Person> values) {
		super(context, R.layout.activity_main, values);
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.label);
		TextView rankView = (TextView) rowView.findViewById(R.id.rank);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
		
		final Person person = getItem(position);
		
		if (person.rank <= 3) {
			textView.setTextColor(Color.GREEN);
			imageView.setImageResource(R.drawable.money_dollar);
		} else {
		    imageView.setImageResource(R.drawable.money_dollar_none);
		}
		textView.setText(person.name);
		rankView.setText(""+person.rank);
	    
		return rowView;
	}
   
} 