package s2.gestion.actions.ventas;

import java.util.HashMap;
import java.util.Map;

import org.openxava.actions.OnChangePropertyBaseAction;
import org.openxava.jpa.XPersistence;

import s2.gestion.model.ventas.Cliente;

public class OnChangeClienteDocumentoVenta extends OnChangePropertyBaseAction {

    @Override
    public void execute() throws Exception {
	if (getNewValue() == null)
	    return;

	Cliente cliente = XPersistence.getManager().find(Cliente.class, getNewValue());

	Map<String, Object> mapIdCliente = new HashMap<>();
	mapIdCliente.put("id", cliente.getTarifaVenta().getId());
	getView().setValueNotifying("tarifaVenta", mapIdCliente);
	
	mapIdCliente.put("id", cliente.getFormaPago().getId());
	getView().setValueNotifying("formaPago", mapIdCliente);
    }

}
