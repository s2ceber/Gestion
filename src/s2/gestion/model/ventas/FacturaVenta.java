package s2.gestion.model.ventas;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Alberto
 * Modelo para la cabecera de las facturas de venta
 *
 */
@Entity
@Table(name = "factura_venta")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_entidad")
public class FacturaVenta extends DocumentoVentaBase{


}
