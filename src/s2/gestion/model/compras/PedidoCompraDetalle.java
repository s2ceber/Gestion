package s2.gestion.model.compras;

import java.math.*;

import javax.persistence.*;

import s2.gestion.model.ficheros.*;

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
