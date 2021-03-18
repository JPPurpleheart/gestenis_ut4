package gestenis;

import java.io.*;
import java.util.ArrayList;


public class Tenista {
    private String nombre;
    private int edad;
    private ArrayList<Torneo> palmares;
	private static ArrayList<Tenista> lista;
    
    /**
     * Constructor sobrecargado de Tenista
     * @param nombre
     * @param edad
     */
    Tenista(String nombre,int edad){
        this.nombre=nombre;
        this.edad=edad;
        //lista de torneos
        palmares=new ArrayList<Torneo>();
    }

    /**
     * Obtenemos el nombre del tenista
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asignamos el nombre del tenista
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Nos devuelve la edad del tenista
     * @return la edad
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Asigna la edad del tenista
     * @param edad
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }
    /*
     * Devuelve el palmarés con los nombres de los torneos ganados
     * @return
     */
    public String[] getPalmares(){
        String[] p = new String[palmares.size()];
        for(int i=0;i<p.length;i++){
            p[i] = palmares.get(i).getnombreTorneo();
        }
        return p;
    }
    /**
     * Añade el torneo ganado al palmarés
     * @param torneo
     */
    public void aniadirPalmares(Torneo torneo){
        palmares.add(torneo);
    }
    
    /*
     * Recorre un bucle acumulando la puntuación obtenida
     * 
     */
    public int getPuntuacionATP(){
    	int p = 0;
        for(Torneo t:palmares){
            p += t.getpuntuacion();
        }
        return p;
    }
    
    /**
     * Carga en fichero el archivo seleccionado e introduce
     * sus datos en el ArrayList Tenista y devuelve lista
     * @param fichero
     * @return
     */
	public static ArrayList<Tenista> cargar(File fichero, ArrayList<Tenista> lista){
        ObjectInputStream ficheroEntrada;
        try{
            ficheroEntrada = new ObjectInputStream(new FileInputStream(fichero));
            ArrayList<Tenista> readObject = extracted(ficheroEntrada);
			lista = readObject;
			ficheroEntrada.close();
			return lista;
        }catch(ClassNotFoundException onfe){
            return null;
        }catch(FileNotFoundException fnfe){
            return null;
        }catch(IOException ioe){
            return null;
        }
    }
    /**
     * Graba en fichero los datos del ArrayList lista
     * devolviendo true si ha sido todo correcto o false en caso contrario
     * @param lista
     * @param fichero
     * @return
     */
    public static boolean guardar(ArrayList<Tenista> lista, File fichero){
        try{
            ObjectOutputStream ficheroSalida = new ObjectOutputStream(new FileOutputStream(fichero));
            ficheroSalida.writeObject(lista);
            ficheroSalida.flush();
             ficheroSalida.close();
            return true;
        }catch(FileNotFoundException fnfe){
            return false;
        }catch(IOException ioe){
            return false;
        }
    }
    
    /**
     * Método que carga y guarda un fichero
     * @param lista
     * @param fichero
     * @return Devuelve true si se guarda correctamente
     */
    public static boolean cargarGuardar(ArrayList<Tenista> lista, File fichero){
    	//cargar
    	cargar(fichero, lista);
        //Creamos el Boolean que devuelve y le asignamos el Boolean génerado por el método guardar
        Boolean guardar = guardar(lista, fichero);
        return guardar;
    }

    /**
	 * Método extraido que realiza la función ficheroEntrada.readObject()
	 * @param ficheroEntrada
	 * @return Retorna un ArrayList<Tenista> readObject
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private static ArrayList<Tenista> extracted(ObjectInputStream ficheroEntrada)
			throws IOException, ClassNotFoundException {
		ArrayList<Tenista> readObject = (ArrayList<Tenista>) ficheroEntrada.readObject();
		return readObject;
	}
    
}
