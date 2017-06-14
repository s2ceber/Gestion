package s2.gestion.model.compras;

import javax.persistence.*;

import s2.gestion.model.base.Documentable;

/**
 * @author Alberto
 * Modelo para las direcciones del proveedor
 *
 */
@Entity
@Table(name = "direccion_proveedor")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_entidad")
public class DireccionProveedor extends Documentable{

}
