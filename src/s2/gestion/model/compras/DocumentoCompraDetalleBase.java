package s2.gestion.model.compras;

import java.math.*;

import javax.persistence.*;

import org.openxava.annotations.Depends;
import org.openxava.annotations.DescriptionsList;
import org.openxava.annotations.OnChange;

import lombok.*;
import s2.gestion.actions.compras.OnChangeArticuloDocumentoCompraDetalleBaseAction;
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
    @OnChange(value=OnChangeArticuloDocumentoCompraDetalleBaseAction.class)
    private Articulo articulo;
    private String nombre;
    private String codigo;
    private BigDecimal unidades;
    private BigDecimal precio;
    private BigDecimal importe;
    private BigDecimal dto1;
    private BigDecimal dto2;
    private BigDecimal dto3;
    private BigDecimal dto4;


    public void setArticulo(Articulo articulo){
	if (articulo!=null){
	    setNombre(articulo.getNombre());
	    setCodigo(articulo.getCodigo());
	    
	}
    }
    
    @Depends("unidades, precio")
    public BigDecimal getTotal(){
	BigDecimal multiply;
	try{
	    multiply=unidades.multiply(precio);
	}catch(Exception e){
	    multiply = BigDecimal.ZERO;
	}
	return multiply;
    }
    
}
