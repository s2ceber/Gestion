package s2.gestion.actions.ficheros;

import org.openxava.actions.ViewBaseAction;

import s2.gestion.model.ficheros.DeleteEjercicioUI;
import s2.gestion.model.ficheros.Ejercicio;

public class GoDeleteEjercicioAction extends ViewBaseAction {
    @Override
    public void execute() throws Exception {
	Ejercicio ejercicio = (Ejercicio) getView().getEntity();

	showDialog();
	getView().setModelName("DeleteEjercicioUI");
	
	DeleteEjercicioUI ui = (DeleteEjercicioUI) getView().getEntity();
	ui.setEjercicioToDelete(ejercicio);
	getView().setModel(ui);

	setControllers("DeleteEjercicio");
    }


}
