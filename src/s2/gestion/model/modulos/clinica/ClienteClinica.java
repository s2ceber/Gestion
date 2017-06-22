package s2.gestion.model.modulos.clinica;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.openxava.annotations.Action;
import org.openxava.annotations.AsEmbedded;
import org.openxava.annotations.DescriptionsList;
import org.openxava.annotations.ReferenceView;
import org.openxava.annotations.Tab;
import org.openxava.annotations.View;

import lombok.Getter;
import lombok.Setter;
import s2.gestion.model.ventas.Cliente;

@Entity
@Table(name="mod_clinica_cliente")
@DiscriminatorValue("clinica")
@View(members = "#nombre, nif;datosPersonales{nss; contacto; direccion} contactos{contactos} direcciones{direcciones} otros{documentos; nota} cuestionario{cuestionarioBase;cuestionarioCliente}")
@Tab(properties="nombre, nif, nss, contacto.telefono, direccion.poblacion")
public @Getter @Setter class ClienteClinica extends Cliente {   
    private String nss;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @Transient
    @DescriptionsList
    @Action(value="ClienteClinicaOpt.crearCuestionario")
    private PlantillaCuestionario cuestionarioBase;
    
    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    @AsEmbedded
    @ReferenceView(value="vistaCliente")
    private CuestionarioCliente cuestionarioCliente;
    
    public void crearCuestionario(PlantillaCuestionario plantillaCuestionario){
	CuestionarioCliente cc= new CuestionarioCliente(this, Timestamp.valueOf(LocalDateTime.now()), plantillaCuestionario);
	this.cuestionarioCliente=cc;
    }
}
