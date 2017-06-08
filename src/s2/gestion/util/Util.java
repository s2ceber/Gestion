package s2.gestion.util;

import s2.gestion.model.ficheros.Ejercicio;

public class Util {
    
    public static String getEjercicio(){
	return Ejercicio.getDefault().getNombre();
    }
}
