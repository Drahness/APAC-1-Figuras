package com.ieseljust.ad.figures;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class FileManager {

    public FileManager() {

    }

    

    private boolean validaInt(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

    public Boolean Exists(String file) {
    	File f = new File(file);
    	return f.exists();
    }

    public Escena importFromText(String file) {
    	Escena escena;
		escena = new Escena();
		File f = new File(file);
		try { // No tirara nunca, pero me obliga a poner, la excepcion
			int i = 0;
    		FileReader fr = new FileReader(f);
    		BufferedReader bf = new BufferedReader(fr);
    		String[] currentLine; 
    		try {
				while(fr.ready()) {
					currentLine = bf.readLine().split(" ");
					if(currentLine[0].equalsIgnoreCase("dimensions")) {
						int x ,y;
						x = Integer.parseInt(currentLine[1]);
						y = Integer.parseInt(currentLine[2]);
						escena.dimensions(x, y);
					}
					else {
						Figura fig = createFigura(currentLine);
						if(fig == null) {
							System.out.println(String.format("\\u001B[31m Error en linea %d\\u001B[0m",i));
						}
						else {
							escena.add(fig);
						}
					}
					i++;
				}
			} catch (IOException e) {
				System.out.println(String.format("\\u001B[31m Error leyendo el fichero %s\\u001B[0m",f.getName()));
			}
    		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        return escena;

    }

    public Escena importFromObj(String file) {

        /**
         * **********************************************************************
         * TODO: Mètode a implementar: * Llegirà el fitxer indicat, en format
         * d'objectes seriats, i importa * la llista de figures. *
         * **********************************************************************
         */
        // Comentar o elimina aquestes línies quan implementeu el mètode
        Escena escena = null;

        return escena;

    }

    public Boolean exportText(Escena escena, String file) {

        /**
         * ************************************************
         * TODO: Mètode a implementar: * exporta l'escena donada a un fitxer de
         * text, * en format per poder ser importat posteriorment.*
         * ************************************************
         */
        // Comentar o elimina aquestes línies quan implementeu el mètode
        boolean out = false;

        return out;

    }

    public Boolean exportObj(Escena escena, String file) {

        /**
         * **********************************************************
         * TODO: Mètode a implementar: * exporta l'escena donada a un fitxer
         * binari d'objectes, * per poder ser importat posteriorment. *
         * **********************************************************
         */
        // Comentar o elimina aquestes línies quan implementeu el mètode
        boolean out = false;

        return out;

    }

    public Boolean exportSVG(Escena escena, String file) {
        /**
         * **********************************************************
         * TODO: Mètode a implementar: * exporta l'escena donada a un fitxer
         * SVG (format XML). * El fitxer s'haurà de poder obrir amb Inkscape. *
         * **********************************************************
         */
        /*
            <?xmlversion="1.0"encoding="UTF-8"standalone="no"?> 2 <svgheight="500"width="500">
            <rect fill="#ccccee" height="480" width="480" x="10" y="10"/>
            <circle cx="250" cy="250" fill="#aaaaaa" r="100"/>
            <line stroke="#aaaaaa" stroke-width="3" x1="50" x2="450" y1="250" y2="250"/>
            <line stroke="#aaaaaa" stroke-width="3" x1="50" x2="50" y1="50" y2="
            450"/>
            <line stroke="#aaaaaa" stroke-width="3" x1="450" x2="450" y1="40" y2= "450"/>
            </svg>
         */

        // Comentar o elimina aquestes línies quan implementeu el mètode
        boolean out = false;

        return out;

    }

    public Boolean exportJSON(Escena escena, String filename) {

        /**
         * **********************************************
         * TODO: Mètode a implementar: * exporta l'escena donada a un fitxer
         * JSON. * **********************************************
         */
        // Comentar o elimina aquestes línies quan implementeu el mètode
        boolean out = false;

        return out;

    }
    /**
     * Para esto yo haria una factoria. Porque no me gusta hacer copyPaste del main
     * @param comandament
     * @return
     */
    private Figura createFigura(String[] components) {
        String figura = components[0];
        switch (figura){
            case "cercle":
                // Creació d'una figura de la classe cercle
                try{
                    // Extraiem les dimensions
                    int x=Integer.parseInt((components[1]));
                    int y=Integer.parseInt((components[2]));
                    int radi=Integer.parseInt((components[3]));
                    String color=components[4];
                    
                    // Si tot és correcte creem la figura cercle
                    Cercle nouCercle=new Cercle(x, y, radi, color);
                    // I l'afegim a la llista
                    return nouCercle;


                } catch (Exception e){
                    // Si s'ha produït algun error als paràmetres, s'indica un error de sintaxi
                    System.out.println("\u001B[31m Error de sintaxi. La sintaxi correcta és:\ncercle x y radi color \u001B[0m");
                };
                break;
        
            case "rectangle":
                // Creació d'una figura de la classe rectangle
                try{
                    // Extraiem les dimensions
                    int x=Integer.parseInt((components[1]));
                    int y=Integer.parseInt((components[2]));
                    int llarg=Integer.parseInt((components[3]));
                    int alt=Integer.parseInt((components[4]));
                    String color=components[5];
                    
                    // Si tot és correcte creem la figura rectangle
                    Rectangle nouRectangle=new Rectangle(x, y, llarg, alt, color);
                    // I l'afegim a la llista
                    return  nouRectangle;


                } catch (Exception e){
                    // Si s'ha produït algun error als paràmetres, s'indica un error de sintaxi
                    System.out.println("\u001B[31m Error de sintaxi. La sintaxi correcta és:\nrectangle x y llargària altura color \u001B[0m");
                };
                break;

            case "linia":
                // Creació d'una figura de la classe rectangle
                try{
                    // Extraiem les dimensions
                    int x=Integer.parseInt((components[1]));
                    int y=Integer.parseInt((components[2]));
                    int x2=Integer.parseInt((components[3]));
                    int y2=Integer.parseInt((components[4]));
                    String color=components[5];
                    
                    // Si tot és correcte creem la figura linia
                    Linia liniaNova=new Linia(x, y, x2, y2, color);
                    // I l'afegim a la llista
                    return liniaNova;


                } catch (Exception e){
                    // Si s'ha produït algun error als paràmetres, s'indica un error de sintaxi
                    System.out.println("\u001B[31m Error de sintaxi. La sintaxi correcta és:\nlinia x y x2 y2 color \u001B[0m");
                    //System.out.println(e);
                };
                break;
        }
		return null;
    }

}
