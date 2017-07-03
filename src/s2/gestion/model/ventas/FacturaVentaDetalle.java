package s2.gestion.model.ventas;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.openxava.annotations.View;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Alberto Modelo para los detalles de las facturas de venta
 *
 */
@Entity
@Table(name = "factura_venta_detalle")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo_entidad")
@View(members = "articulo, codigo,nombre, precio;unidades, dto1, dto2, dto3, dto4; importe ")
public @Getter @Setter class FacturaVentaDetalle extends DocumentoVentaDetalleBase {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_factura_venta"))
    private FacturaVenta facturaVenta;

}
