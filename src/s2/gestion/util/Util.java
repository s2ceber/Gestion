package s2.gestion.util;

import org.openxava.util.Users;

import com.openxava.naviox.model.User;

import s2.gestion.model.ficheros.Ejercicio;

public class Util {
    
    public static String getEjercicio(){
	return Ejercicio.getDefault(User.find(Users.getCurrent())).getNombre();
    }
}
