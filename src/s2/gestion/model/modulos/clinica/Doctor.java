package s2.gestion.model.modulos.clinica;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.openxava.annotations.AsEmbedded;
import org.openxava.annotations.ListAction;
import org.openxava.annotations.ListProperties;
import org.openxava.annotations.NoFrame;
import org.openxava.annotations.ReferenceView;
import org.openxava.annotations.View;
import org.openxava.annotations.Views;

import lombok.Getter;
import lombok.Setter;
import s2.gestion.model.base.Contacto;
import s2.gestion.model.base.Direccion;
import s2.gestion.model.base.Documentable;

@Entity
@Table(name = "mod_clinica_doctor")
@Views({ @View(members = "#nombre, nif; contacto; direccion; contactos{contactos} direcciones{direcciones} otros{documentos; nota} citas{citas}") })
public @Getter @Setter class Doctor extends Documentable {
    private String nombre;
    private String nif;
    
    @Embedded
    @ReferenceView("basico")
    @NoFrame
    private Contacto contacto;
    
    @Embedded
    @NoFrame
    private Direccion direccion;    
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "doctor", cascade = CascadeType.REMOVE)
    @AsEmbedded
    @OrderColumn
    @ListProperties(value="contacto.aliasContacto, contacto.telefono, contacto.email")
    private List<ContactoDoctor> contactos;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "doctor", cascade = CascadeType.REMOVE)
    @AsEmbedded
    @OrderColumn()
    @ListProperties(value="direccion.direccion, direccion.poblacion, direccion.codigoPostal, direccion.provincia")
    private List<DireccionDoctor> direcciones;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "doctor",cascade=CascadeType.REMOVE)
    //@AsEmbedded
    //@OrderBy("fecha desc")
    @ListAction(value="CitasDoctor.citasHoy")
    @ListProperties(value="fecha, cliente.nombre, estado.nombre, motivo, tratamiento")
    private Collection<Cita> citas;

}
