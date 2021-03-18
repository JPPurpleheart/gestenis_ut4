package gestenis;

import java.io.*;
import java.util.ArrayList;


public class Torneo  {
    
    private String nombreTorneo;
    private int puntuacion;
    
    /**
     * Constructor sobrecargado de Torneo
     * @param nombreTorneo
     * @param puntuacion
     */
    Torneo (String nombreTorneo,int puntuacion){
        this.nombreTorneo=nombreTorneo;
        this.puntuacion=puntuacion;
    }

    /**
     * Devuelve el nombre del torneo
     * @return
     */
    public String getnombreTorneo() {
        return nombreTorneo;
    }

    /**
     * Asignamos un nombre de torneo
     * @param nombreTorneo del torneo
     */
    public void setnombreTorneo(String nombreTorneo) {
        this.nombreTorneo = nombreTorneo;
    }

    /**
     * Devuelve la puntuacionuación asignada al torneo
     * @return
     */
    public int getpuntuacion() {
        return puntuacion;
    }

    /**
     * Introducimos la puntuacionuación asignada para el torneo
     * @param
     */
    public void setpuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
    /**
     * Carga los datos del fichero en el ArrayList lista
     * y devuelve true si todo ha ido bien o false si ha fallado algo
     * @param fichero
     * @return
     */
    public static ArrayList<Torneo> cargar(File fichero){
        ArrayList<Torneo> lista ;
        try{
        	lista = new ArrayList<Torneo>();
            ObjectInputStream ficheroEntrada ;
            ficheroEntrada = new ObjectInputStream(new FileInputStream(fichero));
            lista = extracted(ficheroEntrada);
            ficheroEntrada.close();
            return lista;
        }catch(ClassNotFoundException cnfe){
            return null;
        }catch(FileNotFoundException fnfe){
            return null;
        }catch (IOException ioe){
            return null;
        }
    }

	/**
	 * Método extraido que realiza la función ficheroEntrada.readObject()
	 * @param ficheroEntrada
	 * @return Retorna un ArrayList<Torneo> readObject
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private static ArrayList<Torneo> extracted(ObjectInputStream ficheroEntrada)
			throws IOException, ClassNotFoundException {
		ArrayList<Torneo> readObject = (ArrayList<Torneo>) ficheroEntrada.readObject();
		return readObject;
	}
	
	/**
     * Guarda los datos del ArrayList lista en el fichero fichero
     * Si todo ha ido bien devuelve true y en caso contrario false 
     * @param lista
     * @param fichero
     * @return
     */
    public static boolean guardar(ArrayList<Torneo> lista,File fichero){
        try{
        	//Fichero de salida
            ObjectOutputStream sal ; 
            sal = new ObjectOutputStream(new FileOutputStream (fichero));
            sal.writeObject(lista);
            sal.flush();
            sal.close();
            return true;
        }catch(FileNotFoundException fnfe){
            return false;
        }catch(IOException ioe){
            return false;
        }
    }
    
    /**
     * Carga los datos del fichero en el ArrayList lista, luego guarda los datos del ArrayList lista en el fichero fichero
     * Si todo ha ido bien devuelve true y en caso contrario false.
     * @param lista
     * @param fichero
     * @return
     */
    public static boolean cargarYGuardar(ArrayList<Torneo> lista, File fichero){
        
    	//cargar
        cargar(fichero);
        //Creamos el Boolean que devuelve y le asignamos el Boolean génerado por el método guardar
        Boolean guardar = guardar(lista, fichero);
        return guardar;
    }
}
