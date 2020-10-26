package com.ieseljust.ad.figures;

// Llibreríes per a poder dibuixar 
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

// Definim la classe cercle a partir de la classe figura
// Heretarem per tant, la posició i el color
class Cercle extends Figura implements Serializable {
	public static final String ROOT = "circle";
    /**
	 * 
	 */
	private static final long serialVersionUID = 7773860790286572835L;
	// Té un nou atribut que serà el radi
    private Integer radi;

    // Constructors
    Cercle() {
        // Sense paràmetres:
        super(); // Invoca al constructor del pare
        this.radi = 0;
    }

    Cercle(int x, int y, int radi, String color) {
        // Amb paràmetres:
        super(x - radi, y - radi, color); // Invoquem al constructor pare
        this.radi = radi; // Indiquem el valor del rado
        // Nota: La posició del cercle serà el seu punt central, per
        // aquest motiu restem el radi a X i a Y
    }

    public void renderText() {
        // Escriu les propietats de la figura
        System.out.println("Cercle en (" + this.posicio.getX() + "," + this.posicio.getY() + ") de radi " + this.radi + " i color " + this.color);
    }

    

    public void render(GraphicsContext gc) {
        // Dibuixem el cercle amb el seu color, la posició i el radi
        Color color = Color.web(this.color);
        gc.setFill(color);

        // Per dibuixar al canvas de JavaFX no hi ha una primitiva per dibuixar cercles,
        // per tant, hem de dibuixar un òval. Aquesta figura espera que li indiquem els diàmetres major i menor, 
        // pel que caldrà multiplicar per 2 el radi.
        gc.fillOval(this.posicio.getX(), this.posicio.getY(), this.radi * 2, this.radi * 2);
    }

    public String toString() {
    	return new StringBuilder()
    		.append("cercle").append(' ')
    		.append(posicio).append(' ')
    		.append(radi).append(' ')
    		.append(color).append('\n').toString();
    }

	@Override
	public Map<String, String> getMap() {
		Map<String,String> map = new HashMap<>();
		map.put(TAGS.CX, String.valueOf(posicio.getX() + radi ));
		map.put(TAGS.CY, String.valueOf(posicio.getY() + radi ));
		map.put(TAGS.FILL, color);
		map.put(TAGS.STROKE, color);
		map.put(TAGS.STROKE_WIDTH, "4");
		map.put(TAGS.RADIO, String.valueOf(radi));
		return map;
	}

	@Override
	public String getRoot() {
		return ROOT;
	}
}
