package s2.gestion.model.ventas;

import javax.persistence.*;

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
public @Getter @Setter class DireccionCliente extends Documentable{
    @ManyToOne(fetch=FetchType.LAZY)
    private Cliente cliente;
    
    private String direccion;
    
    private String direccionExtra;
    
    private String poblacion;
    
    private String codigoPostal;
    
    private String provincia;
    
    private String comunidad;
    
    private String pais;
}
