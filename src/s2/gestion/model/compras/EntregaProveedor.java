package s2.gestion.model.compras;

import javax.persistence.*;

import s2.gestion.model.base.Documentable;

/**
 * @author Alberto
 * Modelo para las entregas a cuenta del proveedor, tanto pedidos como albaranes
 *
 */
@Entity
@Table(name = "entrega_proveedor")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_entidad")
public class EntregaProveedor extends Documentable{

}
