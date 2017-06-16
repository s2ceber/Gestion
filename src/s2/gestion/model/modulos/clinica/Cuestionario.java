package s2.gestion.model.modulos.clinica;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.openxava.annotations.DescriptionsList;

import lombok.Getter;
import lombok.Setter;
import s2.gestion.model.base.Documentable;

@Entity
@Table(name = "mod_clinica_cuestionario")
public @Getter @Setter class Cuestionario extends Documentable {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="fk_cliente")
    @DescriptionsList
    private ClienteClinica cliente;
    
    private String pregunta1;
    
    private String pregunta2;

}
