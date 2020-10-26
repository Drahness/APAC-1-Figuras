package com.ieseljust.ad.figures;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

class FileManager {

    public FileManager() {

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
				while(bf.ready()) {
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
			} finally {
				try {
					bf.close();
					fr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
    		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        return escena;

    }

    /**
     * Se te olvido a�adir el serial number de cada figura.
     * @param file
     * @return
     */
    public Escena importFromObj(String file) {
    	Escena escena = null;
    	try {
    		FileInputStream fis = new FileInputStream(new File(file));
			ObjectInputStream ois = new ObjectInputStream(fis);
			escena = (Escena) ois.readObject();
			ois.close();
			fis.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getLocalizedMessage()+"");
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println(e.getLocalizedMessage()+"");
			
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage()+"");
			
			e.printStackTrace();
		}
        return escena;

    }

    public Boolean exportText(Escena escena, String file) {
    	try {
    		List<Figura> figures = escena.LlistaFigures;
			FileWriter fw = new FileWriter(new File(file));
			StringBuilder b = new StringBuilder();
			for (Figura figura : figures) {
				b.append(figura);
			}
			fw.write(b.toString());
			fw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return false;
    }

    public Boolean exportObj(Escena escena, String file) {
		try {
			FileOutputStream fos;
			fos = new FileOutputStream(new File(file));
	    	ObjectOutputStream oos = new ObjectOutputStream(fos);
	    	oos.writeObject(escena);
	    	oos.close();
	    	fos.close();
	    	return true;
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
        return false;
    }

    /**
     * Comprobado en https://www.rapidtables.com/web/tools/svg-viewer-editor.html con el archivo ADroid.txt
     * @param escena
     * @param file
     * @return
     */
    public Boolean exportSVG(Escena escena, String file) {
    	try {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			Element root = escena.appendTo(doc);
			for (Figura figura : escena.LlistaFigures) {
				figura.appendTo(root);
			}
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(file);
			TransformerFactory.newInstance().newTransformer().transform(source, result);
			return true;
    	} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		}
    	return false;
    }

    public Boolean exportJSON(Escena escena, String filename) {
    	try {
	    	JSONObject root = new JSONObject();
	        JSONObject escenaJSON = escena.appendTo(root);
	        for (Figura figura : escena.LlistaFigures) {
	        	JSONObject json = new JSONObject();
				figura.appendTo(json);
				escenaJSON.getJSONArray(TAGS.FIGURAS).put(json);
			}

	        FileWriter fw = new FileWriter(filename);
			fw.write(root.toString(4));
			fw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return false;

    }
    /**
     * Para esto yo haria una factoria. Porque no me gusta hacer copypaste del main
     * @param components
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
