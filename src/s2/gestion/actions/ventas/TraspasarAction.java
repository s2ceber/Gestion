package s2.gestion.actions.ventas;

import org.openxava.actions.ViewBaseAction;

import s2.gestion.model.ventas.PresupuestoVenta;

public class TraspasarAction extends ViewBaseAction {

    @Override
    public void execute() throws Exception {	
	PresupuestoVenta entity = (PresupuestoVenta) getView().getEntity();
	if (entity==null)return;
	if (entity.getTraspasarA()==null)return;
	
	entity.traspasar(entity.getTraspasarA());
	entity.toString();
    }

}
