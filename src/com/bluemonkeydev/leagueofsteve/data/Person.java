package com.bluemonkeydev.leagueofsteve.data;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.util.Log;

public class Person {
	public boolean rickRoll;
    public int rank;
    public String name;
    public String url;
    public int score;
    public int correct;
    public int bestScore;
    public int bestCorrect;
    public String winner;
	
	
	@Override
	public String toString() {
		return name + ":" + rank;
	}
	
	public static List<Person> loadFromIOStream(InputStream file) {

		DocumentBuilderFactory factory;
		DocumentBuilder builder;
		Document d;
		try {
			factory = DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();
			d = builder.parse(file);
		} catch (Exception e) {
			e.printStackTrace();
			Log.v("Person", "UNABLE TO LOAD FILE");
			return null;
		}
		
		List<Person> people = new ArrayList<Person>();
		
		NodeList nodeList = d.getElementsByTagName("person");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Person person = new Person();
			
			Element element = (Element) nodeList.item(i);
			
			person.name = element.getElementsByTagName("name").item(0).getTextContent();
			person.url = element.getElementsByTagName("url").item(0).getTextContent();
			
			String rankString = element.getElementsByTagName("rank").item(0).getTextContent();
			if (rankString != null) {
				person.rank = Integer.parseInt(rankString);
			}
			
			String scoreString = element.getElementsByTagName("score").item(0).getTextContent();
			if (scoreString != null) {
				person.score = Integer.parseInt(scoreString);
			}
			
			String bestScoreString = element.getElementsByTagName("bestscore").item(0).getTextContent();
			if (bestScoreString != null) {
				person.bestScore = Integer.parseInt(bestScoreString);
			}
			
			String correctString = element.getElementsByTagName("correct").item(0).getTextContent();
			if (correctString != null) {
				person.correct = Integer.parseInt(correctString);
			}
			
			String bestCorrectString = element.getElementsByTagName("bestcorrect").item(0).getTextContent();
			if (bestCorrectString != null) {
				person.bestCorrect = Integer.parseInt(bestCorrectString);
			}
			
			person.winner = element.getElementsByTagName("winner").item(0).getTextContent();
			
			Node rickRollNode = element.getElementsByTagName("rickroll").item(0);
			if (rickRollNode != null) {
				String rickRollString = rickRollNode.getTextContent();
				if (rickRollString != null) {
					person.rickRoll = Boolean.parseBoolean(rickRollString);
				}
			}
			
			people.add(person);
		}
		
        Collections.sort(people, new Comparator<Person>() {

            @Override
            public int compare(Person arg0, Person arg1) {
                if (arg0.rank == arg1.rank) {
                    return 0;
                } else if (arg0.rank > arg1.rank) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
        
		return people;
	}

}
