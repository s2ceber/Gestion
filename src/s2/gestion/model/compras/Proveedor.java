package s2.gestion.model.compras;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import s2.gestion.model.base.Documentable;

/**
 * @author Alberto
 * Modelo para los datos de proveedores
 *
 */
@Entity
@Table(name = "proveedor")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_entidad")
public @Getter @Setter class Proveedor extends Documentable {
    private String codigo;
    private String nombre;
}
