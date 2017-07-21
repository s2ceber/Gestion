package s2.gestion.model.ventas;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import javax.persistence.Transient;

import org.openxava.annotations.DefaultValueCalculator;
import org.openxava.annotations.DescriptionsList;
import org.openxava.annotations.OnChange;
import org.openxava.annotations.Stereotype;
import org.openxava.calculators.CurrentDateCalculator;

import lombok.Getter;
import lombok.Setter;
import s2.gestion.actions.ventas.OnChangeClienteDocumentoVenta;
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
    @OnChange(value=OnChangeClienteDocumentoVenta.class)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_forma_pago"))
    @DescriptionsList(descriptionProperties="codigo, nombre", forTabs="NONE")    
    private FormaPagoVenta formaPago;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_tarifa_venta"))
    @DescriptionsList(descriptionProperties="nombre", forTabs="NONE")    
    private TarifaVenta tarifaVenta;
    
    public abstract List<T> getLineasDetalles();
    public abstract void setLineasDetalles(List<T> lineasDetalles);
    
    //@Depends("lineasDetalles")
    @Stereotype("MONEY")
    public BigDecimal getTotalSinIva(){
	BigDecimal total=BigDecimal.ZERO;
	for (T detalle : getLineasDetalles()) {
	    total=total.add(detalle.getImporte());
	}
	return total;
    }
    
    @Stereotype("MONEY")
    public BigDecimal getImporteIva(){
	BigDecimal total=BigDecimal.ZERO;
	for (T detalle : getLineasDetalles()) {
	    total=total.add(detalle.getImporteIva());
	}
	return total;
    }
    
    @Stereotype("MONEY")
    public BigDecimal getTotalConIva(){
	return getTotalSinIva().add(getImporteIva());
    }
    
    public boolean isTraspasado(){
	for (T detalle : getLineasDetalles()) {
	    if (!detalle.isTraspasoCompleto())
		return false;
	}
	return true;
    }
    
    public List<T> getDetallesNoTraspasados(){
	List<T> detalles=new ArrayList<>();
	for(T detalle:getLineasDetalles()){
	    if (!detalle.isTraspasoCompleto()){
		detalles.add(detalle);
	    }
	}
	return detalles;
    }
    
    public static enum DocumentoType {
	PRESUPUESTO, PEDIDO, ALBARAN, FACTURA
    }

    @Transient
    private DocumentoType traspasarA;

    public void traspasar(DocumentoType documentoType){
	
    }

    public void aprobarEnPedido(){

	
    }
    public void copiarLineaDetalle(DocumentoVentaDetalleBase origen, DocumentoVentaDetalleBase destino){
	destino.setArticulo(origen.getArticulo());
	destino.setCodigo(origen.getCodigo());
	destino.setDto1(origen.getDto1());
	
    }

}
