package s2.gestion.model.ficheros;

import javax.persistence.*;

import org.openxava.annotations.Tab;
import org.openxava.annotations.View;

import lombok.Getter;
import lombok.Setter;
import s2.gestion.model.base.Documentable;

/**
 * @author Alberto
 * Modelo para los ejercicios contables de la empresa
 *
 */
@Entity
@Table(name = "ejercicio")
@View(members="nombre;nota;documentos")
@Tab(properties="nombre, nota")
public @Getter @Setter class Ejercicio extends Documentable{
    private String nombre;
}
