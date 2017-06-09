package s2.gestion.actions.ficheros.ejercicio;

import javax.inject.Inject;

import org.openxava.actions.SetDefaultSchemaAction;
import org.openxava.view.View;

import s2.gestion.model.ficheros.Ejercicio;

public class ChangeEjercicioAction extends SetDefaultSchemaAction {
    @Inject
    private View view;
    
    @Override
    public void execute() throws Exception {
	Ejercicio ejercicio = (Ejercicio) getView().getEntity();
	ejercicio.makeDefault();

	setNewDefaultSchema(ejercicio.getNombre());
        super.execute();
    }

    public View getView() {
	return view;
    }

    public void setView(View view) {
	this.view = view;
    }
}
