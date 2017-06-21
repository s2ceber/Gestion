package s2.gestion.model.modulos.clinica;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "mod_clinica_pregunta_cliente")
public @Getter @Setter class PreguntaCliente extends Pregunta {
    @ManyToOne(fetch=FetchType.LAZY)
    private CuestionarioCliente cuestionario;
}
