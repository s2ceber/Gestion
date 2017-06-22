package s2.gestion.model.modulos.clinica;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.openxava.annotations.View;

import lombok.Getter;
import lombok.Setter;
import s2.gestion.model.base.Documentable;

@Entity
@Table(name = "mod_clinica_plantilla_cuestionario")
@View(members="nombre;documentos;nota;plantillaPreguntas")
public @Getter @Setter class PlantillaCuestionario extends Documentable {
    private String nombre;
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="plantillaCuestionario",cascade=CascadeType.REMOVE)
    @OrderColumn
    private List<PlantillaPregunta> plantillaPreguntas;
}
