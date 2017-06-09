package s2.gestion.actions.compras;

import org.openxava.actions.OnChangePropertyBaseAction;
import org.openxava.jpa.XPersistence;

import s2.gestion.model.ficheros.Articulo;

public class OnChangeArticuloDocumentoCompraDetalleBaseAction extends OnChangePropertyBaseAction {

    @Override
    public void execute() throws Exception {
	if (getNewValue() == null)
	    return;
	Articulo articulo = XPersistence.getManager().find(Articulo.class, getNewValue());
	getView().setValue("nombre", articulo.getNombre());
	getView().setValue("codigo", articulo.getCodigo());
	getView().setValueNotifying("precio", articulo.getPrecio());
	getView().setFocus("unidades");
    }

}
