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

import lombok.Getter;
import lombok.Setter;

/**
 * @author Alberto
 * Modelo para la cabecera de los albaranes de venta
 *
 */
@Entity
@Table(name = "albaran_venta")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_entidad")
public @Getter @Setter class AlbaranVenta extends DocumentoVentaBase<AlbaranVenta, AlbaranVentaDetalle>{
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "maestro", cascade = CascadeType.REMOVE)
    @ListProperties("codigo, nombre,unidades,tipoIva, precio, dto1, dto2, dto3, dto4, importeLinea[maestro.totalSinIva, maestro.importeIva, maestro.totalConIva]")
    @AsEmbedded()
    @OrderColumn
    private List<AlbaranVentaDetalle> detalles;
}
