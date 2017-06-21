package s2.gestion.model.modulos.clinica;

import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.openxava.annotations.DefaultValueCalculator;
import org.openxava.annotations.DescriptionsList;
import org.openxava.annotations.NoCreate;
import org.openxava.annotations.NoModify;
import org.openxava.annotations.OnChange;
import org.openxava.annotations.Stereotype;
import org.openxava.annotations.View;
import org.openxava.calculators.CurrentDateCalculator;

import lombok.Getter;
import lombok.Setter;
import s2.gestion.actions.modulos.clinica.OnChangeTipoCita;
import s2.gestion.model.base.Documentable;

@Entity
@Table(name = "mod_clinica_cita")
@View(members="fecha, cliente, doctor;tipo, estado; motivo,tratamiento;documentos[documentos];nota")
public @Getter @Setter class Cita extends Documentable {
    @Stereotype("DATETIME")
    @DefaultValueCalculator(value=CurrentDateCalculator.class)
    private Timestamp fecha;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey=@ForeignKey(name="fk_estado"))
    @DescriptionsList
    private EstadoCita estado;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey=@ForeignKey(name="fk_cliente"))
    @DescriptionsList(descriptionProperties="nombre, nif")
    private ClienteClinica cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @NoModify @NoCreate
    @JoinColumn(foreignKey=@ForeignKey(name="fk_doctor"))
    @DescriptionsList(descriptionProperties="nombre")
    private Doctor doctor;
    
    @ManyToOne
    @DescriptionsList(descriptionProperties="nombre")
    @Transient
    @NoModify
    @OnChange(value=OnChangeTipoCita.class)
    private TipoCita tipo;    
    
    @Column(columnDefinition="text")
    @Stereotype("MEMO")
    private String motivo;

    @Column(columnDefinition="text")
    @Basic(fetch = FetchType.LAZY) //
    @Stereotype("MEMO") //
    private String tratamiento;

}
