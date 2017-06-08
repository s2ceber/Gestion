package s2.gestion.model.compras;

import java.math.*;

import javax.persistence.*;

import org.openxava.annotations.DescriptionsList;

import lombok.*;
import s2.gestion.model.base.*;
import s2.gestion.model.ficheros.*;

/**
 * @author Alberto Modelo para los detalles de los documentos de compra
 *
 */
@MappedSuperclass
// @Table(name = "documento_compra_detalle")

public abstract @Getter @Setter class DocumentoCompraDetalleBase extends Documentable {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey=@ForeignKey(name="fk_articulo"))
    @DescriptionsList(descriptionProperties="codigo, nombre")
    private Articulo articulo;
    private String nombreArticulo;
    private String codigoArticulo;
    private BigDecimal unidades;
    private BigDecimal precio;
    private BigDecimal importe;
    private BigDecimal dto1;
    private BigDecimal dto2;
    private BigDecimal dto3;
    private BigDecimal dto4;

    public void setArticulo(Articulo articulo){
	if (articulo!=null){
	    setNombreArticulo(articulo.getNombre());
	    setCodigoArticulo(articulo.getCodigo());
	}
    }
    
    public void calculoPrecio(){
	setImporte(unidades.multiply(precio));
    }
}
