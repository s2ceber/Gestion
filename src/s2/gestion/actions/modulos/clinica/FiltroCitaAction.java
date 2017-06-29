package s2.gestion.actions.modulos.clinica;

import java.time.LocalDate;

import org.openxava.actions.TabBaseAction;

public class FiltroCitaAction extends TabBaseAction {
    private static enum FILTER_TYPE{
	TODAS, HOY, PENDIENTES
    }

    private String tipoFiltro;
    
    @Override
    public void execute() throws Exception {
	String filtro = getTipoFiltro();
	if(FILTER_TYPE.valueOf(filtro)==FILTER_TYPE.TODAS){
	    getTab().setTitleVisible(false);
	    getTab().setBaseCondition("");
	}else if(FILTER_TYPE.valueOf(filtro)==FILTER_TYPE.HOY){
	    getTab().setTitleVisible(true);
	    getTab().setTitle("CitasParaHoy");
		String hoy = LocalDate.now().toString();
		String baseCondition = "${fecha} >='" + hoy + "'";
		getTab().setBaseCondition(baseCondition);
	   // getTab().setConditionValue("fecha", Timestamp.valueOf(LocalDateTime.now()));
	    //getTab().setConditionValue("fecha", "23/06/2017");
	}if(FILTER_TYPE.valueOf(filtro)==FILTER_TYPE.PENDIENTES){
	    getTab().setTitleVisible(true);
	    getTab().setTitle("Pendientes");
	    String baseCondition = "${estado.id}=1";
	    getTab().setBaseCondition(baseCondition);
	}
	getView().refresh();
    }

    public String getTipoFiltro() {
	return tipoFiltro;
    }

    public void setTipoFiltro(String tipoFiltro) {
	this.tipoFiltro = tipoFiltro;
    }

}
