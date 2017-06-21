package s2.gestion.model.modulos.clinica;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "mod_clinica_cuestionario_cliente")
public @Getter @Setter class CuestionarioCliente extends Cuestionario {
    @ManyToOne(fetch=FetchType.LAZY)
    private ClienteClinica cliente;
    
    private Timestamp fecha;
}
