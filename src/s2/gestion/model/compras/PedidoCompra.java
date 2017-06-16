package s2.gestion.model.compras;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * @author Alberto
 * Modelo para la cabecera de los pedidos de compra
 *
 */
@Entity
@Table(name = "pedido_compra")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_entidad")
public class PedidoCompra extends DocumentoCompraBase{
}
