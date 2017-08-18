package s2.gestion.model.ventas;

import java.util.ArrayList;
import java.util.Collection;
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
import org.openxava.annotations.Tab;
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
@View(members="serieDocumento, numero, fecha, cliente, tarifaVenta, formaPago;articulos{detalles} impuestos{ivas}otrosDatos{documentos;nota}")
@Tab(properties="serieDocumento.nombre, numero, fecha, cliente.nombre, cliente.nif, totalSinIva, importeIva, totalConIva")
public @Getter @Setter class FacturaVenta extends DocumentoVentaBase<FacturaVenta, FacturaVentaDetalle>{  
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "maestro", cascade = CascadeType.REMOVE)
    @ListProperties("codigo, nombre,unidades,tipoIva, precio, dto1, dto2, dto3, dto4, importeLinea[maestro.totalSinIva, maestro.importeIva, maestro.totalConIva]")
    @AsEmbedded()
    @OrderColumn
    private List<FacturaVentaDetalle> detalles;
    
    
    public Collection<Impuesto> getIvas(){
	Collection<Impuesto> impuestos=new ArrayList<>();
	for (FacturaVentaDetalle facturaVentaDetalle : getDetalles()) {
	    boolean existe=false;
	    for(Impuesto impuesto:impuestos){
		if (facturaVentaDetalle.getTipoIva().compareTo(impuesto.getTipo())==0){
		    existe=true;
		    impuesto.setImporte(impuesto.getImporte().add(facturaVentaDetalle.getImporteIva()));
		    break;
		}
	    }
	    if (!existe){
		Impuesto impuesto=new Impuesto();
		impuesto.setTipo(facturaVentaDetalle.getTipoIva());
		impuesto.setImporte(facturaVentaDetalle.getImporteIva());
		impuestos.add(impuesto);
	    }
	}
	return impuestos;
    }

}
