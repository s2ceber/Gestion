package s2.gestion.model.modulos.clinica;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.openxava.annotations.NoFrame;
import org.openxava.annotations.View;
import org.openxava.annotations.Views;

import lombok.Getter;
import lombok.Setter;
import s2.gestion.model.base.Direccion;
import s2.gestion.model.base.Documentable;

/**
 * @author Alberto
 * Modelo para las direcciones de los clientes
 *
 */
@Entity
@Table(name = "mod_clinica_direccion_doctor")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_entidad")
@Views({ @View(members="cliente;direccion"),
    @View(name="clientes",members="direccion")
})
public @Getter @Setter class DireccionDoctor extends Documentable{
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(foreignKey=@ForeignKey(name="fk_doctor"))
    private Doctor doctor;
    
    @Embedded
    @NoFrame
    private Direccion direccion;
}
