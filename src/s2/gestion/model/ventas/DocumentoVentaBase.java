package s2.gestion.model.ventas;

import java.math.BigDecimal;
/**
 * @author Alberto
 * Modelo para la cabecera de los documentos de venta
 *
 */
import java.util.Date;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.openxava.annotations.DefaultValueCalculator;
import org.openxava.annotations.DescriptionsList;
import org.openxava.annotations.Stereotype;
import org.openxava.calculators.CurrentDateCalculator;

import lombok.Getter;
import lombok.Setter;
import s2.gestion.model.base.Documentable;

@MappedSuperclass
// @Table(name = "documento_venta")
public @Getter @Setter
abstract class DocumentoVentaBase<T extends DocumentoVentaDetalleBase> extends Documentable {
    private Integer numero;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_serieDocumento"))
    @DescriptionsList(descriptionProperties="nombre", forTabs="NONE")    
    private SerieDocumento serieDocumento;
    
    @DefaultValueCalculator(value=CurrentDateCalculator.class)
    private Date fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_cliente"))
    @DescriptionsList(descriptionProperties="nombre, nif", forTabs="NONE")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_forma_pago"), columnDefinition="UUID")
    @DescriptionsList(descriptionProperties="codigo, nombre", forTabs="NONE")    
    private FormaPagoVenta formaPago;

    
    public abstract List<T> getLineasDetalles();
    public abstract void setLineasDetalles(List<T> lineasDetalles);
    
    //@Depends("lineasDetalles")
    @Stereotype("MONEY")
    public BigDecimal getTotal(){
	BigDecimal total=BigDecimal.ZERO;
	for (T detalle : getLineasDetalles()) {
	    total=total.add(detalle.getTotal());
	}
	return total;
    }
    
    @Stereotype("MONEY")
    public BigDecimal getTotalConIva(){
	BigDecimal total=BigDecimal.ZERO;
	for (T detalle : getLineasDetalles()) {
	    total=total.add(detalle.getTotalConIva());
	}
	return total;
    }
    
    @Stereotype("MONEY")
    public BigDecimal getImporteIva(){
	return getTotalConIva().subtract(getTotal());
    }
}
