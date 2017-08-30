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

import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.openxava.annotations.DefaultValueCalculator;
import org.openxava.annotations.DescriptionsList;
import org.openxava.annotations.EditOnly;
import org.openxava.annotations.ListProperties;
import org.openxava.annotations.OnChange;
import org.openxava.annotations.Stereotype;
import org.openxava.calculators.CurrentDateCalculator;
import org.openxava.jpa.XPersistence;

import lombok.Getter;
import lombok.Setter;
import s2.gestion.actions.ventas.OnChangeClienteDocumentoVenta;
import s2.gestion.model.base.Documentable;

@MappedSuperclass
// @Table(name = "documento_venta")
public @Getter @Setter
abstract class DocumentoVentaBase<M extends DocumentoVentaBase<M,D>, D extends DocumentoVentaDetalleBase<M,D>> extends Documentable  {
   
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

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "maestro", cascade = CascadeType.REMOVE)
//    @ListProperties("codigo, nombre,unidades,tipoIva, precio, dto1, dto2, dto3, dto4, importeLinea[maestro.totalSinIva, maestro.importeIva, maestro.totalConIva]")
//    @AsEmbedded()
//    @OrderColumn
//    private List<D> detalles;
    
    public abstract List<D> getDetalles();
    public abstract void setDetalles(List<D> detalles);
    
    
    //@Depends("lineasDetalles")
    @Stereotype("MONEY")
    public BigDecimal getTotalSinIva(){
	BigDecimal total=BigDecimal.ZERO;
	for (D detalle : getDetalles()) {
	    total=total.add(detalle.getImporte());
	}
	return total;
    }
    
    @Stereotype("MONEY")
    public BigDecimal getImporteIva(){
	BigDecimal total=BigDecimal.ZERO;
	for (D detalle : getDetalles()) {
	    total=total.add(detalle.getImporteIva());
	}
	return total;
    }
    
    @Stereotype("MONEY")
    public BigDecimal getTotalConIva(){
	return getTotalSinIva().add(getImporteIva());
    }
    
    public boolean isTraspasado(){
	for (D detalle : getDetalles()) {
	    if (!detalle.isTraspasoCompleto())
		return false;
	}
	return true;
    }
    
    @ListProperties("id, codigo, nombre,unidades, precio, importeLinea, unidadesPendientesTraspaso, unidadesATraspasar")
    @ElementCollection
    @EditOnly
    @Transient
    private List<D> detallesNoTraspasados;
    
    public List<D> getDetallesNoTraspasados(){
	detallesNoTraspasados=new ArrayList<>();
	for(D detalle:getDetalles()){
	    if (!detalle.isTraspasoCompleto()){
		detallesNoTraspasados.add(detalle);
	    }
	}
	return detallesNoTraspasados;
    }

    @Transient
    private DocumentoType traspasarA;

    public <T extends DocumentoVentaBase<T,U>, U extends DocumentoVentaDetalleBase<T,U>> T traspasar(DocumentoType type){
	if (this.isTraspasado()) return null;
	
	T destino = DocumentoVentaFactory.getDocumento(type);
	destino.setCliente(getCliente());
	destino.setDocumentos(this.getDocumentos());
	destino.setFecha(new java.util.Date());
	destino.setFormaPago(this.getFormaPago());
	destino.setNota(this.getNota());
	// pv.setNumero(numero);
	destino.setSerieDocumento(this.getSerieDocumento());
	destino.setTarifaVenta(this.getTarifaVenta());

	for (D detalleOrigen : this.getDetallesNoTraspasados()) {
	    U detalleDestino = DocumentoVentaFactory.getDetalle(type);
	    OrigenTraspaso ot =new OrigenTraspaso();
	    ot.setDocumentoType(DocumentoType.get(this));
	    detalleDestino.setOrigenTraspaso(ot);
	    detalleDestino.setMaestro(destino);
	    
	    detalleOrigen.traspasar(detalleDestino);
	    
	    XPersistence.getManager().persist(detalleDestino);
	    destino.getDetalles().add(detalleDestino);
	}
	
	XPersistence.getManager().persist(destino);
	return destino;
    }
}
