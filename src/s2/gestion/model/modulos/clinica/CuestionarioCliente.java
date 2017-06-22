package s2.gestion.model.modulos.clinica;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.openxava.annotations.AsEmbedded;
import org.openxava.annotations.DescriptionsList;
import org.openxava.annotations.ListProperties;
import org.openxava.annotations.View;
import org.openxava.annotations.Views;

import lombok.Getter;
import lombok.Setter;
import s2.gestion.model.base.Documentable;

@Entity
@Table(name = "mod_clinica_cuestionario_cliente")
@Views({ @View(members = "fecha, nombre, clienteClinica;documentos; preguntaClientes; nota"),
	@View(name = "vistaCliente", members = "fecha, nombre;documentos; preguntaClientes; nota") })
public @Getter @Setter class CuestionarioCliente extends Documentable {
    public CuestionarioCliente(ClienteClinica cliente, Timestamp fecha, PlantillaCuestionario plantillaCuestionario) {
	super();
	this.clienteClinica = cliente;
	this.fecha = fecha;
	this.nombre = plantillaCuestionario.getNombre();
	addPreguntas(plantillaCuestionario.getPlantillaPreguntas());
    }

    public CuestionarioCliente() {
	// TODO Auto-generated constructor stub
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @DescriptionsList
    private ClienteClinica clienteClinica;

    private Timestamp fecha;

    private String nombre;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cuestionarioCliente", cascade = CascadeType.ALL)
    @OrderColumn
    @AsEmbedded
    @ListProperties("pregunta, respuesta, observacion")
    private List<PreguntaCliente> preguntaClientes;

    public void addPregunta(PreguntaCliente pregunta){
	if (preguntaClientes==null){
	    preguntaClientes=new ArrayList<>();
	}
	preguntaClientes.add(pregunta);
    }
    public void addPregunta(String pregunta, Boolean respuesta, String observacion) {
	PreguntaCliente pc = new PreguntaCliente();
	pc.setCuestionarioCliente(this);
	pc.setPregunta(pregunta);
	pc.setRespuesta(respuesta);
	pc.setObservacion(observacion);
	addPregunta(pc);
    }

    public void addPregunta(PlantillaPregunta plantillaPregunta) {
	addPregunta(plantillaPregunta.getTexto(), plantillaPregunta.getRespuesta(), plantillaPregunta.getObservacion());
    }

    public void addPreguntas(List<PlantillaPregunta> plantillaPreguntas) {
	for (PlantillaPregunta plantillaPregunta : plantillaPreguntas) {
	    addPregunta(plantillaPregunta);
	}
    }
}
