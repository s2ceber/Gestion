package s2.gestion.model.ventas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.openxava.annotations.Depends;
import org.openxava.annotations.DescriptionsList;
import org.openxava.annotations.OnChange;
import org.openxava.annotations.Stereotype;
import org.openxava.jpa.XPersistence;

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
@MappedSuperclass
public abstract @Getter @Setter class DocumentoVentaDetalleBase<M extends DocumentoVentaBase<M, D>, D extends DocumentoVentaDetalleBase<M,D>> extends Documentable  {
    public abstract void setMaestro(M maestro);
    
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
    @Transient
    private BigDecimal unidadesATraspasar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_articulo"))
    @DescriptionsList(descriptionProperties = "codigo, nombre")
    @OnChange(value = OnChangeArticuloDocumentoDetalleBaseAction.class)
    private Articulo articulo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_tarifa_venta"))
    @DescriptionsList(descriptionProperties = "nombre, nota")
    private TarifaVenta tarifaVenta;

    private OrigenTraspaso origenTraspaso;
        
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
	for (DocumentoVentaDetalleBase<?, ?> destino : getDestinos()) {
	    unidades = unidades.add(destino.getUnidades());
	}
	return unidades;
    }

    
    private List<? extends DocumentoVentaDetalleBase<?, ?>> getDestinos() {
	List resultList= new ArrayList<>();
	if (getId()==null) return resultList;
	resultList = getDestinos(PedidoVentaDetalle.class);
	resultList.addAll(getDestinos(AlbaranVentaDetalle.class));
	resultList.addAll(getDestinos(FacturaVentaDetalle.class));
	return resultList;
    }
    
    private List<? extends DocumentoVentaDetalleBase<?, ?>>  getDestinos(Class<? extends DocumentoVentaDetalleBase<?, ?>> clazz) {
	List<? extends DocumentoVentaDetalleBase<?, ?>> resultList=new ArrayList<>();
	if (getOrigenTraspaso()==null) return resultList;
	
	String name=clazz.getAnnotation(Table.class).name();
	DocumentoType type = getOrigenTraspaso().getDocumentoType();
	Long id = origenTraspaso.getIdOrigen();
	String jpql = "from " + name + " d where d.origenTraspaso.documentoType=:type and d.origenTraspaso.idOrigen=:id";
	resultList = XPersistence.getManager()
		.createQuery(jpql, clazz)
		.setParameter("type", type)
		.setParameter("id", id)
		.getResultList();
	return resultList;
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

    public void traspasar(DocumentoVentaDetalleBase<?, ?> destino) {
	if (isTraspasoCompleto())
	    return;
	destino.setArticulo(getArticulo());
	destino.setCodigo(getCodigo());
	destino.setDto1(getDto1());
	destino.setDto2(getDto2());
	destino.setDto3(getDto3());
	destino.setDto4(getDto4());
	destino.setImporte(getImporte());
	destino.setImporteIva(getImporteIva());
	destino.setIvaIncluido(getIvaIncluido());
	destino.setNombre(getNombre());
	destino.setNota(getNota());
	destino.setPrecio(getPrecio());
	destino.setTarifaVenta(getTarifaVenta());
	destino.setTipoIva(getTipoIva());
	destino.setUnidades(getUnidades());
    }

    public BigDecimal getUnidadesPendientesTraspaso() {
	return getUnidades().subtract(getUnidadesTraspasadas());
    }

    public void setIvaIncluido(Boolean ivaIncluido) {
	this.ivaIncluido = ivaIncluido == null ? false : ivaIncluido;
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
