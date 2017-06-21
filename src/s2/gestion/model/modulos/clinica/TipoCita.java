package s2.gestion.model.modulos.clinica;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import s2.gestion.model.base.Identificable;

@Entity
@Table(name = "mod_clinica_tipo_cita")
public @Getter @Setter class TipoCita extends Identificable {
    private String nombre;
    private String descripcion;
}
