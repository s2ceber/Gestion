package s2.gestion.actions.ficheros;

import org.openxava.actions.NewAction;

public class NewEjercicioAction extends NewAction {

    @Override
    public void execute() throws Exception {
	super.execute();
	getView().setTitle("crearNuevoEjercicio");
	getView().setViewName("newEjercicio");
	addActions("EjercicioCrear.crearEjercicio");
	removeActions("CRUD.save");
	//getView().action
	
    }

}
