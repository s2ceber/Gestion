package s2.gestion.model.compras;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import s2.gestion.model.base.Documentable;

/**
 * @author Alberto
 * Modelo para las formas de pago de las facturas de los proveedores
 *
 */
@Entity
@Table(name = "forma_pago_compra")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_entidad")
public @Getter @Setter class FormaPagoCompra extends Documentable{
    private String codigo;
    private String nombre;
}
