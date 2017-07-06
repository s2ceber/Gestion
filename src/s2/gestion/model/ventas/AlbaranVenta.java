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
 * Modelo para la cabecera de los albaranes de venta
 *
 */
@Entity
@Table(name = "albaran_venta")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_entidad")
public class AlbaranVenta extends DocumentoVentaBase<AlbaranVentaDetalle>{
    @OneToMany(fetch=FetchType.LAZY, mappedBy="albaranVenta", cascade = CascadeType.REMOVE)
    @ListProperties("codigo, nombre,unidades,tipoIva, precio, dto1, dto2, dto3, dto4, importe[albaranVenta.total, albaranVenta.importeIva, albaranVenta.totalConIva]")
    @AsEmbedded()
    @OrderColumn
    private List<AlbaranVentaDetalle> lineasDetalles;

    @Override
    public List<AlbaranVentaDetalle> getLineasDetalles() {
        return lineasDetalles;
    }
    @Override
    public void setLineasDetalles(List<AlbaranVentaDetalle> lineasDetalles) {
        this.lineasDetalles = lineasDetalles;
    }

}
