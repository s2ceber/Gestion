package s2.gestion.model.compras;

import javax.persistence.*;

import s2.gestion.model.base.Documentable;

/**
 * @author Alberto
 * Modelo para los precios de compra
 *
 */
@Entity
@Table(name = "precio_compra")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_entidad")
public class PrecioCompra extends Documentable{

}
