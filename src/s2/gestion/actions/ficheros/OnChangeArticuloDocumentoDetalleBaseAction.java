package s2.gestion.actions.ficheros;

import java.util.HashMap;

import org.openxava.actions.OnChangePropertyBaseAction;
import org.openxava.jpa.XPersistence;

import s2.gestion.model.ficheros.Articulo;
import s2.gestion.model.ventas.TarifaPrecio;
import s2.gestion.model.ventas.TarifaVenta;

public class OnChangeArticuloDocumentoDetalleBaseAction extends OnChangePropertyBaseAction {

    @Override
    public void execute() throws Exception {
	if (getNewValue() == null)
	    return;

	Articulo articulo = null;
	TarifaPrecio tarifaPrecio = null;
	TarifaVenta tarifaVenta = null;

	articulo = XPersistence.getManager().find(Articulo.class, getNewValue());
	tarifaVenta = getTarifaVenta();

	if (articulo == null || tarifaVenta == null)
	    return;

	getView().setValue("nombre", articulo.getNombre());
	getView().setValue("codigo", articulo.getCodigo());
	getView().setValue("ivaIncluido", tarifaVenta.getIvaIncluido());
	getView().setValueNotifying("tipoIva", articulo.getTipoIva().getTipo());
	
	tarifaPrecio = TarifaPrecio.findByArticuloAndTarifaVenta(articulo, tarifaVenta);
	getView().setValueNotifying("precio", tarifaPrecio.getPrecio());

	getView().setFocus("unidades");
    }

    private TarifaVenta getTarifaVenta() {
	@SuppressWarnings("unchecked")
	HashMap<String, Object> valueCliente = (HashMap<String, Object>) getView().getRoot().getValue("tarifaVenta");
	Object object = valueCliente.get("id");
	TarifaVenta tarifaVenta = XPersistence.getManager().find(TarifaVenta.class, object);
	return tarifaVenta;
    }
}
