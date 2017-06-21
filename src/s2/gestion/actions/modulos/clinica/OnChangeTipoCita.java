package s2.gestion.actions.modulos.clinica;

import org.openxava.actions.OnChangePropertyBaseAction;
import org.openxava.jpa.XPersistence;

import s2.gestion.model.modulos.clinica.TipoCita;
/**
 * Añade un motivo de visita a motivo
 * @author progr
 *
 */
public class OnChangeTipoCita extends OnChangePropertyBaseAction {

    @Override
    public void execute() throws Exception {
	Long tipoCitaId = (Long) getView().getValue("tipo.id");
	if (tipoCitaId==null) return;
	
	TipoCita tipoCita = XPersistence.getManager().find(TipoCita.class, tipoCitaId);
	
	String motivo = getView().getValueString("motivo");
	//String carriageReturn = System.getProperty("line.separator");
	motivo=tipoCita.getNombre()+motivo;
	getView().setValue("motivo", motivo);
	
    }

}
