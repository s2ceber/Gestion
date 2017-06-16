package s2.gestion.model.ventas;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * @author Alberto
 * Modelo para el detalle de los albaranes de venta
 *
 */
@Entity
@Table(name = "albaran_venta_detalle")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_entidad")
public class AlbaranVentaDetalle extends DocumentoVentaDetalleBase{


}
