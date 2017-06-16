package s2.gestion.model.compras;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * @author Alberto
 * Modelo para los detalles de los pedidos de compra
 *
 */
@Entity
@Table(name = "pedido_compra_detalle")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_entidad")
public class PedidoCompraDetalle extends DocumentoCompraDetalleBase{

}
