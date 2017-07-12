package s2.gestion.actions.ficheros;

import org.openxava.actions.OnChangePropertyBaseAction;
import org.openxava.jpa.XPersistence;

import s2.gestion.model.ficheros.Articulo;
import s2.gestion.model.ventas.Cliente;

public class OnChangeArticuloDocumentoDetalleBaseAction extends OnChangePropertyBaseAction {

    @Override
    public void execute() throws Exception {
	if (getNewValue() == null)
	    return;
	Articulo articulo = XPersistence.getManager().find(Articulo.class, getNewValue());
	if (articulo == null)
	    return;
	Cliente cliente = (Cliente) getView().getRoot().getValue("cliente");
	if (cliente!=null){
	    getView().setValueNotifying("tarifaVenta", cliente.getTarifaVenta());    
	}
	
	getView().setValue("nombre", articulo.getNombre());
	getView().setValue("codigo", articulo.getCodigo());
	
//	getView().setValueNotifying("precio", articulo.getPrecio());
	getView().setFocus("unidades");
    }

}
