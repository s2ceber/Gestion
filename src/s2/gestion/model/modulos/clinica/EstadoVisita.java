package s2.gestion.model.modulos.clinica;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.openxava.model.Identifiable;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "mod_clinica_estado_visita")
public @Getter @Setter class EstadoVisita extends Identifiable{
    private String nombre;
    private String nota;
}
