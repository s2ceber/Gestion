package s2.gestion.actions.ficheros.ejercicio;

import javax.inject.Inject;

import org.openxava.actions.SetDefaultSchemaAction;
import org.openxava.util.Users;
import org.openxava.view.View;

import com.openxava.naviox.model.User;

import s2.gestion.model.ficheros.Ejercicio;

public class ChangeEjercicioAction extends SetDefaultSchemaAction {
    @Inject
    private View view;
    
    @Override
    public void execute() throws Exception {
	Ejercicio ejercicio = (Ejercicio) getView().getEntity();
	ejercicio.makeDefault(User.find(Users.getCurrent()));

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
