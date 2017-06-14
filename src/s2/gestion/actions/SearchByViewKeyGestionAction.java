package s2.gestion.actions;

import java.util.Map;

import org.openxava.actions.SearchByViewKeyAction;

import s2.gestion.model.ficheros.Ejercicio;

public class SearchByViewKeyGestionAction extends SearchByViewKeyAction {
    @Override
    public void execute() throws Exception {
	if (Ejercicio.class.getSimpleName().equals(getModelName())) {
	    vistaEjercicio();
	}
	super.execute();
    }

    private void vistaEjercicio() {
	@SuppressWarnings("rawtypes")
	Map key = getView().getKeyValues(); // Nos guardamos la clave
	getView().setViewName(""); // Aquí se borran los datos de la vista
	getView().setValues(key); // Volvemos a poner la clave
	setControllers("Ejercicio");	
    }
    
}
