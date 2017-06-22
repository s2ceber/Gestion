package s2.gestion.actions.modulos.clinica;

import org.openxava.actions.ViewBaseAction;
import org.openxava.jpa.XPersistence;

import s2.gestion.model.modulos.clinica.ClienteClinica;
import s2.gestion.model.modulos.clinica.PlantillaCuestionario;

public class CrearCuestionarioAction extends ViewBaseAction {

    @Override
    public void execute() throws Exception {
	Long id = (Long) getView().getValue("cuestionarioBase.id");
	if (id==null){
	    return;
	}
	
	PlantillaCuestionario plantillaCuestionario = XPersistence.getManager().find(PlantillaCuestionario.class, id);
	
	ClienteClinica cliente = (ClienteClinica) getView().getEntity();
	cliente.crearCuestionario(plantillaCuestionario);
	
	
	getView().setValueNotifying("cuestionarioCliente.fecha", cliente.getCuestionarioCliente().getFecha());
	getView().setValueNotifying("cuestionarioCliente.nombre", cliente.getCuestionarioCliente().getNombre());
	getView().setValueNotifying("cuestionarioCliente.clientePreguntas", cliente.getCuestionarioCliente().getPreguntaClientes());
	getView().getSubview("cuestionarioCliente").getSubview("preguntaClientes").refreshCollections();
    }

}
