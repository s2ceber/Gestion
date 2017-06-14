package s2.gestion.model.ventas;

import javax.persistence.*;

import s2.gestion.model.base.Documentable;

/**
 * @author Alberto
 * Modelo para las entregas de los clientes de presupuestos, pedidos y albaranes
 *
 */
@Entity
@Table(name = "entrega_cliente")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_entidad")
public class EntregaCliente extends Documentable{

}
