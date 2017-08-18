package s2.gestion.actions;

import java.util.Map;

import org.openxava.actions.SearchByViewKeyAction;
import org.openxava.view.View;

import s2.gestion.model.ficheros.Ejercicio;
import s2.gestion.model.modulos.clinica.Doctor;
import s2.gestion.model.ventas.PresupuestoVenta;

public class SearchByViewKeyGestionAction extends SearchByViewKeyAction {
    @Override
    public void execute() throws Exception {
	if (Ejercicio.class.getSimpleName().equals(getModelName())) {
	    vistaEjercicio();
	}else if(Doctor.class.getSimpleName().equals(getModelName())){
	    vistaDoctor();
	}else if (PresupuestoVenta.class.getSimpleName().equals(getModelName())){
	    vistaPresupuesto();
	}
	super.execute();
    }

    private void vistaPresupuesto() {
	View dnt = getView().getSubview("detallesNoTraspasados");
	dnt.setEditable("codigo",false);
	dnt.setEditable("nombre",false);
	dnt.setEditable("unidades",false);
	dnt.setEditable("precio",false);
	dnt.setEditable("unidadesPendientesTraspaso",false);
	dnt.setEditable("unidadesATraspasar",true);
	
    }

    private void vistaDoctor() {
	getView().getSubview("citas").getCollectionTab().setModelName("Cita");
	getView().getSubview("citas").getCollectionTab().setTabName("citaDoctor");
	
    }

    private void vistaEjercicio() {
	@SuppressWarnings("rawtypes")
	Map key = getView().getKeyValues(); // Nos guardamos la clave
	getView().setViewName(""); // Aquí se borran los datos de la vista
	getView().setValues(key); // Volvemos a poner la clave
	setControllers("Ejercicio");	
    }
    
}
