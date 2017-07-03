package s2.gestion.model.ficheros;

import javax.persistence.*;

import org.openxava.annotations.DescriptionsList;
import org.openxava.annotations.NoCreate;
import org.openxava.annotations.NoModify;
import org.openxava.annotations.PreCreate;
import org.openxava.annotations.PreDelete;
import org.openxava.annotations.ReadOnly;
import org.openxava.annotations.Stereotype;
import org.openxava.annotations.Tab;
import org.openxava.annotations.View;
import org.openxava.annotations.Views;
import org.openxava.jpa.XPersistence;
import org.openxava.util.XavaException;

import com.openxava.naviox.model.User;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Alberto Modelo para los ejercicios contables de la empresa
 *
 */
@Entity
@Table(name = "v_ejercicio")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_entidad")
@Views({ @View(members = "nombre;nota;documentos"),
	@View(name = "newEjercicio", members = "nombre, copiarDe; copiarArticulos") })
@Tab(properties = "nombre, nota")
@NamedQueries({ @NamedQuery(name = "Ejercicio.getAll", query = "select e from Ejercicio e") })
public @Getter @Setter class Ejercicio {
    @Id @Column(length=40)
    @ReadOnly(notForViews = "newEjercicio")
    private String nombre;
    
    @Version
    private Integer versionOptBlq;
    
    @Stereotype("FILES") @Column(length=32)
    private String documentos;
    
    @Column(columnDefinition="text") 
    @Basic(fetch = FetchType.LAZY) //
    @Stereotype("SIMPLE_HTML_TEXT") // 
    private String nota;    

    @Transient
    @ManyToOne
    @NoCreate
    @NoModify
    @DescriptionsList(descriptionProperties = "nombre")
    private Ejercicio copiarDe;

    @Transient
    private Boolean copiarArticulos;
    @Transient
    private Boolean deleteSchema;

    @PreCreate
    public void createEjercicio() {
	String schemaOrigen;
	boolean addDatos;
	if (copiarDe == null) {
	    schemaOrigen = "public";
	    addDatos = false;
	} else {
	    schemaOrigen = copiarDe.getNombre();
	    addDatos = true;
	}
	// String sqlQuery=String.format(sql, schemaOrigen, nombre, addDatos);
	Query query = XPersistence.getManager()
		.createNativeQuery("select count(*) from public.clone_schema(?1, ?2, ?3);");
	query.setParameter(1, schemaOrigen);
	query.setParameter(2, getNombre());
	query.setParameter(3, addDatos);
	query.getResultList();
    }

    @PreDelete
    public void deleteEjercicio() {
	if (deleteSchema == true) {
	    Query query = XPersistence.getManager().createNativeQuery("select count(*) from public.delete_schema(?1);");
	    query.setParameter(1, getNombre());
	    query.getResultList();
	}
    }

    public void makeDefault(User user) {
	ContadorGlobal contador = ContadorGlobal.getDefault(user);
	contador.setEjercicio(this);
	ContadorGlobal.setDefault(contador);
    }

    public static Ejercicio getDefault(User user) {
	Ejercicio myEjercicio = ContadorGlobal.getDefault(user).getEjercicio();
	if (myEjercicio == null) {
	    throw new XavaException("noInitEjercicio");
	}
	return myEjercicio;
    }

}
