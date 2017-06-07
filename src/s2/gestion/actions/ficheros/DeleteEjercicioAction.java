package s2.gestion.actions.ficheros;

import org.openxava.actions.DeleteAction;

import s2.gestion.model.ficheros.Ejercicio;

public class DeleteEjercicioAction extends DeleteAction {
    private Boolean deleteSchema=false;
    
    @Override
    public void execute() throws Exception {
	closeDialog();
        Ejercicio ejercicio = (Ejercicio) getView().getEntity();
        ejercicio.setDeleteSchema(getDeleteSchema());
        super.execute();
    }

    public Boolean getDeleteSchema() {
	return deleteSchema;
    }

    public void setDeleteSchema(Boolean deleteSchema) {
	this.deleteSchema = deleteSchema;
    }
}
