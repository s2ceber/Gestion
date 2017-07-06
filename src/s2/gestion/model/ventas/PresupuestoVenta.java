package s2.gestion.model.ventas;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.openxava.annotations.AsEmbedded;
import org.openxava.annotations.ListProperties;

/**
 * @author Alberto
 * Modelo para la cabecera de los presupuestos de venta
 *
 */
@Entity
@Table(name = "presupuesto_venta")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_entidad")
public class PresupuestoVenta extends DocumentoVentaBase<PresupuestoVentaDetalle>{
    @OneToMany(fetch=FetchType.LAZY, mappedBy="presupuestoVenta", cascade = CascadeType.REMOVE)
    @ListProperties("codigo, nombre,unidades,tipoIva, precio, dto1, dto2, dto3, dto4, importe[presupuestoVenta.total, presupuestoVenta.importeIva, presupuestoVenta.totalConIva]")
    @AsEmbedded()
    @OrderColumn
    private List<PresupuestoVentaDetalle> lineasDetalles;

    public List<PresupuestoVentaDetalle> getLineasDetalles() {
        return lineasDetalles;
    }

    public void setLineasDetalles(List<PresupuestoVentaDetalle> lineasDetalles) {
        this.lineasDetalles = lineasDetalles;
    }

}
