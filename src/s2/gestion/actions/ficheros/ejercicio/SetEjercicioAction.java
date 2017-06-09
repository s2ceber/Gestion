package s2.gestion.actions.ficheros.ejercicio;

import org.openxava.actions.SetDefaultSchemaAction;

import s2.gestion.model.ficheros.Ejercicio;

public class SetEjercicioAction extends SetDefaultSchemaAction {
    @Override
    public void execute() throws Exception {
	String nombre = Ejercicio.getDefault().getNombre();
	setNewDefaultSchema(nombre);
	super.execute();
    }
    

}
