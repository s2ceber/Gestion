package s2.gestion.model.ventas;

import javax.persistence.*;

import org.openxava.annotations.View;
import org.openxava.annotations.Views;

import lombok.Getter;
import lombok.Setter;
import s2.gestion.model.base.Documentable;

/**
 * @author Alberto
 * Modelo para las direcciones de los clientes
 *
 */
@Entity
@Table(name = "direccion_cliente")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_entidad")
@Views({ @View(members="cliente;direccion, direccionExtra; poblacion; codigoPostal"),
    @View(name="clientes",members="direccion, direccionExtra; poblacion; codigoPostal")
})
public @Getter @Setter class DireccionCliente extends Documentable{
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="fk_cliente")
    private Cliente cliente;
    
    private String direccion;
    
    private String direccionExtra;
    
    private String poblacion;
    
    private String codigoPostal;
    
    private String provincia;
    
    private String comunidad;
    
    private String pais;
}
