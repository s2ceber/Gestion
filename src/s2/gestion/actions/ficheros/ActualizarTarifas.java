package s2.gestion.actions.ficheros;

import org.openxava.actions.TabBaseAction;

import s2.gestion.model.ficheros.Articulo;
import s2.gestion.model.ventas.TarifaPrecio;

public class ActualizarTarifas extends TabBaseAction {

    @Override
    public void execute() throws Exception {
	Articulo articulo = (Articulo) getView().getRoot().getEntity();
	TarifaPrecio.createTarifas(articulo);
	getView().getRoot().refreshCollections();
    }

}
