package s2.gestion.model.modulos.clinica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.openxava.annotations.Action;
import org.openxava.annotations.AsEmbedded;
import org.openxava.annotations.DescriptionsList;
import org.openxava.annotations.ListProperties;
import org.openxava.annotations.Tab;
import org.openxava.annotations.View;

import lombok.Getter;
import lombok.Setter;
import s2.gestion.model.ventas.Cliente;

@Entity
@Table(name = "mod_clinica_cliente")
@DiscriminatorValue("clinica")
@View(members = "#nombre, nif;datosPersonales{nss; contacto; direccion} contactos{contactos} direcciones{direcciones} otros{documentos; nota} cuestionario{cuestionarioBase;fechaCuestionario;preguntaClientes}")
@Tab(properties = "nombre, nif, nss, contacto.telefono, direccion.poblacion")
public @Getter @Setter class ClienteClinica extends Cliente {
    private String nss;

    @ManyToOne(fetch = FetchType.LAZY)
    @Transient
    @DescriptionsList
    @Action(value = "ClienteClinicaOpt.crearCuestionario")
    private PlantillaCuestionario cuestionarioBase;

    private Date fechaCuestionario;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clienteClinica", cascade = CascadeType.ALL)
    @OrderColumn
    @AsEmbedded
    @ListProperties("plantillaPregunta.texto, respuesta, observacion")
    private List<PreguntaCliente> preguntaClientes;

    public void addPregunta(PreguntaCliente pregunta) {
	if (preguntaClientes == null) {
	    preguntaClientes = new ArrayList<>();
	}
	preguntaClientes.add(pregunta);
    }

    public void addPregunta(PlantillaPregunta plantillaPregunta) {
	PreguntaCliente pc = new PreguntaCliente();
	pc.setClienteClinica(this);
	pc.setPlantillaPregunta(plantillaPregunta);
	pc.setRespuesta(plantillaPregunta.getRespuesta());
	pc.setObservacion(plantillaPregunta.getObservacion());
	addPregunta(pc);
    }

    public void addPreguntas(List<PlantillaPregunta> plantillaPreguntas) {
	if (preguntaClientes == null) {
	    preguntaClientes = new ArrayList<>();
	}
	for (PlantillaPregunta plantillaPregunta : plantillaPreguntas) {
	    if (!existPlantillaInPreguntas(plantillaPregunta)) {
		addPregunta(plantillaPregunta);
	    }
	}
    }

    private boolean existPlantillaInPreguntas(PlantillaPregunta plantillaPregunta) {
	for (PreguntaCliente pregunta : preguntaClientes) {
	    if (pregunta.getPlantillaPregunta().getId().equals(plantillaPregunta.getId())) {
		return true;
	    }
	}
	return false;
    }
}
