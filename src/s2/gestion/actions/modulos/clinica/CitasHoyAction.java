package s2.gestion.actions.modulos.clinica;

import javax.inject.Inject;

import org.openxava.actions.ViewBaseAction;
import org.openxava.tab.Tab;

public class CitasHoyAction extends ViewBaseAction  {
    @Inject 
    private Tab tab;
    
    private String viewObject;
    
    @Override
    public void execute() throws Exception {
	//tab.setConditionValue("estado", 1);
	getView().getSubview("citas").getCollectionTab().setConditionValue("motivo", "tttt");
	getView().getSubview("citas").refreshCollections();
	
    }

    public String getViewObject() {
	return viewObject;
    }

    public void setViewObject(String viewObject) {
	this.viewObject = viewObject;
    }
}
