package s2.gestion.model.base;

import javax.persistence.Embeddable;

import org.openxava.annotations.View;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@View(members="direccion, direccionExtra; codigoPostal, poblacion; provincia; comunidad, pais")
public @Getter @Setter class Direccion {
    private String direccion;
    
    private String direccionExtra;
    
    private String poblacion;
    
    private String codigoPostal;
    
    private String provincia;
    
    private String comunidad;
    
    private String pais;
}
