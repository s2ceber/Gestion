package s2.gestion.model.modulos.clinica;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.openxava.annotations.View;

import lombok.Getter;
import lombok.Setter;
import s2.gestion.model.ventas.Cliente;

@Entity
@Table(name="mod_clinica_cliente")
@DiscriminatorValue("clinica")
@View(members = "#nombre, nif;nss; contacto; direccion; contactos{contactos} direcciones{direcciones} otros{documentos; nota} cuestionario{cuestionario}")
public @Getter @Setter class ClienteClinica extends Cliente {   
    private String nss;
    
    private CuestionarioCliente cuestionario;
}
