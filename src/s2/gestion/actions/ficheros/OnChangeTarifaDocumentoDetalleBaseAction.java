package s2.gestion.actions.ficheros;

import org.openxava.actions.OnChangePropertyBaseAction;
import org.openxava.jpa.XPersistence;

import s2.gestion.model.ficheros.Articulo;
import s2.gestion.model.ventas.TarifaPrecio;
import s2.gestion.model.ventas.TarifaVenta;

public class OnChangeTarifaDocumentoDetalleBaseAction extends OnChangePropertyBaseAction {

    @Override
    public void execute() throws Exception {
	if (getNewValue() == null)
	    return;
	TarifaVenta tarifa = XPersistence.getManager().find(TarifaVenta.class, getNewValue());
	if (tarifa == null)
	    return;
	Articulo articulo = (Articulo) getView().getValue("articulo");
	if (articulo==null)
	    return;
	TarifaPrecio tarifaPrecio = TarifaPrecio.findByArticuloAndTarifaVenta(articulo, tarifa);
	getView().setValueNotifying("precio", tarifaPrecio.getPrecio());
	getView().setFocus("unidades");

    }

}
