package com.ieseljust.ad.figures;

import java.io.Serializable;


class Punt implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4968424781770969291L;
	// Classe que representa una ubicació a l'escena
    private int x;
    private int y;

    Punt(){
        // Mètode Constructor
        this.x=0;
        this.y=0;
    }

    Punt (int x, int y){
        this.x=x;
        this.y=y;
    }

    // Mètodes getters
    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    // Mètode setter
    public void set(int x, int y){
        this.x=x;
        this.y=y;
    };

}

