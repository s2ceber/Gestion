package s2.gestion.model.ventas;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import s2.gestion.model.base.Documentable;

/**
 * @author Alberto
 * Modelo para los contactos de los clientes
 *
 */
@Entity
@Table(name = "contacto_cliente")
public @Getter @Setter class ContactoCliente extends Documentable{
    @ManyToOne(fetch=FetchType.LAZY)
    private Cliente cliente;
    
    private String nombre;
    
    private String telefono;
    
    private String email;

}
