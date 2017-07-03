package s2.gestion.model.ventas;

import java.math.BigDecimal;
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
import org.openxava.annotations.Stereotype;
import org.openxava.annotations.View;

import lombok.Getter;
import lombok.Setter;


/**
 * @author Alberto
 * Modelo para la cabecera de las facturas de venta
 *
 */
@Entity
@Table(name = "factura_venta")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_entidad")
@View(members="numero, fecha, cliente, formaPago;articulos{facturaVentaDetalles} otrosDatos{documentos;nota}")
public @Getter @Setter class FacturaVenta extends DocumentoVentaBase{

    @OneToMany(fetch=FetchType.LAZY, mappedBy="facturaVenta", cascade = CascadeType.REMOVE)
    @ListProperties("codigo, nombre,unidades,tipoIva, precio, dto1, dto2, dto3, dto4, importe[facturaVenta.total, facturaVenta.importeIva, facturaVenta.totalConIva]")
    @AsEmbedded()
    @OrderColumn
    private List<FacturaVentaDetalle> facturaVentaDetalles;

    //@Depends("facturaVentaDetalles")
    @Stereotype("MONEY")
    public BigDecimal getTotal(){
	BigDecimal total=BigDecimal.ZERO;
	for (FacturaVentaDetalle facturaVentaDetalle : facturaVentaDetalles) {
	    total=total.add(facturaVentaDetalle.getTotal());
	}
	return total;
    }
    
    @Stereotype("MONEY")
    public BigDecimal getTotalConIva(){
	BigDecimal total=BigDecimal.ZERO;
	for (FacturaVentaDetalle facturaVentaDetalle : facturaVentaDetalles) {
	    total=total.add(facturaVentaDetalle.getTotalConIva());
	}
	return total;
    }
    
    @Stereotype("MONEY")
    public BigDecimal getImporteIva(){
	return getTotalConIva().subtract(getTotal());
    }
}
