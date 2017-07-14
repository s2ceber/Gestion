package s2.gestion.model.ventas;

import java.math.BigDecimal;

import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.openxava.annotations.Depends;
import org.openxava.annotations.DescriptionsList;
import org.openxava.annotations.OnChange;
import org.openxava.annotations.Stereotype;

import lombok.Getter;
import lombok.Setter;
import s2.gestion.actions.ficheros.OnChangeArticuloDocumentoDetalleBaseAction;
import s2.gestion.model.base.Documentable;
import s2.gestion.model.ficheros.Articulo;

/**
 * @author Alberto Modelo para los detalles de los documentos de venta
 *
 */
@MappedSuperclass
// @Table(name = "documento_venta_detalle")

public abstract @Getter @Setter class DocumentoVentaDetalleBase extends Documentable {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_articulo"))
    @DescriptionsList(descriptionProperties = "codigo, nombre")
    @OnChange(value = OnChangeArticuloDocumentoDetalleBaseAction.class)
    private Articulo articulo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_tarifa_venta"))
    @DescriptionsList(descriptionProperties = "nombre, nota")
    private TarifaVenta tarifaVenta;
    private Boolean ivaIncluido;

    private String nombre;
    private String codigo;
    private BigDecimal unidades;
    @Stereotype("MONEY")
    private BigDecimal precio;
    private BigDecimal tipoIva;

    private BigDecimal dto1;
    private BigDecimal dto2;
    private BigDecimal dto3;
    private BigDecimal dto4;

    private BigDecimal importeIva;

    private BigDecimal importe;

    
    public void calculo() {
	if (getPrecio() == null)
	    setPrecio(BigDecimal.ZERO);
	if (getUnidades() == null)
	    setUnidades(BigDecimal.ZERO);
	BigDecimal subTotal = getPrecio().multiply(getUnidades());

	BigDecimal d1 = dto1 == null ? BigDecimal.ZERO : subTotal.multiply(dto1);
	BigDecimal d2 = dto2 == null ? BigDecimal.ZERO : subTotal.multiply(dto2);
	BigDecimal d3 = dto3 == null ? BigDecimal.ZERO : subTotal.multiply(dto3);
	BigDecimal d4 = dto4 == null ? BigDecimal.ZERO : subTotal.multiply(dto4);

	subTotal.subtract(d1).subtract(d2).subtract(d3).subtract(d4);
	
	if (getIvaIncluido()){
	    subTotal.divide(BigDecimal.ONE.add(getTipoIva()));
	}
	setImporte(subTotal);
	setImporteIva(subTotal.multiply(getTipoIva()));
    }
    
    @Depends("precio, unidades")
    public BigDecimal getImporteLinea(){
	calculo();
	return importe;	
    }
    
    public void setIvaIncluido(Boolean ivaIncluido){
	if (ivaIncluido==null) ivaIncluido=false;
	this.ivaIncluido=ivaIncluido;
    }
}
