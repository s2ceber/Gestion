package s2.gestion.actions.modulos.clinica;

import org.openxava.actions.OnChangePropertyBaseAction;
import org.openxava.jpa.XPersistence;

import s2.gestion.model.modulos.clinica.MotivoVisita;
/**
 * Añade un motivo de visita a motivo
 * @author progr
 *
 */
public class OnChangeMotivoVisita extends OnChangePropertyBaseAction {

    @Override
    public void execute() throws Exception {
	Long motivoVisitaId = (Long) getView().getValue("motivoVisita.id");
	if (motivoVisitaId==null) return;
	
	MotivoVisita motivoVisita = XPersistence.getManager().find(MotivoVisita.class, motivoVisitaId);
	
	String motivo = getView().getValueString("motivo");
	//String carriageReturn = System.getProperty("line.separator");
	motivo=motivoVisita.getNombre()+motivo;
	getView().setValue("motivo", motivo);
	
    }

}
