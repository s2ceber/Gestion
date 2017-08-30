package s2.gestion.model.modulos.clinica;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.openxava.annotations.ReadOnly;
import org.openxava.annotations.View;

import lombok.Getter;
import lombok.Setter;
import s2.gestion.model.base.Identificable;

@Entity
@Table(name = "mod_clinica_pregunta_cliente")
@View(members="plantillaPregunta.texto; respuesta; observacion")
public @Getter @Setter class PreguntaCliente extends Identificable {
    @ManyToOne(fetch = FetchType.LAZY)
    private ClienteClinica clienteClinica;

    @ManyToOne(fetch = FetchType.LAZY)
    private PlantillaPregunta plantillaPregunta;

    private Boolean respuesta;

    private String observacion;
    
    @ReadOnly
    public String getTexto(){
	if (plantillaPregunta!=null)
	return plantillaPregunta.getTexto();
	
	return null;
    }

}
