package s2.gestion.model.modulos.clinica;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.openxava.annotations.DescriptionsList;
import org.openxava.annotations.Stereotype;

import lombok.Getter;
import lombok.Setter;
import s2.gestion.model.base.Documentable;

@Entity
@Table(name = "mod_clinica_alerta")
public @Getter @Setter class Alerta extends Documentable {
    @Stereotype("DATETIME")
    private Timestamp fecha;
        
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="fk_cliente")
    @DescriptionsList
    private ClienteClinica cliente;
}
