package s2.gestion.actions.ficheros;

import org.openxava.actions.SaveAction;

public class CreateEjercicioAction extends SaveAction {

    @Override
    public void execute() throws Exception {
	String nombre = getView().getValueString("nombre");
	try {
//	    Ejercicio.createEjercicio(nombre);
	    super.execute();
	} catch (Exception e) {
	    addError("No se puede crear el ejercicio", nombre);
	}
	
    }

}
