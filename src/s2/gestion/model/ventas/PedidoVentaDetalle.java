package s2.gestion.model.ventas;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.openxava.annotations.View;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Alberto
 * Modelo para los detalles de los pedidos de venta
 *
 */
@Entity
@Table(name = "pedido_venta_detalle")
@View(members = "articulo, codigo,nombre; precio, tipoIva, ivaIncluido, unidades; dto1, dto2, dto3, dto4; importeLinea ")
public @Getter @Setter class PedidoVentaDetalle extends DocumentoVentaDetalleBase{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_pedido_venta"))
    private PedidoVenta pedidoVenta;

}
