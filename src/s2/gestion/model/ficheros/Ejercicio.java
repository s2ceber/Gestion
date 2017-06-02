package s2.gestion.model.ficheros;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.openxava.annotations.DescriptionsList;
import org.openxava.annotations.ListProperties;
import org.openxava.annotations.ListsProperties;
import org.openxava.annotations.Tab;
import org.openxava.annotations.View;
import org.openxava.annotations.Views;
import org.openxava.jpa.XPersistence;

import lombok.Getter;
import lombok.Setter;
import s2.gestion.model.base.Documentable;

/**
 * @author Alberto Modelo para los ejercicios contables de la empresa
 *
 */
@Entity
@Table(name = "ejercicio")
@Views({ @View(members = "nombre;nota;documentos"), @View(name = "newEjercicio", members = "nombre, copiarDe; copiarArticulos") })
@Tab(properties = "nombre, nota")
@NamedQueries({ @NamedQuery(name = "Ejercicio.getAll", query = "select e from Ejercicio e") })
public @Getter @Setter class Ejercicio extends Documentable {
    private String nombre;

    @Transient
    @ManyToOne
    @DescriptionsList(descriptionProperties="nombre")
    private Ejercicio copiarDe;
    
    @Transient
    private Boolean copiarArticulos;
    
}
