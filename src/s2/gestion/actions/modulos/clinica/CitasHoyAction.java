package s2.gestion.actions.modulos.clinica;

import java.time.LocalDate;

import org.openxava.actions.TabBaseAction;
import org.openxava.view.View;

public class CitasHoyAction extends TabBaseAction {
    private Boolean soloHoy;

    @Override
    public void execute() throws Exception {
	View view = getTab().getCollectionView();
		
	removeActions(view);
	
	String baseCondition;
	if (soloHoy) {
	    baseCondition=getConditionCitasHoy();
	    view.addListAction("CitasDoctor.citasTodas");
	} else {
	    baseCondition="${doctor.id}=?";
	    view.addListAction("CitasDoctor.citasHoy");
	}
	
	getTab().setBaseCondition(baseCondition);
	view.getRoot().refreshCollections();
    }

    private void removeActions(View view) {
	view.removeListAction("CitasDoctor.citasHoy");
	view.removeListAction("CitasDoctor.citasTodas");
    }

    private String getConditionCitasHoy() {
	String hoy = LocalDate.now().toString();
	String manana = LocalDate.now().plusDays(1).toString();
	String baseCondition = "${doctor.id}=? and ${fecha} >='" + hoy + "' and ${fecha}<'" + manana + "'";
	return baseCondition;
    }

    public Boolean getSoloHoy() {
	return soloHoy;
    }

    public void setSoloHoy(Boolean soloHoy) {
	this.soloHoy = soloHoy;
    }

}
