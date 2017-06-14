package s2.gestion.model.compras;

import javax.persistence.*;

import s2.gestion.model.base.Documentable;

/**
 * @author Alberto
 * Modelo para las previsiones de las facturas de compra
 *
 */
@Entity
@Table(name = "prevision_factura_compra")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_entidad")
public class PrevisionFacturaCompra extends Documentable {

}
