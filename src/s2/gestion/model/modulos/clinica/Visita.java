package s2.gestion.model.modulos.clinica;

import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.openxava.annotations.DescriptionsList;
import org.openxava.annotations.Stereotype;
import org.openxava.annotations.View;

import lombok.Getter;
import lombok.Setter;
import s2.gestion.model.base.Documentable;

@Entity
@Table(name = "mod_clinica_visita")
@View(members="fecha, cliente, doctor;motivo,tratamiento")
public @Getter @Setter class Visita extends Documentable {
    @Stereotype("DATETIME")
    private Timestamp fecha;
    
    @Enumerated(EnumType.STRING)
    private EstadoVisita estado;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @DescriptionsList
    private ClienteClinica cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @DescriptionsList
    private Doctor doctor;
    
    @Lob
    private String motivo;

    @Lob
    @Basic(fetch = FetchType.LAZY) //
    @Stereotype("SIMPLE_HTML_TEXT") //
    private String tratamiento;

}
