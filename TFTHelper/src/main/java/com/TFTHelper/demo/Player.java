package com.TFTHelper.demo;

public class Player {
	
    private static Player instance;
    private Deck deck;

    private Player( ) {
    }

    public static Player getInstance() {
        if (instance == null) {
            synchronized (Player.class) {
                if (instance == null) {
                    instance = new Player();
                }
            }
        }
        return instance;
    }

    
	public Deck getDeck() {
		return deck;
	}


	public void setDeck(Deck deck) {
		this.deck = deck;
	}
	
}
