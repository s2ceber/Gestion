package s2.gestion.actions.modulos.clinica;

import java.sql.Date;
import java.time.LocalDate;

import org.openxava.actions.TabBaseAction;

public class CitasHoyAction extends TabBaseAction  {
    @Override
    public void execute() throws Exception {
	getTab().setConditionValue("fecha", Date.valueOf(LocalDate.now()));
    }
}
