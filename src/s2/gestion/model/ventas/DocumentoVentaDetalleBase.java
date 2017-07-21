package s2.gestion.model.ventas;

import java.math.BigDecimal;
import java.util.Collection;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.openxava.annotations.Depends;
import org.openxava.annotations.DescriptionsList;
import org.openxava.annotations.OnChange;
import org.openxava.annotations.Stereotype;

import lombok.Getter;
import lombok.Setter;
import s2.gestion.actions.ficheros.OnChangeArticuloDocumentoDetalleBaseAction;
import s2.gestion.model.base.Documentable;
import s2.gestion.model.ficheros.Articulo;
import s2.gestion.util.Util;

/**
 * @author Alberto Modelo para los detalles de los documentos de venta
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "documento_venta_detalle")
@DiscriminatorColumn(name = "tipo_entidad")
public abstract @Getter @Setter class DocumentoVentaDetalleBase extends Documentable {
    private String codigo;
    private BigDecimal dto1;
    private BigDecimal dto2;
    private BigDecimal dto3;
    private BigDecimal dto4;
    private BigDecimal importe;
    private BigDecimal importeIva;
    private Boolean ivaIncluido;
    private String nombre;
    private BigDecimal tipoIva;
    private BigDecimal unidades;
    @Stereotype("MONEY")
    private BigDecimal precio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_articulo"))
    @DescriptionsList(descriptionProperties = "codigo, nombre")
    @OnChange(value = OnChangeArticuloDocumentoDetalleBaseAction.class)
    private Articulo articulo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "origen")
    private Collection<DocumentoVentaDetalleBase> destinos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_origen"))
    private DocumentoVentaDetalleBase origen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_tarifa_venta"))
    @DescriptionsList(descriptionProperties = "nombre, nota")
    private TarifaVenta tarifaVenta;

    public void calculo() {
	if (getPrecio() == null)
	    setPrecio(Util.ZERO);
	if (getUnidades() == null)
	    setUnidades(Util.ZERO);
	BigDecimal subTotal = getPrecio().multiply(getUnidades());

	BigDecimal d1 = dto1 == null ? Util.ZERO : subTotal.multiply(dto1);
	BigDecimal d2 = dto2 == null ? Util.ZERO : subTotal.multiply(dto2);
	BigDecimal d3 = dto3 == null ? Util.ZERO : subTotal.multiply(dto3);
	BigDecimal d4 = dto4 == null ? Util.ZERO : subTotal.multiply(dto4);

	subTotal.subtract(d1).subtract(d2).subtract(d3).subtract(d4);

	if (getIvaIncluido()) {
	    subTotal.divide(Util.ONE.add(tipoIva), Util.getNumeroDecimales(), Util.DEFAULT_ROUNDING_MODE);
	}
	setImporte(subTotal);
	setImporteIva(subTotal.multiply(getTipoIva()));
    }

    @Depends("precio, unidades")
    public BigDecimal getImporteLinea() {
	calculo();
	return importe;
    }

    public BigDecimal getUnidadesTraspasadas() {
	BigDecimal unidades = Util.ZERO;
	for (DocumentoVentaDetalleBase destino : getDestinos()) {
	    unidades = unidades.add(destino.getUnidades());
	}
	return unidades;
    }

    public boolean isTraspasoCompleto() {
	if (getUnidadesTraspasadas().compareTo(getUnidades()) == 0)
	    return true;
	else
	    return false;
    }

    public boolean isTraspasoParcial() {
	if (getUnidadesTraspasadas().compareTo(Util.ZERO) > 0 && !isTraspasoCompleto())
	    return true;
	else
	    return false;
    }
    public void traspasar(DocumentoVentaDetalleBase origen){
	if (origen.isTraspasoCompleto()) return;
	setArticulo(origen.getArticulo());
	setCodigo(origen.getCodigo());
	setDto1(origen.getDto1());
	setDto2(origen.getDto2());
	setDto3(origen.getDto3());
	setDto4(origen.getDto4());
	setImporte(origen.getImporte());
	setImporteIva(origen.getImporteIva());
	setIvaIncluido(origen.getIvaIncluido());
	setNombre(origen.getNombre());
	setNota(origen.getNota());
	setPrecio(origen.getPrecio());
	setTarifaVenta(origen.getTarifaVenta());
	setTipoIva(origen.getTipoIva());
	setUnidades(origen.getUnidades());
    }
    public BigDecimal getUnidadesPendientesTraspaso(){
	return getUnidades().subtract(getUnidadesTraspasadas());
    }
    public void setIvaIncluido(Boolean ivaIncluido) {
	this.ivaIncluido = ivaIncluido==null?false:ivaIncluido;
    }

    public BigDecimal getDto1() {
	return Util.createBigDecimal(dto1);
    }

    public BigDecimal getDto2() {
	return Util.createBigDecimal(dto2);
    }

    public BigDecimal getDto3() {
	return Util.createBigDecimal(dto3);
    }

    public BigDecimal getDto4() {
	return Util.createBigDecimal(dto4);
    }

    public BigDecimal getImporte() {
	return Util.createBigDecimal(importe);
    }

    public BigDecimal getImporteIva() {
	return Util.createBigDecimal(importeIva);
    }

    public BigDecimal getPrecio() {
	return Util.createBigDecimal(precio);
    }

    public BigDecimal getTipoIva() {
	return Util.createBigDecimal(tipoIva);
    }

    public BigDecimal getUnidades() {
	return Util.createBigDecimal(unidades);
    }
}
