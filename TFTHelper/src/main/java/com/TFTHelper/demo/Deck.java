package com.TFTHelper.demo;

import java.util.ArrayList;
import java.util.List;

// 롬복 없음
public class Deck {
	
	
	
	private String name;
	private List<String> champions;
	
    public Deck(String name) {
        this.name = name;
        this.champions = new ArrayList<>();
    }
    
    public Deck() {
    	this.name = "이름 없음";
        this.champions = new ArrayList<>();
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setChampions(List<String> champions) {
		this.champions = champions;
	}

	public String getName() {
        return name;
    }

    public List<String> getChampions() {
        return champions;
    }

    public void addChampion(String champion) {
        this.champions.add(champion);
    }

    @Override
    public String toString() {
        return "Deck{name='" + name + "', champions=" + champions + '}';
    }

//	public void add(Deck deck) {
//		this.deck.add(deck);
//	}
    
    
}
