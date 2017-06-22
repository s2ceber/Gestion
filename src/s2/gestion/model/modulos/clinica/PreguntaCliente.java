package s2.gestion.model.modulos.clinica;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import s2.gestion.model.base.Identificable;

@Entity
@Table(name = "mod_clinica_pregunta_cliente")
public @Getter @Setter class PreguntaCliente extends Identificable {
    @ManyToOne(fetch=FetchType.LAZY)
    private CuestionarioCliente cuestionarioCliente;

    private String pregunta;
    
    private Boolean respuesta;
    
    private String observacion;
}
