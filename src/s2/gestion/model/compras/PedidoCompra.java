package s2.gestion.model.compras;

import java.util.*;

import javax.persistence.*;

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
