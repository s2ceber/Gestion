package s2.gestion.actions.ficheros.ejercicio;

import org.openxava.actions.SetDefaultSchemaAction;
import org.openxava.util.Users;

import com.openxava.naviox.model.User;

import s2.gestion.model.ficheros.Ejercicio;

public class SetEjercicioAction extends SetDefaultSchemaAction {
    @Override
    public void execute() throws Exception {
	String nombre = Ejercicio.getDefault(User.find(Users.getCurrent())).getNombre();
	setNewDefaultSchema(nombre);
	super.execute();
    }
    

}
