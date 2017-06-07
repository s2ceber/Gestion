package s2.gestion.model.ficheros;

import org.openxava.annotations.Hidden;

import lombok.Getter;
import lombok.Setter;

public @Getter @Setter class DeleteEjercicioUI {
    @Hidden
    private Ejercicio ejercicioToDelete;
       
    public String getNameEjercicioToDelete(){
	return ejercicioToDelete.getNombre();
    }

}
