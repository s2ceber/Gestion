package s2.gestion.actions.modulos.clinica;

import java.sql.Timestamp;
import java.time.LocalDateTime;

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
	cliente.addPreguntas(plantillaCuestionario.getPlantillaPreguntas());
	XPersistence.getManager().persist(cliente);
	
	getView().setValueNotifying("fechaCuestionario",Timestamp.valueOf(LocalDateTime.now()) );
	getView().getSubview("preguntaClientes").refreshCollections();

    }

}
