package s2.gestion.model.compras;

import javax.persistence.*;

import s2.gestion.model.base.Documentable;

/**
 * @author Alberto
 * Modelo para los contactos del proveedor
 *
 */
@Entity
@Table(name = "contacto_proveedor")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_entidad")
public class ContactoProveedor extends Documentable{
	

}
