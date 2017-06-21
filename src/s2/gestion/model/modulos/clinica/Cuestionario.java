package s2.gestion.model.modulos.clinica;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import s2.gestion.model.base.Documentable;

@Entity
@Table(name = "mod_clinica_cuestionario")
public @Getter @Setter class Cuestionario extends Documentable {
    private String nombre;
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="cuestionario",cascade=CascadeType.REMOVE)
    @OrderColumn
    private List<Pregunta> preguntas;
}
