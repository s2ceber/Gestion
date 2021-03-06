package s2.gestion.model.ventas;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.openxava.annotations.View;
import org.openxava.annotations.Views;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Alberto Modelo para los detalles de los presupuestos de venta
 *
 */
@Entity
@Table(name = "presupuesto_venta_detalle")
@Views({
@View(members = "id, articulo, codigo,nombre; precio, tipoIva, ivaIncluido, unidades; dto1, dto2, dto3, dto4; importeLinea, unidadesPendientesTraspaso, unidadesATraspasar"),
@View(name="detalle", members = "articulo, codigo,nombre; precio, tipoIva, ivaIncluido, unidades; dto1, dto2, dto3, dto4; importeLinea")
})
public @Getter @Setter class PresupuestoVentaDetalle
	extends DocumentoVentaDetalleBase<PresupuestoVenta, PresupuestoVentaDetalle> {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_presupuesto"))
    private PresupuestoVenta maestro;

}
