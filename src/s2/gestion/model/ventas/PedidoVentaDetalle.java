package s2.gestion.model.ventas;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * @author Alberto
 * Modelo para los detalles de los pedidos de venta
 *
 */
@Entity
@Table(name = "pedido_venta_detalle")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_entidad")
public class PedidoVentaDetalle extends DocumentoVentaDetalleBase{


}
