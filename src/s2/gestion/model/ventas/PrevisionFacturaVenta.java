package s2.gestion.model.ventas;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import s2.gestion.model.base.Documentable;

/**
 * @author Alberto
 * Modelo para las previsiones de las facturas de venta
 *
 */
@Entity
@Table(name = "prevision_factura_venta")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_entidad")
public class PrevisionFacturaVenta extends Documentable{

}
