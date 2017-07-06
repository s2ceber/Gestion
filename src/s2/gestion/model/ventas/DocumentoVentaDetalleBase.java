package s2.gestion.model.ventas;

import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Formula;
import org.openxava.annotations.Depends;
import org.openxava.annotations.DescriptionsList;
import org.openxava.annotations.OnChange;
import org.openxava.annotations.Stereotype;

import lombok.Getter;
import lombok.Setter;
import lombok.AccessLevel;
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
    
    @Formula("precio*unidades")
    @Stereotype("MONEY")
    @Setter(AccessLevel.NONE)
    @Basic(fetch=FetchType.LAZY)    
    private BigDecimal total;

    @Formula("precio*unidades*coalesce(dto1,0)")
    @Stereotype("MONEY")
    @Setter(AccessLevel.NONE)
    @Basic(fetch=FetchType.LAZY)
    private BigDecimal importeDto1;
    
    public void setArticulo(Articulo articulo) {
	if (articulo != null) {
	    setNombre(articulo.getNombre());
	    setCodigo(articulo.getCodigo());
	    if (articulo.getTipoIva() != null)
		setTipoIva(articulo.getTipoIva().getTipo());
	}
    }

    @Stereotype("MONEY")
    public BigDecimal getImporte() {
	
	BigDecimal d1 = dto1 == null ? BigDecimal.ZERO : getTotal().multiply(dto1);
	BigDecimal d2 = dto2 == null ? BigDecimal.ZERO : getTotal().multiply(dto2);
	BigDecimal d3 = dto3 == null ? BigDecimal.ZERO : getTotal().multiply(dto3);
	BigDecimal d4 = dto4 == null ? BigDecimal.ZERO : getTotal().multiply(dto4);
	
	return getTotal().subtract(d1).subtract(d2).subtract(d3).subtract(d4);
    }
    
    @Stereotype("MONEY")
    public BigDecimal getImporteIva() {
	try {
	    return getImporte().multiply(getTipoIva());
	} catch (Exception e) {
	    return BigDecimal.ZERO;
	}
    }

//    public BigDecimal getTotal() {
//	BigDecimal multiply;
//	try {
//	    multiply = unidades.multiply(precio);
//	} catch (Exception e) {
//	    multiply = BigDecimal.ZERO;
//	}
//	return multiply;
//    }

    @Stereotype("MONEY")
    @Depends("unidades, precio, tipoIva")
    public BigDecimal getTotalConIva() {
	try {
	    return getImporte().add(getImporteIva());
	} catch (Exception e) {
	    return BigDecimal.ZERO;
	}
    }
}
