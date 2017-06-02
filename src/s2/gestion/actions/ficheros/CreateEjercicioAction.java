package s2.gestion.actions.ficheros;

import org.openxava.actions.NewAction;

public class CreateEjercicioAction extends NewAction {

    @Override
    public void execute() throws Exception {
	super.execute();
	getView().setTitle("crearNuevoEjercicio");
	getView().setViewName("newEjercicio");
	addActions("Ejercicio.crearEjercicio");
	//getView().action
	
    }

}
